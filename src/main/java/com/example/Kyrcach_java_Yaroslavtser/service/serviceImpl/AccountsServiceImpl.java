package com.example.Kyrcach_java_Yaroslavtser.service.serviceImpl;

import java.io.IOException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.example.Kyrcach_java_Yaroslavtser.DataTable.*;
import com.example.Kyrcach_java_Yaroslavtser.Repositry.AccountsEntityRepository;
import com.example.Kyrcach_java_Yaroslavtser.Repositry.UserEntityRepository;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.AccountsDTO;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.UserDTO;
import com.example.Kyrcach_java_Yaroslavtser.exception.UserNotFoundException;
import com.example.Kyrcach_java_Yaroslavtser.model.AccountsEntity;
import com.example.Kyrcach_java_Yaroslavtser.model.OrderStatus;
import com.example.Kyrcach_java_Yaroslavtser.model.Work_ipEntity;
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
        accountsEntity.setSymm(accountsDTO.getSymm());
        accountsEntity.setUserEntity(userRepository.findByLogin(currUser.getLogin()));
        accountsEntity.setStatus(OrderStatus.Выручка);
        accountsEntity.setDate(accountsDTO.getDate());
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
        accountsEntity.setDate(accountsDTO.getDate());
        accountsEntityRepository.save(accountsEntity);
    }



    @Override
    public List<AccountsDTO> getOrdersByUserIdAndStatus(Long id, OrderStatus status) {
        return null;
    }



}
