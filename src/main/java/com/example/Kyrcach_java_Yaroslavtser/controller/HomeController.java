package com.example.Kyrcach_java_Yaroslavtser.controller;

import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.ApiError;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.UserDTO;
import com.example.Kyrcach_java_Yaroslavtser.security.CustomUserDetail;
import com.example.Kyrcach_java_Yaroslavtser.service.RoleService;
import com.example.Kyrcach_java_Yaroslavtser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class HomeController {


    private UserService userService;

    private RoleService roleService;

    public HomeController(UserService userService,
                          RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping()
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/home")
    public String getHomeView(Model model, @AuthenticationPrincipal CustomUserDetail currentUser) {
        model.addAttribute("id", currentUser.getId());
        return "home";
    }

    public String getSignUpView_test() {
        return "reg";
    }

    @GetMapping(value = "/reg")
    public String getSignUpView(Model model) {
        model.addAttribute("user", new UserDTO());
        return "reg";
    }

    @PostMapping(path = "/reg")
    public String registrationClient(@Valid @ModelAttribute("user") UserDTO client,
                                     BindingResult result,
                                     Model model) {
        if (result.hasErrors()) {
            ApiError apiError = new ApiError();
            String message = "";
            for (FieldError str : result.getFieldErrors()) {
                message += str.getDefaultMessage();
                apiError.setMessage(message);
            }
            model.addAttribute("user", client);
            model.addAttribute("apiError", apiError);
            return "reg";
        }

        userService.save(client, "ROLE_USER");

        return "redirect:/";
    }


    @GetMapping("/403")
    public String error403() {
        return "statuscode/403";
    }

}
