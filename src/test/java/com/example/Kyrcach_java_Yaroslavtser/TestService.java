package com.example.Kyrcach_java_Yaroslavtser;

import com.example.Kyrcach_java_Yaroslavtser.Repositry.*;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.BalanceDTO;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.DogovorDTO;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.UserDTO;
import com.example.Kyrcach_java_Yaroslavtser.model.*;
import com.example.Kyrcach_java_Yaroslavtser.service.*;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import javax.validation.constraints.AssertTrue;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Transactional
public class TestService {
    @Autowired
    BalanceService balanceService;
    @Autowired
    DogovorService dogovorService;
    @Autowired
    WorkService workService;
    @Autowired
    YchetService ychetService;
    @Autowired
    AccountsService accountsService;



    BalanceEntity balanceEntity;

    DogovorEntity dogovorEntity;

    Work_ipEntity work_ipEntity;

    YchetEntity ychetEntity;

    AccountsEntity accountsEntity;

    UserEntity userEntity;

    @Autowired
    BalanceEntityRepository balanceEntityRepository;

    @Autowired
    DogovorEntityRepository dogovorEntityRepository;

    @Autowired
    WorkEntityRepository workEntityRepository;

    @Autowired
    YchetEntityRepository ychetEntityRepository;

    @Autowired
    AccountsEntityRepository accountsEntityRepository;

    @Autowired
    Accounts_ychetEntityRepository accounts_ychetEntityRepository;

    @BeforeEach
    void setUp(){

        Date date = new Date();
        balanceEntity = new BalanceEntity(0,0,0);
        dogovorEntity = new DogovorEntity("name_dogovor",50,date,DogovorStatus.Заключен);
        work_ipEntity = new Work_ipEntity("name_work","adress",5);
        ychetEntity = new YchetEntity("work",YchetStatus.Оперативный,YchetStatus_admin.Оформлен,date,date,userEntity);
    }


    @Test
    void udpate_balance(){
        UserDTO userDTO = new UserDTO();
        BalanceDTO balanceDTO = new BalanceDTO(1L,0,0,0,userDTO);
        balanceService.update(balanceDTO);
    }

    @Test
    void minus_balance(){
        UserDTO userDTO = new UserDTO();
        BalanceDTO balanceDTO = new BalanceDTO(1L,0,0,0,userDTO);
        balanceService.minus(balanceDTO);
    }

    @Test
    void test_find_balance(){
        BalanceDTO balanceDTO = balanceService.findByUserId(10L);
        Assert.assertNotNull(balanceDTO);
    }

    @Test
    void find_dogovor(){
        DogovorDTO dogovorDTO = dogovorService.findByUserId(10L);
        Assert.assertNotNull(dogovorDTO);
    }

    @Test
    void dogovor_update(){
        Date date = new Date();
        UserDTO userDTO = new UserDTO();
        DogovorDTO dogovorDTO = new DogovorDTO(1L,"name_dogovor",50,date,userDTO,DogovorStatus.Заключен);
        dogovorService.update(dogovorDTO);
    }


    @Test
    void check_dogovor_rep(){
        DogovorEntity dogovorEntity1 = dogovorEntityRepository.findByOwnerId(10L);
        Assert.assertNotNull(dogovorEntity1);
    }

    @Test
    void check_dogovor_rep_List(){
        List<DogovorEntity> dogovorEntityList = dogovorEntityRepository.findAll();
        Assert.assertNotNull(dogovorEntityList);
    }

    @Test
    void check_work_rep(){
        Work_ipEntity work_ipEntity1 = workEntityRepository.findByOwnerId(10L);
        Assert.assertNotNull(work_ipEntity1);

    }

    @Test
    void check_work_name_work(){
        String name_work = workEntityRepository.getname_workByOwnerId(10L);
        Assert.assertNotNull(name_work);
    }

    @Test
    void check_ychet(){
        YchetEntity ychetEntity1 = ychetEntityRepository.getById(10L);
        Assert.assertNotNull(ychetEntity1);
    }

    @Test
    void check_ychet_1(){
       List<YchetEntity> ychetEntityList = ychetEntityRepository.findAll();
       Assert.assertNotNull(ychetEntityList);
    }

    @Test
    void account_ychet(){
        Date date = new Date();
       List<AccountsEntity> accountsEntities = accountsEntityRepository.findAllByDate(date, 10L);
       Assert.assertNotNull(accountsEntities);
    }

    @Test
    void account_ychet__1(){
        List<AccountsEntity> accountsEntities = accountsEntityRepository.findAllByUserEntityId(14L);
        Assert.assertNotNull(accountsEntities);
    }

    @Test
    void account_ychet_2(){
        Date date = new Date();
        List<AccountsEntity> accountsEntities = accountsEntityRepository.findAllByDateFirstandSecond(date,date,10L);
        Assert.assertNotNull(accountsEntities);
    }

    @Test
    void Kydir_1(){
        List<Accounts_ychetEntity> accountsEntities = accounts_ychetEntityRepository.findAll();
        Assert.assertNotNull(accountsEntities);
    }


    @Test
    void Kydir__1(){
        List<Accounts_ychetEntity> accountsEntities = accounts_ychetEntityRepository.findAll();
        Assert.assertNotNull(accountsEntities);
    }
}
