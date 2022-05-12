package com.example.Kyrcach_java_Yaroslavtser.controller;


import com.example.Kyrcach_java_Yaroslavtser.Repositry.AccountsEntityRepository;
import com.example.Kyrcach_java_Yaroslavtser.Repositry.Accounts_ychetEntityRepository;
import com.example.Kyrcach_java_Yaroslavtser.Repositry.UserEntityRepository;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.*;
import com.example.Kyrcach_java_Yaroslavtser.exception.UserNotFoundException;
import com.example.Kyrcach_java_Yaroslavtser.model.*;
import com.example.Kyrcach_java_Yaroslavtser.security.CustomUserDetail;
import com.example.Kyrcach_java_Yaroslavtser.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public String getAllUsersByRole(@RequestParam(value = "role", required = false) String role, Model model) {
        role = role == null ? "USER" : role;
        List<UserDTO> users = roleService.getUsersByRole(role);
        model.addAttribute("role", role);
        model.addAttribute("users", users.size() == 0 ? null : users);
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

    @GetMapping(path = {"/vibor_edit","/vibor_edit/{id}"})
    public String getVibor(Model model,
            @AuthenticationPrincipal CustomUserDetail currentUser) {

        DogovorDTO dogovorDTO = dogovor.findByUserId(currentUser.getId());
        if(dogovorDTO.getStatus() == DogovorStatus.Заключен) {
            model.addAttribute("id", currentUser.getId());
            return "vibor_edit";
        }

        return "ErorDogovor";

    }


    @GetMapping(path = {"/dogovor"})
    public String getDogovor(Model model,
                           @AuthenticationPrincipal CustomUserDetail currentUser) {

        model.addAttribute("id", currentUser.getId());
        model.addAttribute("dogovor", dogovor.findByUserId(currentUser.getId()));
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
        if(dogovorDTO.getStatus() == DogovorStatus.Заключен) {
            model.addAttribute("userEntity", currentUser);
            model.addAttribute("bux", new YchetDTO());
            return "Byx_ychetAdd";
        }
        return "ErorDogovor";

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
        if(dogovorDTO.getStatus() == DogovorStatus.Заключен) {
            model.addAttribute("userEntity", currentUser);
            model.addAttribute("bux", new YchetDTO());
            return "oper_ychetAdd";
        }
        return "ErorDogovor";

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
            model.addAttribute("balance", balanceService.findByUserId(currUser.getId()));
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
        if(dogovorDTO.getStatus() == DogovorStatus.Заключен) {
            model.addAttribute("id", currUser.getId());
            model.addAttribute("work", workService.findByUserId(currUser.getId()));
            return "addEditWork";
        }

        return "ErorDogovor";
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
            if(accountsEntity.getStatus() == OrderStatus.Выручка) {
            vir = vir + accountsEntity.getSymm();
            }
            if(accountsEntity.getStatus() == OrderStatus.Расходы) {
                vir = vir - accountsEntity.getSymm();
            }
        }
        model.addAttribute("vir",vir);
        return "virychka";
    }

    @GetMapping(path = "/byx_pr")
    public String byx_pr(Model model, @AuthenticationPrincipal CustomUserDetail currUser) {
        Long id_ychet = 0L;
        ArrayList<Accounts_ychetEntity> byx = new ArrayList<>();
        List<Accounts_ychetEntity> accounts_ychetEntitiList = accounts_ychetEntityRepository.findAll();
        for (Accounts_ychetEntity accountsYchetEntity : accounts_ychetEntitiList) {
            if (accountsYchetEntity.getYchetEntity().getStatus() == YchetStatus.Бухгалтерский) {
                if (accountsYchetEntity.getYchetEntity().getYchetStatus_admin() == YchetStatus_admin.Оформлен) {
                    if(accountsYchetEntity.getYchetEntity().getId() != id_ychet){
                        if(accountsYchetEntity.getYchetEntity().getUserEntity1().getId() == currUser.getId()){
                            id_ychet = accountsYchetEntity.getYchetEntity().getId();
                            byx.add(accountsYchetEntity);
                        }

                    }
                }

            }
        }
        model.addAttribute("byx",byx);
        return "byx_pr";
    }


    @GetMapping(path = "/byx_pr_id")
    public String byx_pr_id(Model model,Long id, Long id_user) {
        Long id_ychet = 0L;
        Accounts_ychetEntity accounts_ychetEntity1 = null;
        List<Accounts_ychetEntity> accounts_ychetEntitiList = accounts_ychetEntityRepository.findByYchetEntityId(id);
        for (Accounts_ychetEntity accountsYchetEntity : accounts_ychetEntitiList) {
            accounts_ychetEntity1 = accountsYchetEntity;
        }
        accounts_ychetEntitiList.remove(accounts_ychetEntity1);
        model.addAttribute("byx",accounts_ychetEntitiList);
        model.addAttribute("byx_1", accounts_ychetEntity1);
        return "byx_pr_id";
    }


    @PostMapping(path =  "/byx_pr")
    public String Byx_pr_post(@RequestParam Long id,@RequestParam Long id_user, Model model) {

        return byx_pr_id(model, id, id_user);
    }

    @GetMapping(path = "/oper_pr")
    public String oper_pr(Model model, @AuthenticationPrincipal CustomUserDetail currUser) {
        Long id_ychet = 0L;
        ArrayList<Accounts_ychetEntity> byx = new ArrayList<>();
        List<Accounts_ychetEntity> accounts_ychetEntitiList = accounts_ychetEntityRepository.findAll();
        for (Accounts_ychetEntity accountsYchetEntity : accounts_ychetEntitiList) {
            if (accountsYchetEntity.getYchetEntity().getStatus() == YchetStatus.Оперативный) {
                if (accountsYchetEntity.getYchetEntity().getYchetStatus_admin() == YchetStatus_admin.Оформлен) {
                    if(accountsYchetEntity.getYchetEntity().getId() != id_ychet){
                        if(accountsYchetEntity.getYchetEntity().getUserEntity1().getId() == currUser.getId()){
                            id_ychet = accountsYchetEntity.getYchetEntity().getId();
                            byx.add(accountsYchetEntity);
                        }

                    }
                }

            }
        }
        model.addAttribute("byx",byx);
        return "oper_pr";
    }


    @GetMapping(path = "/oper_pr_id")
    public String oper_pr_id(Model model,Long id, Long id_user) {
        Long id_ychet = 0L;
        Accounts_ychetEntity accounts_ychetEntity1 = null;
        List<Accounts_ychetEntity> accounts_ychetEntitiList = accounts_ychetEntityRepository.findByYchetEntityId(id);
        for (Accounts_ychetEntity accountsYchetEntity : accounts_ychetEntitiList) {
            accounts_ychetEntity1 = accountsYchetEntity;
        }
        accounts_ychetEntitiList.remove(accounts_ychetEntity1);
        model.addAttribute("byx",accounts_ychetEntitiList);
        model.addAttribute("byx_1", accounts_ychetEntity1);
        return "oper_pr_id";
    }


    @PostMapping(path =  "/oper_pr")
    public String oper_pr_post(@RequestParam Long id,@RequestParam Long id_user, Model model) {

        return oper_pr_id(model, id, id_user);
    }

    @GetMapping(path = "/KYDIR")
    public String kydir(Model model, @AuthenticationPrincipal CustomUserDetail currUser) {
        List<AccountsEntity> accountsEntityList = accountsEntityRepository.findAllByUserEntityId(currUser.getId());

        AccountsEntity accountsEntity1 = null;
        for (AccountsEntity accountsEntity :  accountsEntityList) {
            accountsEntity1 = accountsEntity;
        }
        accountsEntityList.remove(accountsEntity1);
        model.addAttribute("byx", accountsEntityList);
        model.addAttribute("byx_1", accountsEntity1);
        return "kydir";
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
