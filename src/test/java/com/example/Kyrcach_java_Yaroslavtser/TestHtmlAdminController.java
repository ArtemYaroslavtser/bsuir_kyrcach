package com.example.Kyrcach_java_Yaroslavtser;

import com.example.Kyrcach_java_Yaroslavtser.controller.AdminController;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TestHtmlAdminController {

    @Autowired
    AdminController adminController;

    void Checkbyx_admin_test() {
        Assert.isTrue(adminController.byx_admin_test().equals("adminTable"));
    }

    void Checkoper_admin_test() {
        Assert.isTrue(adminController.oper_admin_test().equals("Oper_admin"));
    }
    void Checkvir_admin_test() {
        Assert.isTrue(adminController.vir_admin_test().equals("adminPrib"));
    }
    void Checkpolzovateli_test() {
        Assert.isTrue(adminController.polzovateli_test().equals("polzovateli"));
    }
    void Checkadmins_test() {
        Assert.isTrue(adminController.admins_test().equals("admins"));
    }
}
