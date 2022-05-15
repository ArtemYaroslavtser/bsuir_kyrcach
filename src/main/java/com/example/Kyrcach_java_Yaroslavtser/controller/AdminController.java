package com.example.Kyrcach_java_Yaroslavtser.controller;


import com.example.Kyrcach_java_Yaroslavtser.Repositry.Accounts_ychetEntityRepository;
import com.example.Kyrcach_java_Yaroslavtser.Repositry.UserEntityRepository;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.ApiError;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.DogovorDTO;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.YchetDTO;
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
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;
    private BalanceService balanceService;
    private WorkService workService;

    private DogovorService dogovor;

    private Accounts_ychetService accounts_ychetService;

    private YchetService ychetService;

    @Autowired
    Accounts_ychetEntityRepository accounts_ychetEntityRepository;

    @Autowired
    UserEntityRepository userEntityRepository;

    public AdminController(UserService userService, RoleService roleService, BalanceService balanceService, WorkService workService, DogovorService dogovorService, Accounts_ychetService accounts_ychetService, YchetService ychetService) {
        this.userService = userService;
        this.roleService = roleService;
        this.balanceService = balanceService;
        this.workService = workService;
        this.dogovor = dogovorService;
        this.accounts_ychetService = accounts_ychetService;
        this.ychetService = ychetService;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(path = "/byx")
    public String byx_admin(Model model) {
        int byx_balance = 0;
        int virychka = 0;
        Long id_ychet = 0L;
        ArrayList<Accounts_ychetEntity> byx = new ArrayList<>();
        List<Accounts_ychetEntity> accounts_ychetEntitiList = accounts_ychetEntityRepository.findAll();
        for (Accounts_ychetEntity accountsYchetEntity : accounts_ychetEntitiList) {
            if (accountsYchetEntity.getYchetEntity().getStatus() == YchetStatus.Бухгалтерский) {
                if (accountsYchetEntity.getYchetEntity().getYchetStatus_admin() == YchetStatus_admin.Не_оформлен) {
                    if(accountsYchetEntity.getYchetEntity().getId() != id_ychet){
                        id_ychet = accountsYchetEntity.getYchetEntity().getId();
                        byx.add(accountsYchetEntity);
                    }
                }

            }
        }
        model.addAttribute("byx",byx);
        return "adminTable";
    }

    @PostMapping(path =  "/byx")
    public String Byx_post(@RequestParam Long id, Model model) {

        accounts_ychetService.change_status(id);

        return byx_admin(model);
    }
    public String byx_admin_test(){
        return "adminTable";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(path = "/oper")
    public String oper_admin(Model model) {
        int byx_balance = 0;
        int virychka = 0;
        Long id_ychet = 0L;
        ArrayList<Accounts_ychetEntity> byx = new ArrayList<>();
        List<Accounts_ychetEntity> accounts_ychetEntitiList = accounts_ychetEntityRepository.findAll();
        for (Accounts_ychetEntity accountsYchetEntity : accounts_ychetEntitiList) {
            if (accountsYchetEntity.getYchetEntity().getStatus() == YchetStatus.Оперативный) {
                if (accountsYchetEntity.getYchetEntity().getYchetStatus_admin() == YchetStatus_admin.Не_оформлен) {
                    if(accountsYchetEntity.getYchetEntity().getId() != id_ychet){
                        id_ychet = accountsYchetEntity.getYchetEntity().getId();
                        byx.add(accountsYchetEntity);
                    }
                }

            }
        }
        model.addAttribute("byx",byx);
        return "Oper_admin";
    }

    public String oper_admin_test() {
        return "Oper_admin";
    }
    @PostMapping(path =  "/oper")
    public String Oper_post(@RequestParam Long id, Model model) {

        accounts_ychetService.change_status(id);

        return oper_admin(model);
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(path = "/prib")
    public String vir_admin(Model model,  @AuthenticationPrincipal CustomUserDetail currUser) {
        int dogovor = 0;
        int vir = 0;
        List<UserEntity> userEntityList = userEntityRepository.findAllByDogovorEntity(DogovorStatus.Заключен);
        for (UserEntity userEntity : userEntityList) {
            dogovor++;
            vir = vir + 500;
        }
        model.addAttribute("id", currUser.getId());
        model.addAttribute("dogovor",dogovor);
        model.addAttribute("vir",vir);
        return "adminPrib";
    }
    public String vir_admin_test(){
        return "adminPrib";
    }

    @PostMapping(path =  "/polzovateli")
    public String delete_pol(@RequestParam Long id, Model model) {

        userService.deleteUserById(id);

        return polzovateli(model);
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(path = "/polzovateli")
    public String polzovateli(Model model) {
        List<UserEntity> userEntityList = userEntityRepository.findAllByRoleEntity_Role("ROLE_USER");
        model.addAttribute("user",userEntityList);
        return "polzovateli";
    }

    public String polzovateli_test() {
        return "polzovateli";
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(path = "/admins")
    public String admins(Model model) {
        List<UserEntity> userEntityList = userEntityRepository.findAllByRoleEntity_Role("ROLE_ADMIN");
        model.addAttribute("user",userEntityList);
        return "admins";
    }

    public String admins_test() {
        return "admins";
    }
}
