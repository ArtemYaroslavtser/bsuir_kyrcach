package com.example.Kyrcach_java_Yaroslavtser;

import com.example.Kyrcach_java_Yaroslavtser.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.util.Assert;

@SpringBootTest
class ControllerTest {
    @Autowired
    private UserController userController;


    @Test
    void checkLogin() {
        Object model;
        Assert.isTrue(userController.kydir().equals("kydir"));
    }

    @Test
    void checkRedirectToRegistrationForm() {
        Assert.isTrue(userController.byx_pr().equals("byx_pr"));
    }

    @Test
    void checkRedirectToQuestionsPage() {
        Assert.isTrue(userController.oper_pr_id().equals("oper_pr_id"));
    }

    @Test
    void checkRedirectToAdminPage() {
        Assert.isTrue(userController.byx_pr_id().equals("byx_pr_id"));
    }


}
