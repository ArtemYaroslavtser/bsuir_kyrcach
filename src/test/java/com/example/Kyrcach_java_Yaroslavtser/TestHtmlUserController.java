package com.example.Kyrcach_java_Yaroslavtser;

import com.example.Kyrcach_java_Yaroslavtser.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class TestHtmlUserController {
    @Autowired
    private UserController userController;

    @Test
    void checkkydir() {
        Assert.isTrue(userController.kydir().equals("kydir"));
    }

    @Test
    void checkbyx_pr() {
        Assert.isTrue(userController.byx_pr().equals("byx_pr"));
    }

    @Test
    void checkoper_pr_id() {
        Assert.isTrue(userController.oper_pr_id().equals("oper_pr_id"));
    }

    @Test
    void getAddOrEditUserView() {
        Assert.isTrue(userController.getAddOrEditUserView().equals("addEditUser"));
    }

    @Test
    void getDogovor() {
        Assert.isTrue(userController. getDogovor().equals("addEditDogovor"));
    }

    @Test
    void Byx_add() {
        Assert.isTrue(userController.Byx_add().equals("Byx_ychetAdd"));
    }
    @Test
    void Oper_add() {
        Assert.isTrue(userController.Oper_add().equals("oper_ychetAdd"));
    }
    @Test
    void Add_orChange_Balance_get() {
        Assert.isTrue(userController.Add_orChange_Balance_get().equals("addEditBalance"));
    }
    @Test
    void Add_orChange_Work_get() {
        Assert.isTrue(userController.Add_orChange_Work_get().equals("addEditWork"));
    }
    @Test
    void vir_test() {
        Assert.isTrue(userController.vir_test().equals("virychka"));
    }
}
