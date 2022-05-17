package com.example.Kyrcach_java_Yaroslavtser.service.serviceImpl;

import java.io.IOException;

import com.example.Kyrcach_java_Yaroslavtser.model.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.example.Kyrcach_java_Yaroslavtser.DataTable.*;
import com.example.Kyrcach_java_Yaroslavtser.Repositry.AccountsEntityRepository;
import com.example.Kyrcach_java_Yaroslavtser.Repositry.UserEntityRepository;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.AccountsDTO;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.UserDTO;
import com.example.Kyrcach_java_Yaroslavtser.exception.UserNotFoundException;
import com.example.Kyrcach_java_Yaroslavtser.security.CustomUserDetail;
import com.example.Kyrcach_java_Yaroslavtser.service.AccountsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static java.sql.Types.NULL;


@Service
public class AccountsServiceImpl implements AccountsService {

    @Autowired
    private UserEntityRepository userRepository;

    @Autowired
    private AccountsEntityRepository accountsEntityRepository;

/*
    @Override
    public List<AccountsDTO> getOrdersByUserId(Long id) {
        List<AccountsEntity> userEntityList = accountsEntityRepository.findAllByUserEntityId(id);
        List<AccountsDTO> accountsDTO = userEntityList.stream()
                .map(a -> new AccountsDTO(
                        a.getId(),
                        a.getName_operat(),
                        a.getGoal(),
                        a.getSymm(),
                        new UserDTO(
                                a.getUserEntity().getId()
                        ),
                        a.getStatus()
                ))
                .collect(Collectors.toList());

        return accountsDTO;
    }
*/
    @Override
    public void add_Accounts_by_Summ(AccountsDTO accountsDTO, CustomUserDetail currUser) {

        AccountsEntity accountsEntity = new AccountsEntity();
        accountsEntity.setName_operat(accountsDTO.getName_operat());
        accountsEntity.setGoal(accountsDTO.getGoal());
        accountsEntity.setNds_oplata(accountsDTO.getNds_oplata());
        accountsEntity.setNDS_YES(0);
        if(accountsDTO.getGoal() == Operation.Оказание_услуги) {
            accountsEntity.setGoal(accountsDTO.getGoal());
            accountsEntity.setStatus(OrderStatus.Доходы);
            accountsEntity.setOrder_balance(Order_balance.Доходы_от_реализации);
            accountsEntity.setNds(NDS.По_реализации);
        }
        if(accountsDTO.getGoal() == Operation.Покупка_ТМЦ_для_оказания_услуги) {
            AccountsEntity accountsEntity1 = new AccountsEntity();
            accountsEntity.setStatus(OrderStatus.Расходы);
            accountsEntity.setOrder_balance(Order_balance.Расходы);
            accountsEntity.setNds(NDS.к_вычету);
            accountsEntity.setSymm(accountsDTO.getSymm());
            accountsEntity.setUserEntity(userRepository.findByLogin(currUser.getLogin()));
            accountsEntity.setDate(accountsDTO.getDate());
            accountsEntity.setPronds(accountsDTO.getPronds());
            accountsEntity.setNalog((accountsDTO.getSymm() * accountsDTO.getPronds())/(100+ accountsDTO.getPronds()));
            accountsEntity.setSymm(accountsDTO.getSymm() - accountsEntity.getNalog());

            accountsEntity1.setNds_oplata(accountsDTO.getNds_oplata());
            accountsEntity1.setNDS_YES(0);
            accountsEntity1.setName_operat(accountsDTO.getName_operat());
            accountsEntity1.setGoal(Operation.НДС_К_ВЫЧЕТУ);
            accountsEntity1.setStatus(OrderStatus.Расходы);
            accountsEntity1.setOrder_balance(Order_balance.Расходы);
            accountsEntity1.setNds(NDS.к_вычету);
            accountsEntity1.setSymm(accountsEntity.getNalog());
            accountsEntity1.setUserEntity(userRepository.findByLogin(currUser.getLogin()));
            accountsEntity1.setDate(accountsDTO.getDate());
            accountsEntity1.setPronds(NULL);
            accountsEntity1.setNalog(NULL);
            accountsEntityRepository.save(accountsEntity);
            accountsEntityRepository.save(accountsEntity1);
            return;
        }

        if(accountsDTO.getGoal() == Operation.Аренда_помещения) {
            AccountsEntity accountsEntity1 = new AccountsEntity();
            accountsEntity.setStatus(OrderStatus.Расходы);
            accountsEntity.setOrder_balance(Order_balance.Расходы);
            accountsEntity.setNds(NDS.к_вычету);
            accountsEntity.setSymm(accountsDTO.getSymm());
            accountsEntity.setUserEntity(userRepository.findByLogin(currUser.getLogin()));
            accountsEntity.setDate(accountsDTO.getDate());
            accountsEntity.setPronds(accountsDTO.getPronds());
            accountsEntity.setNalog((accountsDTO.getSymm() * accountsDTO.getPronds())/(100+ accountsDTO.getPronds()));
            accountsEntity.setSymm(accountsDTO.getSymm() - accountsEntity.getNalog());

            accountsEntity1.setNds_oplata(accountsDTO.getNds_oplata());
            accountsEntity1.setNDS_YES(0);
            accountsEntity1.setName_operat(accountsDTO.getName_operat());
            accountsEntity1.setGoal(Operation.НДС_К_ВЫЧЕТУ);
            accountsEntity1.setStatus(OrderStatus.Расходы);
            accountsEntity1.setOrder_balance(Order_balance.Расходы);
            accountsEntity1.setNds(NDS.к_вычету);
            accountsEntity1.setSymm(accountsEntity.getNalog());
            accountsEntity1.setUserEntity(userRepository.findByLogin(currUser.getLogin()));
            accountsEntity1.setDate(accountsDTO.getDate());
            accountsEntity1.setPronds(NULL);
            accountsEntity1.setNalog(NULL);
            accountsEntityRepository.save(accountsEntity);
            accountsEntityRepository.save(accountsEntity1);
            return;
        }

        if(accountsDTO.getGoal() == Operation.Выплата_ЗП) {
            accountsEntity.setStatus(OrderStatus.Расходы);
            accountsEntity.setOrder_balance(Order_balance.Расходы);
            accountsEntity.setNds(NDS.Подоходный_налог);
            accountsEntity.setSymm(accountsDTO.getSymm());
            accountsEntity.setUserEntity(userRepository.findByLogin(currUser.getLogin()));
            accountsEntity.setDate(accountsDTO.getDate());
            accountsEntity.setPronds(0);
            accountsEntity.setNalog((accountsDTO.getSymm() * 0)/100);
            accountsEntity.setSymm(accountsDTO.getSymm());

            accountsEntityRepository.save(accountsEntity);
            return;
        }
        accountsEntity.setSymm(accountsDTO.getSymm());
        accountsEntity.setUserEntity(userRepository.findByLogin(currUser.getLogin()));
        accountsEntity.setDate(accountsDTO.getDate());
        accountsEntity.setPronds(accountsDTO.getPronds());
        accountsEntity.setNalog((accountsDTO.getSymm() * accountsDTO.getPronds())/(100 + accountsDTO.getPronds()));
        accountsEntityRepository.save(accountsEntity);
    }

    @Override
    public void add_Accounts_by_YSH(Accounts_ychetEntity accountsYchetEntity, int NDS_) {
        AccountsEntity accountsEntity = new AccountsEntity();
        accountsEntity.setName_operat("");
        accountsEntity.setNDS_YES(1);
        accountsEntity.setNds_oplata(NDS_oplata.Нет_типа_уплаты_налогов);
        accountsEntity.setGoal(Operation.Налог_по_УСН);
        accountsEntity.setStatus(OrderStatus.Расходы);
        accountsEntity.setOrder_balance(Order_balance.Расходы);
        accountsEntity.setNds(NDS.Подоходный_налог);
        accountsEntity.setSymm(NDS_);
        accountsEntity.setUserEntity(accountsYchetEntity.getYchetEntity().getUserEntity1());
        accountsEntity.setDate(accountsYchetEntity.getAccounts().getDate());
        accountsEntity.setPronds(0);
        accountsEntity.setNalog(0);
        accountsEntityRepository.save(accountsEntity);
    }

    @Override
    public void add_Accounts_by_POD(Accounts_ychetEntity accountsYchetEntity, int NDS_) {
        AccountsEntity accountsEntity = new AccountsEntity();
        accountsEntity.setName_operat("");
        accountsEntity.setNDS_YES(1);
        accountsEntity.setNds_oplata(NDS_oplata.Нет_типа_уплаты_налогов);
        accountsEntity.setGoal(Operation.Подоходный_налог);
        accountsEntity.setStatus(OrderStatus.Расходы);
        accountsEntity.setOrder_balance(Order_balance.Расходы);
        accountsEntity.setNds(NDS.Подоходный_налог);
        accountsEntity.setSymm(NDS_);
        accountsEntity.setUserEntity(accountsYchetEntity.getYchetEntity().getUserEntity1());
        accountsEntity.setDate(accountsYchetEntity.getAccounts().getDate());
        accountsEntity.setPronds(0);
        accountsEntity.setNalog(0);
        accountsEntityRepository.save(accountsEntity);
    }

    @Override
    public void add_Accounts_by_Dov(Accounts_ychetEntity accountsYchetEntity, int NDS_) {
        AccountsEntity accountsEntity = new AccountsEntity();
        accountsEntity.setName_operat("");
        accountsEntity.setNDS_YES(1);
        accountsEntity.setNds_oplata(NDS_oplata.Нет_типа_уплаты_налогов);
        accountsEntity.setGoal(Operation.Перечислен_НДС);
        accountsEntity.setStatus(OrderStatus.Расходы);
        accountsEntity.setOrder_balance(Order_balance.Расходы);
        accountsEntity.setNds(NDS.Подоходный_налог);
        accountsEntity.setSymm(NDS_);
        accountsEntity.setUserEntity(accountsYchetEntity.getYchetEntity().getUserEntity1());
        accountsEntity.setDate(accountsYchetEntity.getAccounts().getDate());
        accountsEntity.setPronds(0);
        accountsEntity.setNalog(0);
        accountsEntityRepository.save(accountsEntity);
    }

    @Override
    public void add_Accounts_by_Purchase(AccountsDTO accountsDTO, CustomUserDetail currUser) {

        AccountsEntity accountsEntity = new AccountsEntity();
        accountsEntity.setName_operat(accountsDTO.getName_operat());
        accountsEntity.setGoal(accountsDTO.getGoal());
        accountsEntity.setSymm(accountsDTO.getSymm());
        accountsEntity.setUserEntity(userRepository.findByLogin(currUser.getLogin()));
        accountsEntity.setStatus(OrderStatus.Расходы);
        accountsEntity.setOrder_balance(accountsDTO.getBalance());
        accountsEntity.setDate(accountsDTO.getDate());
        accountsEntity.setNalog(0);
        accountsEntityRepository.save(accountsEntity);
    }



    @Override
    public List<AccountsDTO> getOrdersByUserIdAndStatus(Long id, OrderStatus status) {
        return null;
    }



}
