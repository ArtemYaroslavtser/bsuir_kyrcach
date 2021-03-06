package com.example.Kyrcach_java_Yaroslavtser.controller;


import com.example.Kyrcach_java_Yaroslavtser.Repositry.AccountsEntityRepository;
import com.example.Kyrcach_java_Yaroslavtser.Repositry.Accounts_ychetEntityRepository;
import com.example.Kyrcach_java_Yaroslavtser.Repositry.UserEntityRepository;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.*;
import com.example.Kyrcach_java_Yaroslavtser.exception.UserNotFoundException;
import com.example.Kyrcach_java_Yaroslavtser.model.*;
import com.example.Kyrcach_java_Yaroslavtser.security.CustomUserDetail;
import com.example.Kyrcach_java_Yaroslavtser.service.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


@Controller
@RequestMapping(value = "/users")
public class UserController {

    private UserService userService;
    private RoleService roleService;
    private BalanceService balanceService;
    private WorkService workService;

    private DogovorService dogovor;

    private Accounts_ychetService accounts_ychetService;

    private YchetService ychetService;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private AccountsService accountsService;

    @Autowired
    private AccountsEntityRepository accountsEntityRepository;

    @Autowired Accounts_ychetEntityRepository accounts_ychetEntityRepository;


    public UserController(UserService userService, RoleService roleService, BalanceService balanceService, WorkService workService, DogovorService dogovorService, Accounts_ychetService accounts_ychetService, YchetService ychetService) {
        this.userService = userService;
        this.roleService = roleService;
        this.balanceService = balanceService;
        this.workService = workService;
        this.dogovor = dogovorService;
        this.accounts_ychetService = accounts_ychetService;
        this.ychetService = ychetService;
     }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping()
    public String getAllUsersByRole(@RequestParam(value = "role", required = false) String role, @NotNull Model model) {
        role = role == null ? "USER" : role;
        List<UserDTO> users = roleService.getUsersByRole(role);
        model.addAttribute("role", role);
        model.addAttribute("users", users.size() == 0 ? null : users);
        return "showUsers";
    }

    public String getAllUsersByRole() {
        return "showUsers";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(path = "/delete")
    public String deleteEmployeeById(Model model,
                                     @RequestParam("id") Long id,
                                     @RequestParam(value = "role") String role) throws UserNotFoundException {
        userService.deleteUserById(id);
        String redirect = "redirect:/users?&role=" + role;
        return redirect;
    }


    @GetMapping(path = {"/edit", "/edit/{id}"})
    public String getAddOrEditUserView(
            Model model,
            @RequestParam(value = "role", required = false, defaultValue = "USER") String role,
            @PathVariable(value = "id") Optional<Long> id) {
        if (id.isPresent()) {
            UserDTO userDTO = userService.findUserById(id.get());
            if (userDTO != null) {
                model.addAttribute("user", userDTO);
            } else {
                throw new UserNotFoundException("User with id=" + id + " not found");
            }
            return "addEditUser";
        } else {
            UserDTO userDTO = new UserDTO();
            userDTO.setRole(role);
            model.addAttribute("user", userDTO);
            return "addEditUser";
        }
    }

    public String getAddOrEditUserView() {
        return "addEditUser";
    }

    @GetMapping(path = {"/vibor_edit","/vibor_edit/{id}"})
    public String getVibor(Model model,
            @AuthenticationPrincipal CustomUserDetail currentUser) {
            model.addAttribute("id", currentUser.getId());
            return "vibor_edit";

    }


    @GetMapping(path = {"/dogovor"})
    public String getDogovor(Model model,
                           @AuthenticationPrincipal CustomUserDetail currentUser) {

        model.addAttribute("id", currentUser.getId());
        model.addAttribute("dogovor", dogovor.findByUserId(currentUser.getId()));
        return "addEditDogovor";

    }

    public String getDogovor() {
        return "addEditDogovor";
    }

    @GetMapping(path =  "/dogovor/reg")
    public String Add_orChange_Dogovor(Model model,@AuthenticationPrincipal CustomUserDetail currentUser) {
        BalanceDTO balanceDTO = balanceService.findByUserId(currentUser.getId());
        if(balanceDTO.getBalance() >= 500) {
            balanceService.minus(balanceDTO);
            DogovorDTO dogovorDTO = dogovor.findByUserId(currentUser.getId());
            dogovor.update(dogovorDTO);
            return "redirect:/home";
        }

       return "ErorBalance";
    }


    @GetMapping(path =  "/bux")
    public String Byx_add(Model model,@AuthenticationPrincipal CustomUserDetail currentUser) {
        DogovorDTO dogovorDTO = dogovor.findByUserId(currentUser.getId());
        if(dogovorDTO.getStatus() == DogovorStatus.????????????????) {
          //  Date date = new Date("2021-????????????-01");
            YchetDTO ychetDTO = new YchetDTO();
            //ychetDTO.setDateFirst(date);
            model.addAttribute("id", currentUser.getId());
            model.addAttribute("userEntity", currentUser);
            model.addAttribute("bux", ychetDTO);

            return "Byx_ychetAdd";
        }
        return "ErorDogovor";

    }

    public String Byx_add() {
        return "Byx_ychetAdd";
    }

    @PostMapping(path =  "/bux")
    public String Byx_add_post(@Valid @ModelAttribute("bux") YchetDTO ychetDTO,
                               @AuthenticationPrincipal CustomUserDetail currUser,
                               BindingResult result, Model model) {

        if (result.hasErrors()) {
            ApiError apiError = new ApiError();
            String message = "";
            for (FieldError str : result.getFieldErrors()) {
                message += str.getDefaultMessage();
                apiError.setMessage(message);
            }
            model.addAttribute("balance", ychetDTO);
            model.addAttribute("apiError", apiError);
            return "Byx_ychetAdd";
        }
        accounts_ychetService.add_Byx(ychetDTO,ychetService.add_Ychet_by_Byx(ychetDTO,currUser));
        return "redirect:/home";
    }


    @GetMapping(path =  "/oper")
    public String Oper_add(Model model,@AuthenticationPrincipal CustomUserDetail currentUser) {
        DogovorDTO dogovorDTO = dogovor.findByUserId(currentUser.getId());
        if(dogovorDTO.getStatus() == DogovorStatus.????????????????) {
            model.addAttribute("id", currentUser.getId());
            model.addAttribute("userEntity", currentUser);
            model.addAttribute("bux", new YchetDTO());
            return "oper_ychetAdd";
        }
        return "ErorDogovor";

    }

    public String Oper_add() {
        return "oper_ychetAdd";
    }

    @PostMapping(path =  "/oper")
    public String Oper_add_post(@Valid @ModelAttribute("bux") YchetDTO ychetDTO,
                               @AuthenticationPrincipal CustomUserDetail currUser,
                               BindingResult result, Model model) {

        if (result.hasErrors()) {
            ApiError apiError = new ApiError();
            String message = "";
            for (FieldError str : result.getFieldErrors()) {
                message += str.getDefaultMessage();
                apiError.setMessage(message);
            }
            model.addAttribute("balance", ychetDTO);
            model.addAttribute("apiError", apiError);
            return "oper_ychetAdd";
        }

        accounts_ychetService.add_Oper(ychetDTO, ychetService.add_Ychet_by_oper(ychetDTO, currUser));
        return "redirect:/home";
    }

    @GetMapping(path = {"/balance", "/balance/{id}"})
    public String Add_orChange_Balance_get(
            Model model,
            @AuthenticationPrincipal CustomUserDetail currUser
    ){
            model.addAttribute("id", currUser.getId());
            model.addAttribute("balance", balanceService.findByUserId(currUser.getId()));
            return "addEditBalance";
    }

    public String Add_orChange_Balance_get() {
        return "addEditBalance";
    }

    @PostMapping(path =  "/balance")
    public String Add_orChange_Balance(@Valid @ModelAttribute("balance") BalanceDTO balanceDTO,
                                       BindingResult result, Model model) {

        if (result.hasErrors()) {
            ApiError apiError = new ApiError();
            String message = "";
            for (FieldError str : result.getFieldErrors()) {
                message += str.getDefaultMessage();
                apiError.setMessage(message);
            }
            model.addAttribute("balance", balanceDTO);
            model.addAttribute("apiError", apiError);
            return "home";
        }

        balanceService.update(balanceDTO);

        return "redirect:/home";
    }

    @GetMapping(path = {"/work", "/work/{id}"})
    public String Add_orChange_Work_get(
            Model model,
            @AuthenticationPrincipal CustomUserDetail currUser
    ){
        DogovorDTO dogovorDTO = dogovor.findByUserId(currUser.getId());
        if(dogovorDTO.getStatus() == DogovorStatus.????????????????) {
            model.addAttribute("id", currUser.getId());
            model.addAttribute("work", workService.findByUserId(currUser.getId()));
            return "addEditWork";
        }

        return "ErorDogovor";
    }

    public String Add_orChange_Work_get() {
        return "addEditWork";
    }

    @PostMapping(path =  "/work")
    public String Add_orChange_Work(@Valid @ModelAttribute("work") WorkDTO work,
                                BindingResult result, Model model) {

        if (result.hasErrors()) {
            ApiError apiError = new ApiError();
            String message = "";
            for (FieldError str : result.getFieldErrors()) {
                message += str.getDefaultMessage();
                apiError.setMessage(message);
            }
            model.addAttribute("work", work);
            model.addAttribute("apiError", apiError);
            return "addEditWork";
        }

        workService.update(work);

        return "redirect:/home";
    }

    @PostMapping(path = "/edit")
    public String addOrEditUser(@Valid @ModelAttribute("user") UserDTO user,
                                @RequestParam(value = "role", required = false, defaultValue = "USER") String role,
                                BindingResult result, Model model) {

        if (result.hasErrors()) {
            ApiError apiError = new ApiError();
            String message = "";
            for (FieldError str : result.getFieldErrors()) {
                message += str.getDefaultMessage();
                apiError.setMessage(message);
            }
            model.addAttribute("user", user);
            model.addAttribute("apiError", apiError);
            return "addEditUser";
        }

        if (user.getId() != null) {
            userService.update(user);
        } else {
            userService.save(user, role);
        }

        return "redirect:/home";
    }


    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping(path = "/virychka")
    public String vir(Model model, @AuthenticationPrincipal CustomUserDetail currUser) {
        int vir = 0;
        List<AccountsEntity> accountsEntityList = accountsEntityRepository.findAllByUserEntityId(currUser.getId());
        for (AccountsEntity accountsEntity : accountsEntityList) {
            if(accountsEntity.getStatus() == OrderStatus.????????????) {
            vir = vir + accountsEntity.getSymm();
            }
            if(accountsEntity.getStatus() == OrderStatus.??????????????) {
                vir = vir - accountsEntity.getSymm();
            }
        }
        model.addAttribute("id", currUser.getId());
        model.addAttribute("vir",vir);
        return "virychka";
    }

    public String vir_test() {
        return "virychka";
    }

    @GetMapping(path = "/byx_pr")
    public String byx_pr(Model model, @AuthenticationPrincipal CustomUserDetail currUser) {
        Long id_ychet = 0L;
        ArrayList<Accounts_ychetEntity> byx = new ArrayList<>();
        List<Accounts_ychetEntity> accounts_ychetEntitiList = accounts_ychetEntityRepository.findAll();
        for (Accounts_ychetEntity accountsYchetEntity : accounts_ychetEntitiList) {
            if (accountsYchetEntity.getYchetEntity().getStatus() == YchetStatus.??????????????????????????) {
                if (accountsYchetEntity.getYchetEntity().getYchetStatus_admin() == YchetStatus_admin.????????????????) {
                    if(accountsYchetEntity.getYchetEntity().getId() != id_ychet){
                        if(accountsYchetEntity.getYchetEntity().getUserEntity1().getId() == currUser.getId()){
                            id_ychet = accountsYchetEntity.getYchetEntity().getId();
                            byx.add(accountsYchetEntity);
                        }

                    }
                }

            }
        }
        model.addAttribute("id", currUser.getId());
        model.addAttribute("byx",byx);
        return "byx_pr";
    }


    @GetMapping(path = "/byx_pr_id")
    public String byx_pr(Model model,Long id, Long id_user) {
        int Doxod = 0;
        int Ras = 0;
        int nalog_1 = 0;
        Long id_ychet = 0L;
        Accounts_ychetEntity accounts_ychetEntity1 = null;
        List<Accounts_ychetEntity> accounts_ychetEntitiList = accounts_ychetEntityRepository.findByYchetEntityId(id);
        List<Accounts_ychetEntity> accounts_ychetEntitiList1 = accounts_ychetEntityRepository.findByYchetEntityId(id);
        for (Accounts_ychetEntity accountsYchetEntity : accounts_ychetEntitiList) {
          {
                if(accountsYchetEntity.getAccounts().getStatus() == OrderStatus.????????????) {
                    Doxod = Doxod + accountsYchetEntity.getAccounts().getSymm();
                    nalog_1 = nalog_1 + accountsYchetEntity.getAccounts().getNalog();
                }
                if(accountsYchetEntity.getAccounts().getStatus() == OrderStatus.??????????????){
                    Ras = Ras + accountsYchetEntity.getAccounts().getSymm();
                }
          }

        }
        model.addAttribute("id", id_user);
        model.addAttribute("byx",accounts_ychetEntitiList);
        model.addAttribute("nalog",nalog_1);
        model.addAttribute("vir", OrderStatus.????????????);
        model.addAttribute("doxod", Doxod);
        model.addAttribute("ras", OrderStatus.??????????????);
        model.addAttribute("Doxod_1", Order_balance.????????????_????_????????????????????);
        model.addAttribute("Doxod_2", Order_balance.????????????????????????????????_????????????);
        model.addAttribute("Doxod_3", Order_balance.????????_??????????????????????);
        model.addAttribute("Rasxod_1", Order_balance.??????????????);
        model.addAttribute("Rasxod_2", Order_balance.????????_??????????????);
        model.addAttribute("Rasxod", Ras);
        return "byx_pr_id";
    }


    @PostMapping(path =  "/byx_pr")
    public String Byx_pr_post(@RequestParam Long id,@RequestParam Long id_user, Model model) {

        return byx_pr(model, id, id_user);
    }

    @GetMapping(path = "/oper_pr")
    public String oper_pr(Model model, @AuthenticationPrincipal CustomUserDetail currUser) {
        Long id_ychet = 0L;
        ArrayList<Accounts_ychetEntity> byx = new ArrayList<>();
        List<Accounts_ychetEntity> accounts_ychetEntitiList = accounts_ychetEntityRepository.findAll();
        for (Accounts_ychetEntity accountsYchetEntity : accounts_ychetEntitiList) {
            if (accountsYchetEntity.getYchetEntity().getStatus() == YchetStatus.??????????????????????) {
                if (accountsYchetEntity.getYchetEntity().getYchetStatus_admin() == YchetStatus_admin.????????????????) {
                    if(accountsYchetEntity.getYchetEntity().getId() != id_ychet){
                        if(accountsYchetEntity.getYchetEntity().getUserEntity1().getId() == currUser.getId()){
                            id_ychet = accountsYchetEntity.getYchetEntity().getId();
                            byx.add(accountsYchetEntity);
                        }

                    }
                }

            }
        }
        model.addAttribute("id", currUser.getId());
        model.addAttribute("byx",byx);
        return "oper_pr";
    }


    @GetMapping(path = "/oper_pr_id")
    public String oper_pr(Model model,Long id, Long id_user) {
        int Doxod = 0;
        int Ras = 0;
        Long id_ychet = 0L;
        Accounts_ychetEntity accounts_ychetEntity1 = null;
        List<Accounts_ychetEntity> accounts_ychetEntitiList = accounts_ychetEntityRepository.findByYchetEntityId(id);
        for (Accounts_ychetEntity accountsYchetEntity : accounts_ychetEntitiList) {
            accounts_ychetEntity1 = accountsYchetEntity;
            if(accountsYchetEntity.getAccounts().getStatus() == OrderStatus.????????????) {
                Doxod = Doxod + accountsYchetEntity.getAccounts().getSymm();
            }
            if(accountsYchetEntity.getAccounts().getStatus() == OrderStatus.??????????????){
                Ras = Ras + accountsYchetEntity.getAccounts().getSymm();
            }
        }
        accounts_ychetEntitiList.remove(accounts_ychetEntity1);
        model.addAttribute("id", id_user);
        model.addAttribute("byx",accounts_ychetEntitiList);
        model.addAttribute("byx_1", accounts_ychetEntity1);
        model.addAttribute("vir", OrderStatus.????????????);
        model.addAttribute("doxod", Doxod);
        model.addAttribute("ras", OrderStatus.??????????????);
        model.addAttribute("Rasxod", Ras);
        return "oper_pr_id";
    }


    @PostMapping(path =  "/oper_pr")
    public String oper_pr_post(@RequestParam Long id,@RequestParam Long id_user, Model model) {

        return oper_pr(model, id, id_user);
    }

    @GetMapping(path = "/KYDIR")
    public String kydir(Model model, @AuthenticationPrincipal CustomUserDetail currUser) {
        List<AccountsEntity> accountsEntityList = accountsEntityRepository.findAllByUserEntityId(currUser.getId());
        int Doxod = 0;
        int Ras = 0;
        AccountsEntity accountsEntity1 = null;
        for (AccountsEntity accountsEntity :  accountsEntityList) {
            accountsEntity1 = accountsEntity;
            if(accountsEntity.getStatus() == OrderStatus.????????????) {
                Doxod = Doxod + accountsEntity.getSymm();
            }
            if(accountsEntity.getStatus() == OrderStatus.??????????????){
                Ras = Ras + accountsEntity.getSymm();
            }
        }
        accountsEntityList.remove(accountsEntity1);
        model.addAttribute("byx", accountsEntityList);
        model.addAttribute("byx_1", accountsEntity1);
        model.addAttribute("vir", OrderStatus.????????????);
        model.addAttribute("doxod", Doxod);
        model.addAttribute("ras", OrderStatus.??????????????);
        model.addAttribute("Rasxod", Ras);
        return "kydir";
    }
    @GetMapping(path = "/nds")
    public String View_accounts(Model model) {
        return "nds_vibor";
    }

    @GetMapping(path = "/NDS_YCH")
    public String NDS_YCH(Model model,@AuthenticationPrincipal CustomUserDetail currUser) {
        int promeh = 0;
        int Nds = 0;
        List<Accounts_ychetEntity> accounts_ychetEntityList = accounts_ychetEntityRepository.findByYchetEntityId_(currUser.getId(), NDS_oplata.??????, 0);
        for (Accounts_ychetEntity accountsYchetEntity :  accounts_ychetEntityList) {
            promeh = promeh + accountsYchetEntity.getAccounts().getSymm();
        }
        Nds = promeh * 6/100;
        model.addAttribute("id",currUser.getId());
        model.addAttribute("Nds",Nds);
        model.addAttribute("byx",accounts_ychetEntityList);
        return "NDS_YCH";
    }

    @GetMapping(path = "/NDS_YCH/reg")
    public String NDS_YCH_post(Model model, @AuthenticationPrincipal CustomUserDetail currUser) {
        int promeh = 0;
        int Nds = 0;
        Accounts_ychetEntity accountsYchetEntity1 = new Accounts_ychetEntity();
        List<Accounts_ychetEntity> accounts_ychetEntityList = accounts_ychetEntityRepository.findByYchetEntityId_(currUser.getId(), NDS_oplata.??????,0);
        for (Accounts_ychetEntity accountsYchetEntity :  accounts_ychetEntityList) {
            promeh = promeh + accountsYchetEntity.getAccounts().getSymm();
            accountsYchetEntity1 = accountsYchetEntity;
            accountsYchetEntity.getAccounts().setNDS_YES(1);
            accounts_ychetEntityRepository.save(accountsYchetEntity);
        }
        Nds = promeh * 6/100;
        accountsService.add_Accounts_by_YSH(accountsYchetEntity1,Nds);
        return "home";

    }
    @GetMapping(path = "/NDS_Pod")
    public String NDS_Pod(Model model, @AuthenticationPrincipal CustomUserDetail currUser) {
        int promeh = 0;
        int Nds = 0;
        int minus = 0;
        int minus_2 = 0;
        List<Accounts_ychetEntity> accounts_ychetEntityList = accounts_ychetEntityRepository.findByYchetEntityId_1(currUser.getId(),0);
        for (Accounts_ychetEntity accountsYchetEntity :  accounts_ychetEntityList) {
            if(accountsYchetEntity.getAccounts().getGoal() == Operation.????????????????_????????????){
                if(accountsYchetEntity.getAccounts().getNds_oplata() == NDS_oplata.????????????????????_??????????){
                    promeh = promeh + accountsYchetEntity.getAccounts().getSymm();
                }
            }
            if(accountsYchetEntity.getAccounts().getGoal() == Operation.??????????????_??????_??????_????????????????_???????????? || accountsYchetEntity.getAccounts().getGoal() == Operation.??????????????_???? || accountsYchetEntity.getAccounts().getGoal() == Operation.????????????_?????????????????? ) {
                minus = minus + accountsYchetEntity.getAccounts().getSymm() + accountsYchetEntity.getAccounts().getNalog();
            }
        }
        List<Accounts_ychetEntity> accountsYchetEntity = accounts_ychetEntityRepository.findByYchetEntityId_Vit(currUser.getId(),Operation.????????????????????_??????);
        for (Accounts_ychetEntity accountsYchetEntity1 :  accountsYchetEntity) {
            minus_2 = minus_2 + accountsYchetEntity1.getAccounts().getSymm();
        }

        Nds = (promeh - minus - minus_2)*16/100;
        model.addAttribute("id",currUser.getId());
        model.addAttribute("Nds",32);
        model.addAttribute("byx",accounts_ychetEntityList);
        return "NDS_Pod";
    }

    @GetMapping(path = "/NDS_Pod/reg")
    public String NDS_Pod_post(Model model,@AuthenticationPrincipal CustomUserDetail currUser) {
        int promeh = 0;
        int Nds = 0;
        int minus = 0;
        int minus_2 = 0;
        Accounts_ychetEntity accountsYchetEntity3 = new Accounts_ychetEntity();
        List<Accounts_ychetEntity> accounts_ychetEntityList = accounts_ychetEntityRepository.findByYchetEntityId_1(currUser.getId(),0);
        for (Accounts_ychetEntity accountsYchetEntity :  accounts_ychetEntityList) {
            if(accountsYchetEntity.getAccounts().getGoal() == Operation.????????????????_????????????){
                if(accountsYchetEntity.getAccounts().getNds_oplata() == NDS_oplata.????????????????????_??????????){
                    promeh = promeh + accountsYchetEntity.getAccounts().getSymm();
                }
            }
            if(accountsYchetEntity.getAccounts().getGoal() == Operation.??????????????_??????_??????_????????????????_???????????? || accountsYchetEntity.getAccounts().getGoal() == Operation.??????????????_???? || accountsYchetEntity.getAccounts().getGoal() == Operation.????????????_?????????????????? ) {
                minus = minus + accountsYchetEntity.getAccounts().getSymm() + accountsYchetEntity.getAccounts().getNalog();
            }
            accountsYchetEntity3 = accountsYchetEntity;
        }
        List<Accounts_ychetEntity> accountsYchetEntity = accounts_ychetEntityRepository.findByYchetEntityId_Vit(currUser.getId(),Operation.????????????????????_??????);
        for (Accounts_ychetEntity accountsYchetEntity1 :  accountsYchetEntity) {
            minus_2 = minus_2 + accountsYchetEntity1.getAccounts().getSymm();
        }

        Nds = (promeh - minus - minus_2)*16/100;
        accountsService.add_Accounts_by_POD(accountsYchetEntity3,32);
        return "home";
    }

    @GetMapping(path = "/nds_yplat")
    public String NDS_yplat(Model model, @AuthenticationPrincipal CustomUserDetail currUser) {
        int promeh = 0;
        int Nds = 0;
        int Nds_i= 0;
        int Nds_1 = 0;
        int minus = 0;
        List<Accounts_ychetEntity> accounts_ychetEntitiList = accounts_ychetEntityRepository.findByYchetEntityId_AndRole(currUser.getId(), Operation.??????????????_??????_??????_????????????????_????????????, Operation.????????????_??????????????????);
        for (Accounts_ychetEntity accountsYchetEntity : accounts_ychetEntitiList) {
            Nds = Nds + accountsYchetEntity.getAccounts().getNalog();
        }
        List<Accounts_ychetEntity> accounts_ychetEntitiList1 = accounts_ychetEntityRepository.findByYchetEntityId(currUser.getId(), Operation.????????????????_????????????, NDS_oplata.????????????????????_??????????);
        for (Accounts_ychetEntity accountsYchetEntity1 : accounts_ychetEntitiList1) {
            Nds_1 = Nds_1 + accountsYchetEntity1.getAccounts().getNalog();
        }
        Nds_i = Nds_1 - Nds ;
        model.addAttribute("id",currUser.getId());
        model.addAttribute("Nds",Nds_i);
        return "nds_yplat";
    }

    public String NDS_yplat_test(){
        return "nds_yplat";
    }

    @GetMapping(path = "/NDS_yplat/reg")
    public String NDS_yplat_post(Model model,@AuthenticationPrincipal CustomUserDetail currUser) {
        int promeh = 0;
        int Nds = 0;
        int Nds_i= 0;
        int Nds_1 = 0;
        int minus = 0;
        List<Accounts_ychetEntity> accounts_ychetEntitiList = accounts_ychetEntityRepository.findByYchetEntityId_AndRole(currUser.getId(), Operation.??????????????_??????_??????_????????????????_????????????, Operation.????????????_??????????????????);
        for (Accounts_ychetEntity accountsYchetEntity : accounts_ychetEntitiList) {
            Nds = Nds + accountsYchetEntity.getAccounts().getNalog();
        }
        List<Accounts_ychetEntity> accounts_ychetEntitiList1 = accounts_ychetEntityRepository.findByYchetEntityId(currUser.getId(), Operation.????????????????_????????????, NDS_oplata.????????????????????_??????????);
        for (Accounts_ychetEntity accountsYchetEntity1 : accounts_ychetEntitiList1) {
            Nds_1 = Nds_1 + accountsYchetEntity1.getAccounts().getNalog();
        }
        Nds_i = Nds_1 - Nds ;
        Accounts_ychetEntity accountsYchetEntity1 = new Accounts_ychetEntity();
        List<Accounts_ychetEntity> accounts_ychetEntityList = accounts_ychetEntityRepository.findByYchetEntityId_(currUser.getId(), NDS_oplata.????????????????????_??????????,0);
        for (Accounts_ychetEntity accountsYchetEntity :  accounts_ychetEntityList) {
            promeh = promeh + accountsYchetEntity.getAccounts().getSymm();
            accountsYchetEntity1 = accountsYchetEntity;
        }
        accountsService.add_Accounts_by_Dov(accountsYchetEntity1,Nds_i);
        return "home";
    }



    @GetMapping(path = "/nds_vit")
    public String nds(Model model,@AuthenticationPrincipal CustomUserDetail currUser) {
        int Doxod = 0;
        int St_b = 0;
        int Nds = 0;
        Long id_ychet = 0L;
        List<Accounts_ychetEntity> accounts_ychetEntitie = null;
        List<Accounts_ychetEntity> accounts_ychetEntitiList = accounts_ychetEntityRepository.findByYchetEntityId_AndRole(currUser.getId(), Operation.??????????????_??????_??????_????????????????_????????????, Operation.????????????_??????????????????);
        for (Accounts_ychetEntity accountsYchetEntity : accounts_ychetEntitiList) {
                Doxod = Doxod + accountsYchetEntity.getAccounts().getSymm();
                Nds = Nds + accountsYchetEntity.getAccounts().getNalog();
        }
        St_b = Doxod + Nds;
        model.addAttribute("id", currUser.getId());
        model.addAttribute("byx",accounts_ychetEntitiList);
        model.addAttribute("vir", OrderStatus.????????????);
        model.addAttribute("Tw", 20);
        model.addAttribute("Ten", 10);
        model.addAttribute("Zero",0);
        model.addAttribute("doxod", St_b);
        model.addAttribute("St_b", Doxod);
        model.addAttribute("Nds", Nds);
        model.addAttribute("ras", OrderStatus.??????????????);
        model.addAttribute("Doxod_1", Order_balance.????????????_????_????????????????????);
        model.addAttribute("Doxod_2", Order_balance.????????????????????????????????_????????????);
        model.addAttribute("Doxod_3", Order_balance.????????_??????????????????????);
        model.addAttribute("Rasxod_1", Order_balance.??????????????);
        model.addAttribute("Rasxod_2", Order_balance.????????_??????????????);
        return "nds_vicet";
    }

    public String ndstest(){
        return "nds_vicet";
    }

    @GetMapping(path = "/nds_dobav")
    public String nds_dobav(Model model,@AuthenticationPrincipal CustomUserDetail currUser) {
        int Doxod = 0;
        int St_b = 0;
        int Nds = 0;
        Long id_ychet = 0L;
        List<Accounts_ychetEntity> accounts_ychetEntitie = null;
        List<Accounts_ychetEntity> accounts_ychetEntitiList = accounts_ychetEntityRepository.findByYchetEntityId(currUser.getId(), Operation.????????????????_????????????, NDS_oplata.????????????????????_??????????);
        for (Accounts_ychetEntity accountsYchetEntity : accounts_ychetEntitiList) {
            Doxod = Doxod + accountsYchetEntity.getAccounts().getSymm();
            Nds = Nds + accountsYchetEntity.getAccounts().getNalog();
        }
        St_b = Doxod - Nds;
        model.addAttribute("id", currUser.getId());
        model.addAttribute("byx",accounts_ychetEntitiList);
        model.addAttribute("vir", OrderStatus.????????????);
        model.addAttribute("Tw", 20);
        model.addAttribute("Ten", 10);
        model.addAttribute("Zero",0);
        model.addAttribute("doxod", Doxod);
        model.addAttribute("St_b", St_b);
        model.addAttribute("Nds", Nds);
        model.addAttribute("ras", OrderStatus.??????????????);
        model.addAttribute("Doxod_1", Order_balance.????????????_????_????????????????????);
        model.addAttribute("Doxod_2", Order_balance.????????????????????????????????_????????????);
        model.addAttribute("Doxod_3", Order_balance.????????_??????????????????????);
        model.addAttribute("Rasxod_1", Order_balance.??????????????);
        model.addAttribute("Rasxod_2", Order_balance.????????_??????????????);
        return "nds_dobav";
    }
    public String nds_dobav_test(){
        return "nds_dobav";
    }

    public String kydir() {
        return "kydir";
    }

    public String byx_pr() {
        return "byx_pr";
    }



    public String oper_pr_id() {
        return "oper_pr_id";
    }
    public String byx_pr_id() {
        return "byx_pr_id";
    }
}
