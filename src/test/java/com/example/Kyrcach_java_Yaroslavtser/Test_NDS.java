package com.example.Kyrcach_java_Yaroslavtser;

import com.example.Kyrcach_java_Yaroslavtser.Repositry.*;
import com.example.Kyrcach_java_Yaroslavtser.controller.AccountsController;
import com.example.Kyrcach_java_Yaroslavtser.controller.UserController;
import com.example.Kyrcach_java_Yaroslavtser.model.*;
import com.example.Kyrcach_java_Yaroslavtser.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class Test_NDS {
    @Autowired
    private UserController userController;

    @Autowired
    private AccountsController accountsController;

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

    @Test
    void NDS_dobav() {
        Assert.isTrue(userController.nds_dobav_test().equals("nds_dobav"));
    }
    @Test
    void NDS_yplat() {
        Assert.isTrue(userController.NDS_yplat_test().equals("nds_yplat"));
    }
    @Test
    void ndstest() {
        Assert.isTrue(userController.ndstest().equals("nds_vicet"));
    }

    @Test
    void nds_vicet() {
        int Nds = 0;
        int Nds_i= 0;
        int Nds_1 = 0;
        int minus = 0;
        List<Accounts_ychetEntity> accounts_ychetEntitiList = accounts_ychetEntityRepository.findByYchetEntityId_AndRole(57L, Operation.Покупка_ТМЦ_для_оказания_услуги, Operation.Аренда_помещения);
        for (Accounts_ychetEntity accountsYchetEntity : accounts_ychetEntitiList) {
            Nds = Nds + accountsYchetEntity.getAccounts().getNalog();
        }
        List<Accounts_ychetEntity> accounts_ychetEntitiList1 = accounts_ychetEntityRepository.findByYchetEntityId(57L, Operation.Оказание_услуги, NDS_oplata.Подоходный_налог);
        for (Accounts_ychetEntity accountsYchetEntity1 : accounts_ychetEntitiList1) {
            Nds_1 = Nds_1 + accountsYchetEntity1.getAccounts().getNalog();
        }
        Nds_i = Nds_1 - Nds ;
        org.junit.Assert.assertNotNull(Nds_i);
    }

    @Test
    void nds_dobav() {
        int Doxod = 0;
        int St_b = 0;
        int Nds = 0;
        Long id_ychet = 0L;
        List<Accounts_ychetEntity> accounts_ychetEntitie = null;
        List<Accounts_ychetEntity> accounts_ychetEntitiList = accounts_ychetEntityRepository.findByYchetEntityId_AndRole(57L, Operation.Покупка_ТМЦ_для_оказания_услуги, Operation.Аренда_помещения);
        for (Accounts_ychetEntity accountsYchetEntity : accounts_ychetEntitiList) {
            Doxod = Doxod + accountsYchetEntity.getAccounts().getSymm();
            Nds = Nds + accountsYchetEntity.getAccounts().getNalog();
        }
        St_b = Doxod + Nds;
        org.junit.Assert.assertNotNull(St_b);
    }


    @Test
    void nds_nalog_dobav() {
        int promeh = 0;
        int Nds = 0;
        int minus = 0;
        int minus_2 = 0;
        List<Accounts_ychetEntity> accounts_ychetEntityList = accounts_ychetEntityRepository.findByYchetEntityId_1(57L,0);
        for (Accounts_ychetEntity accountsYchetEntity :  accounts_ychetEntityList) {
            if(accountsYchetEntity.getAccounts().getGoal() == Operation.Оказание_услуги){
                if(accountsYchetEntity.getAccounts().getNds_oplata() == NDS_oplata.Подоходный_налог){
                    promeh = promeh + accountsYchetEntity.getAccounts().getSymm();
                }
            }
            if(accountsYchetEntity.getAccounts().getGoal() == Operation.Покупка_ТМЦ_для_оказания_услуги || accountsYchetEntity.getAccounts().getGoal() == Operation.Выплата_ЗП || accountsYchetEntity.getAccounts().getGoal() == Operation.Аренда_помещения ) {
                minus = minus + accountsYchetEntity.getAccounts().getSymm() + accountsYchetEntity.getAccounts().getNalog();
            }
        }
        List<Accounts_ychetEntity> accountsYchetEntity = accounts_ychetEntityRepository.findByYchetEntityId_Vit(57L,Operation.Перечислен_НДС);
        for (Accounts_ychetEntity accountsYchetEntity1 :  accountsYchetEntity) {
            minus_2 = minus_2 + accountsYchetEntity1.getAccounts().getSymm();
        }

        Nds = (promeh - minus - minus_2)*16/100;
        Assert.isTrue(Nds == 44);
    }

    @Test
    void nds_nalog_YNS_False() {
        int promeh = 0;
        int Nds = 0;
        List<Accounts_ychetEntity> accounts_ychetEntityList = accounts_ychetEntityRepository.findByYchetEntityId_(59L, NDS_oplata.УСН, 0);
        for (Accounts_ychetEntity accountsYchetEntity :  accounts_ychetEntityList) {
            promeh = promeh + accountsYchetEntity.getAccounts().getSymm();
        }
        Nds = promeh * 6/100;
        Assert.isTrue(Nds == 0);
    }

    @Test
    void nds_nalog_YNS_TRUE() {
        int promeh = 0;
        int Nds = 0;
        List<Accounts_ychetEntity> accounts_ychetEntityList = accounts_ychetEntityRepository.findByYchetEntityId_user(59L);
        for (Accounts_ychetEntity accountsYchetEntity :  accounts_ychetEntityList) {
            promeh = promeh + accountsYchetEntity.getAccounts().getSymm();
        }
        Nds = promeh * 6/100;
        Assert.isTrue(Nds == 0);
    }


    @Test
    void nds_nalog_yplata() {
        int promeh = 0;
        int Nds = 0;
        int Nds_i= 0;
        int Nds_1 = 0;
        int minus = 0;
        List<Accounts_ychetEntity> accounts_ychetEntitiList = accounts_ychetEntityRepository.findByYchetEntityId_AndRole(59L, Operation.Покупка_ТМЦ_для_оказания_услуги, Operation.Аренда_помещения);
        for (Accounts_ychetEntity accountsYchetEntity : accounts_ychetEntitiList) {
            Nds = Nds + accountsYchetEntity.getAccounts().getNalog();
        }
        List<Accounts_ychetEntity> accounts_ychetEntitiList1 = accounts_ychetEntityRepository.findByYchetEntityId(59L, Operation.Оказание_услуги, NDS_oplata.Подоходный_налог);
        for (Accounts_ychetEntity accountsYchetEntity1 : accounts_ychetEntitiList1) {
            Nds_1 = Nds_1 + accountsYchetEntity1.getAccounts().getNalog();
        }
        Nds_i = Nds_1 - Nds ;
        Assert.isTrue(Nds == 53);
    }


    @Test
    void nds_nalog_dobav_FALSE() {
        int promeh = 0;
        int Nds = 0;
        int minus = 0;
        int minus_2 = 0;
        List<Accounts_ychetEntity> accounts_ychetEntityList = accounts_ychetEntityRepository.findByYchetEntityId_1(50L,0);
        for (Accounts_ychetEntity accountsYchetEntity :  accounts_ychetEntityList) {
            if(accountsYchetEntity.getAccounts().getGoal() == Operation.Оказание_услуги){
                if(accountsYchetEntity.getAccounts().getNds_oplata() == NDS_oplata.Подоходный_налог){
                    promeh = promeh + accountsYchetEntity.getAccounts().getSymm();
                }
            }
            if(accountsYchetEntity.getAccounts().getGoal() == Operation.Покупка_ТМЦ_для_оказания_услуги || accountsYchetEntity.getAccounts().getGoal() == Operation.Выплата_ЗП || accountsYchetEntity.getAccounts().getGoal() == Operation.Аренда_помещения ) {
                minus = minus + accountsYchetEntity.getAccounts().getSymm() + accountsYchetEntity.getAccounts().getNalog();
            }
        }
        List<Accounts_ychetEntity> accountsYchetEntity = accounts_ychetEntityRepository.findByYchetEntityId_Vit(50L,Operation.Перечислен_НДС);
        for (Accounts_ychetEntity accountsYchetEntity1 :  accountsYchetEntity) {
            minus_2 = minus_2 + accountsYchetEntity1.getAccounts().getSymm();
        }

        Nds = (promeh - minus - minus_2)*16/100;
        Assert.isTrue(Nds != 44);
    }
}
