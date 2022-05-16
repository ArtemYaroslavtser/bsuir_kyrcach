package com.example.Kyrcach_java_Yaroslavtser.service.serviceImpl;

import com.example.Kyrcach_java_Yaroslavtser.Repositry.*;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.YchetDTO;
import com.example.Kyrcach_java_Yaroslavtser.model.*;
import com.example.Kyrcach_java_Yaroslavtser.security.CustomUserDetail;
import com.example.Kyrcach_java_Yaroslavtser.service.Accounts_ychetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class Accounts_ychetServiceImpl implements Accounts_ychetService {


    @Autowired
    private AccountsEntityRepository accountsEntityRepository;

    @Autowired
    private Accounts_ychetEntityRepository accounts_ychetEntityRepository;
    @Autowired
    private YchetEntityRepository ychetEntityRepository;

    @Override
    public void add_Oper(YchetDTO ychetDTO, YchetEntity ychetEntity) {

        int viruchka = 0;
        int byx_balance = 0;
        List<AccountsEntity> accountsEntityList1 = accountsEntityRepository.findAllByDateFirstandSecond(ychetDTO.getDateFirst(), ychetDTO.getDateSecond(),ychetEntity.getUserEntity1().getId());

        List<AccountsEntity> accountsEntityList = accountsEntityRepository.findAllByDateFirstandSecond(ychetDTO.getDateFirst(), ychetDTO.getDateSecond(),ychetEntity.getUserEntity1().getId());

        for (AccountsEntity accountsEntity : accountsEntityList1) {
            if(accountsEntity.getStatus() == OrderStatus.Доходы) {
                viruchka = viruchka + accountsEntity.getSymm();
            }
            if(accountsEntity.getStatus() == OrderStatus.Расходы){
                viruchka = viruchka - accountsEntity.getSymm();
            }
            byx_balance = byx_balance + accountsEntity.getSymm();
        }


        for (AccountsEntity accountsEntity : accountsEntityList) {
            Accounts_ychetEntity accounts_ychetEntity = new Accounts_ychetEntity();
            accounts_ychetEntity.setAccounts(accountsEntity);
            accounts_ychetEntity.setYchetEntity(ychetEntity);
            accounts_ychetEntity.setViruchka(viruchka);
            accounts_ychetEntity.setByx_balance(byx_balance);
            accounts_ychetEntityRepository.save(accounts_ychetEntity);
        }


    }

    @Override
    public void add_Byx(YchetDTO ychetDTO, YchetEntity ychetEntity) {

        int viruchka = 0;
        int byx_balance = 0;
        System.out.println(ychetDTO.getDateSecond());
        Date date = new Date(122,0,1);
        System.out.println(date);
        List<AccountsEntity> accountsEntityList1 = accountsEntityRepository.findAllByDateFirstandSecond(date, ychetDTO.getDateSecond(),ychetEntity.getUserEntity1().getId());

        for (AccountsEntity accountsEntity : accountsEntityList1) {
            if(accountsEntity.getStatus() == OrderStatus.Доходы) {
                viruchka = viruchka + accountsEntity.getSymm();
            }
            if(accountsEntity.getStatus() == OrderStatus.Расходы){
                viruchka = viruchka - accountsEntity.getSymm();
            }
            byx_balance = byx_balance + accountsEntity.getSymm();
        }

        List<AccountsEntity> accountsEntityList = accountsEntityRepository.findAllByDateFirstandSecond(date, ychetDTO.getDateSecond(),ychetEntity.getUserEntity1().getId());
        for (AccountsEntity accountsEntity : accountsEntityList) {
            Accounts_ychetEntity accounts_ychetEntity = new Accounts_ychetEntity();
            accounts_ychetEntity.setAccounts(accountsEntity);
            accounts_ychetEntity.setYchetEntity(ychetEntity);
            accounts_ychetEntity.setViruchka(viruchka);
            accounts_ychetEntity.setByx_balance(byx_balance);
            accounts_ychetEntityRepository.save(accounts_ychetEntity);
        }
    }

    @Override
    public void change_status(Long id) {
      List<Accounts_ychetEntity> accounts_ychetEntityList = accounts_ychetEntityRepository.findByYchetEntityId(id);

        for (Accounts_ychetEntity accounts_ychetEntity : accounts_ychetEntityList) {
            accounts_ychetEntity.getYchetEntity().setYchetStatus_admin(YchetStatus_admin.Оформлен);
            accounts_ychetEntityRepository.save(accounts_ychetEntity);
        }

    }
}
