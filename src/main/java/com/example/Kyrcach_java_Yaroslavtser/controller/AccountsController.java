package com.example.Kyrcach_java_Yaroslavtser.controller;


import com.example.Kyrcach_java_Yaroslavtser.DataTable.Page;
import com.example.Kyrcach_java_Yaroslavtser.DataTable.PageArray;
import com.example.Kyrcach_java_Yaroslavtser.DataTable.PagingRequest;
import com.example.Kyrcach_java_Yaroslavtser.Repositry.AccountsEntityRepository;
import com.example.Kyrcach_java_Yaroslavtser.Repositry.DogovorEntityRepository;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.*;
import com.example.Kyrcach_java_Yaroslavtser.model.AccountsEntity;
import com.example.Kyrcach_java_Yaroslavtser.model.DogovorStatus;
import com.example.Kyrcach_java_Yaroslavtser.model.OrderStatus;
import com.example.Kyrcach_java_Yaroslavtser.security.CustomUserDetail;
import com.example.Kyrcach_java_Yaroslavtser.service.AccountsService;
import com.example.Kyrcach_java_Yaroslavtser.service.DogovorService;
import com.example.Kyrcach_java_Yaroslavtser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = "/accou")
public class AccountsController {

    private UserService userService;

    private AccountsService accountsService;

    private AccountsEntityRepository accountsEntityRepository;

    private DogovorService dogovor;

    public AccountsController(UserService userService, AccountsService accountsService,AccountsEntityRepository accountsEntityRepository, DogovorService dogovor) {
        this.userService = userService;
        this.accountsService = accountsService;
        this.accountsEntityRepository = accountsEntityRepository;
        this.dogovor = dogovor;
    }

    @GetMapping(path = "/")
    public String View_accounts(Model model) {
        return "accounts";
    }

    public String View_accounts_test() {
        return "accounts";
    }


    @GetMapping(path = "/update")
    public String Update_accounts(Model model, @AuthenticationPrincipal CustomUserDetail currUser) {
        DogovorDTO dogovorDTO = dogovor.findByUserId(currUser.getId());
        if(dogovorDTO.getStatus() == DogovorStatus.Заключен) {
            model.addAttribute("id", currUser.getId());
            model.addAttribute("account", accountsEntityRepository.findAllByUserEntityId(currUser.getId()));
            return "DataTable";
        }
        return "ErorDogovor";
    }

    public String Update_accounts_test(){
        return "DataTable";
    }
    @GetMapping(path = "/add")
    public String Add_accounts(Model model, @AuthenticationPrincipal CustomUserDetail currUser) {
        DogovorDTO dogovorDTO = dogovor.findByUserId(currUser.getId());
        if(dogovorDTO.getStatus() == DogovorStatus.Заключен) {
            String SertionValue = "";
            model.addAttribute("id", currUser.getId());
            model.addAttribute("userEntity", currUser);
            model.addAttribute("vibor_1", SertionValue);
            model.addAttribute("account", new AccountsDTO());
            return "accountsAdd";
        }
        return "ErorDogovor";
    }

    public String Add_accounts_test(){
        return "accountsAdd";
    }


    @PostMapping(path = "/add")
    public String Add_accounts_post(Model model,
                                    @Valid @ModelAttribute("account") AccountsDTO account,
                                    @AuthenticationPrincipal CustomUserDetail currUser,
                                    BindingResult result) {

        if (result.hasErrors()) {
            ApiError apiError = new ApiError();
            String message = "";
            for (FieldError str : result.getFieldErrors()) {
                message += str.getDefaultMessage();
                apiError.setMessage(message);
            }
            model.addAttribute("account", account);
            model.addAttribute("apiError", apiError);
            return "accountsAdd";
        }

        if(account.getStatus() == OrderStatus.Выручка) {
            accountsService.add_Accounts_by_Summ(account, currUser);
        }else if (account.getStatus() == OrderStatus.Расходы){
            accountsService.add_Accounts_by_Purchase(account, currUser);
        }
        return "redirect:/home";

    }


}
