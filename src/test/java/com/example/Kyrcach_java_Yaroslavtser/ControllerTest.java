package com.example.Kyrcach_java_Yaroslavtser;

import com.example.Kyrcach_java_Yaroslavtser.Repositry.UserEntityRepository;
import com.example.Kyrcach_java_Yaroslavtser.controller.UserController;
import com.example.Kyrcach_java_Yaroslavtser.model.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.util.Assert;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class ControllerTest {
    @Autowired
    private UserController userController;

    @Autowired
    private UserEntity user;

    @Autowired
    private UserEntityRepository userEntityRepository;

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


    @Test
    public void testSearchingUserForAdminShouldFind() {
        Optional<UserEntity> doc = userEntityRepository.findById(0L);
        org.junit.Assert.assertTrue(doc.isPresent());
    }
    @Test
    public void testSearchingUserForLoginAndPassword() {
        Optional<UserEntity> doc = userEntityRepository.findByLoginAndPassword("login", "nbvjatq_1");
        org.junit.Assert.assertTrue(doc.isPresent());
    }
    /*
    @Test
    public void testSearchingUserForAdminShouldNotFindWhenNotExists() {
        Optional<UserEntity> doc = service.findByUsername("random");
        org.junit.Assert.assertTrue(doc.isPresent());
    }

    @Test
    public void testSearchingUserForAdminByEmailShouldFindWhenExists() {
        Optional<UserEntity> doc = service.findByEmail("dui.suspendisse@outlook.net");
        org.junit.Assert.assertTrue(doc.isPresent());
    }




    @Test
    public void testSearchingUserForAdminByUserIdShouldNotFindWhenNotExists() {
        Optional<UserEntity> doc = service.findById(30000L);
        org.junit.Assert.assertTrue(doc.isPresent());
    }

    @Test
    public void testSearchingUserForAdminByUserIdShouldFindWhenExists() {
        Optional<UserEntity> doc = service.findById(1L);
        org.junit.Assert.assertTrue(doc.isPresent());
    }

    @Test
    public void testActivateUserWhenCodeNotExists() {
        boolean isActivated = service.activateUser(UUID.randomUUID().toString());
        org.junit.Assert.assertTrue(doc.isPresent());
    }

    @Test
    public void testUserBlockUserWhenUserExists() {
        service.changeBlockStatusByUsername("Francis Morin");
        Optional<User> docAfter = service.findByUsername("Francis Morin");
        Assert.assertFalse(docAfter.get().isBlocked());
    }

    @Test
    public void testUserBlockUserWhenUserNotExists() {
        service.changeBlockStatusByUsername("NotExists");
        Optional<User> docAfter = service.findByUsername("NotExists");
        org.junit.Assert.assertTrue(doc.isPresent());
    }

    @Test(expected = EmailNotFoundException.class)
    public void testSendRecoveryLinkOnEmailUserWhenEmailNotExists() {
        org.junit.Assert.assertTrue(doc.isPresent());
    }

    @Test
    public void testSendRecoveryLinkOnEmailUserWhenEmailExists() {
        org.junit.Assert.assertTrue(doc.isPresent());
    }

    @Test
    public void testChangeUserRoleWhenUsernameExists() {
        boolean isChanged = service.changeRole("Honorato Dunn", "ADMIN");
        org.junit.Assert.assertTrue(doc.isPresent());
    }

    @Test
    public void testChangeUserRoleWhenUsernameNotExists() {
        boolean isChanged = service.changeRole("NotExists", "ADMIN");
        org.junit.Assert.assertTrue(doc.isPresent());
    }

    @Test
    public void testChangePasswordWhenUserNotExists() {
        boolean isChanged = service.changePassword(0L, "newPass", new BCryptPasswordEncoder());
        org.junit.Assert.assertTrue(doc.isPresent());
    }

     */
}
