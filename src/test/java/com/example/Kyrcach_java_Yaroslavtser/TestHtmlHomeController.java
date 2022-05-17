package com.example.Kyrcach_java_Yaroslavtser;

import com.example.Kyrcach_java_Yaroslavtser.controller.AdminController;
import com.example.Kyrcach_java_Yaroslavtser.controller.HomeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class TestHtmlHomeController {
    @Autowired
    HomeController homeController;
    @Test
    void getSignUpView_test() {
        Assert.isTrue(homeController.getSignUpView_test().equals("reg"));
    }

    @Test
    void CheckLogin() {
        Assert.isTrue(homeController.login().equals("login"));
    }

}
