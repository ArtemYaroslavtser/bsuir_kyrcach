package com.example.Kyrcach_java_Yaroslavtser;

import com.example.Kyrcach_java_Yaroslavtser.Repositry.RoleEntityRepository;
import com.example.Kyrcach_java_Yaroslavtser.Repositry.UserEntityRepository;
import com.example.Kyrcach_java_Yaroslavtser.controller.AccountsController;
import com.example.Kyrcach_java_Yaroslavtser.controller.AdminController;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.UserDTO;
import com.example.Kyrcach_java_Yaroslavtser.controller.UserController;
import com.example.Kyrcach_java_Yaroslavtser.model.*;
import com.example.Kyrcach_java_Yaroslavtser.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.util.Assert;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class ControllerTest {
    @Autowired
    private UserController userController;

    @Autowired
    private AccountsController accountsController;

    @Autowired
    private AdminController adminController;
    private UserEntity userEntity;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleEntityRepository roleEntityRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Test
    void checkLogin() {
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


    @Test
    public void testSearchingUserForAdminShouldFind() {
        Optional<UserEntity> doc = userEntityRepository.findById(0L);
        org.junit.Assert.assertFalse(doc.isPresent());
    }
    @Test
    public void testSearchingUserForLoginAndPassword() {
        Optional<UserEntity> doc = userEntityRepository.findByLoginAndPassword("login", "nbvjatq_1");
        org.junit.Assert.assertFalse(doc.isPresent());
    }

    @BeforeEach
    void setUp(){

        String login = "testLogin";
        String password = "parol123";
        String name = "name";
        String phone = "375256468568";
        String role = "ROLE_USER";

        userEntity = new UserEntity();
        userEntity.setLogin(login);
        userEntity.setPassword(password);
        userEntity.setFirstName(name);
        userEntity.setLastName(name);
        userEntity.setPhoneNumber(phone);
        userEntity.setRoleEntity(roleEntityRepository.findByRole(role));

        Date date = new Date();
        userEntity.setWork_ipEntity(new Work_ipEntity("NOTHING","NOTHING",1));
        userEntity.setBalanceEntity(new BalanceEntity(0,0,0));
        userEntity.setDogovorEntity(new DogovorEntity("Бухгалтерская помощь ИП",500, date , DogovorStatus.Незаключен ));
    }

    @Test
    public void save_user(){
        Long id = 3L;
        String login = "testLogin";
        String password = "parol123";
        String name = "name";
        String phone = "375256468568";
        String role = "ROLE_USER";

        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(login);
        userEntity.setPassword(password);
        userEntity.setFirstName(name);
        userEntity.setLastName(name);
        userEntity.setPhoneNumber(phone);
        userEntity.setRoleEntity(roleEntityRepository.findByRole(role));

        Date date = new Date();
        userEntity.setWork_ipEntity(new Work_ipEntity("NOTHING","NOTHING",1));
        userEntity.setBalanceEntity(new BalanceEntity(0,0,0));
        userEntity.setDogovorEntity(new DogovorEntity("Бухгалтерская помощь ИП",500, date , DogovorStatus.Незаключен ));
        userEntityRepository.save(userEntity);
        org.junit.Assert.assertNotNull(userEntity);
    }


    @Test
    public void test_save_user(){
        Optional<UserEntity> doc = userEntityRepository.findByLoginAndPassword("testLogin", "parol123");
        org.junit.Assert.assertFalse(doc.isPresent());
    }

    @Test
    void testsave_user_2()throws IllegalArgumentException{
        if(!userEntity.getLogin().equals("testLogin")) throw new IllegalArgumentException();
        if(!userEntity.getPassword().equals("parol123")) throw new IllegalArgumentException();
        if(!userEntity.getPhoneNumber().equals("375256468568")) throw new IllegalArgumentException();
        if(!userEntity.getFirstName().equals("name")) throw new IllegalArgumentException();
        if(!userEntity.getLastName().equals("name")) throw new IllegalArgumentException();
    }

}
