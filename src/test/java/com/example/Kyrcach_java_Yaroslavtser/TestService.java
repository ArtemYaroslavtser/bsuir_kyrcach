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
    UserEntityRepository userEntityRepository;

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
        BalanceDTO balanceDTO = balanceService.findByUserId(55L);
        Assert.assertNotNull(balanceDTO);
    }

    @Test
    void find_dogovor(){
        DogovorDTO dogovorDTO = dogovorService.findByUserId(55L);
        Assert.assertNotNull(dogovorDTO);
    }


    @Test
    void check_dogovor_rep(){
        DogovorEntity dogovorEntity1 = dogovorEntityRepository.findByOwnerId(55L);
        Assert.assertNotNull(dogovorEntity1);
    }

    @Test
    void check_dogovor_rep_List(){
        List<DogovorEntity> dogovorEntityList = dogovorEntityRepository.findAll();
        Assert.assertNotNull(dogovorEntityList);
    }

    @Test
    void check_work_rep(){
        Work_ipEntity work_ipEntity1 = workEntityRepository.findByOwnerId(55L);
        Assert.assertNotNull(work_ipEntity1);

    }

    @Test
    void check_work_name_work(){
        String name_work = workEntityRepository.getname_workByOwnerId(55L);
        Assert.assertNotNull(name_work);
    }

    @Test
    void check_ychet(){
        YchetEntity ychetEntity1 = ychetEntityRepository.getById(46L);
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
       List<AccountsEntity> accountsEntities = accountsEntityRepository.findAllByDate(date, 55L);
       Assert.assertNotNull(accountsEntities);
    }

    @Test
    void account_ychet__1(){
        List<AccountsEntity> accountsEntities = accountsEntityRepository.findAllByUserEntityId(55L);
        Assert.assertNotNull(accountsEntities);
    }

    @Test
    void account_ychet_2(){
        Date date = new Date();
        List<AccountsEntity> accountsEntities = accountsEntityRepository.findAllByDateFirstandSecond(date,date,55L);
        Assert.assertNotNull(accountsEntities);
    }

    @Test
    void polzovatel(){
        List<UserEntity> userEntityList = userEntityRepository.findAll();
        Assertions.assertNotNull(userEntityList);
    }

    @Test
    void admin(){
        List<UserEntity> userEntityList = userEntityRepository.findAllByRoleEntity_Role("ROLE_ADMIN");
        Assertions.assertNotNull(userEntityList);
    }

    @Test
    void vir_pol(){
        int summ = 0;
        List<Accounts_ychetEntity> accounts_ychetEntitiList = accounts_ychetEntityRepository.findByYchetEntityId(57L);
        for (Accounts_ychetEntity accountsYchetEntity : accounts_ychetEntitiList) {
            summ = summ + accountsYchetEntity.getAccounts().getSymm();
        }
        Assertions.assertNotNull(summ);
    }

    @Test
    void vir_byx(){
        int summ = 0;
        List<DogovorEntity> dogovorEntityList = dogovorEntityRepository.findAll();
        for (DogovorEntity accountsYchetEntity : dogovorEntityList) {
            summ = summ + accountsYchetEntity.getSumm();
        }
        Assertions.assertNotNull(summ);
    }
    @Test
    void Kydir_1(){
        List<Accounts_ychetEntity> accountsEntities = accounts_ychetEntityRepository.findAll();
        Assert.assertNotNull(accountsEntities);
    }

}
