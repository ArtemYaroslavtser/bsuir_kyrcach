package com.example.Kyrcach_java_Yaroslavtser;

import com.example.Kyrcach_java_Yaroslavtser.controller.AccountsController;
import com.example.Kyrcach_java_Yaroslavtser.controller.AdminController;
import com.example.Kyrcach_java_Yaroslavtser.controller.UserController;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TestHtml {
    @Autowired
    private UserController userController;
    @Autowired
    private AccountsController accountsController;


    void CheckView_accounts_test() {
        Assert.isTrue(accountsController.View_accounts_test().equals("accounts"));
    }
    void checkUpdate_accounts_test() {
        Assert.isTrue(accountsController.Update_accounts_test().equals("DataTable"));
    }

    void checkAdd_accounts_test() {
        Assert.isTrue(accountsController.Add_accounts_test().equals("accountsAdd"));
    }
}
