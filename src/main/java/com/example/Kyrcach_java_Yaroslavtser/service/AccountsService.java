package com.example.Kyrcach_java_Yaroslavtser.service;

import com.example.Kyrcach_java_Yaroslavtser.DataTable.Page;
import com.example.Kyrcach_java_Yaroslavtser.DataTable.PageArray;
import com.example.Kyrcach_java_Yaroslavtser.DataTable.PagingRequest;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.AccountsDTO;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.WorkDTO;
import com.example.Kyrcach_java_Yaroslavtser.model.OrderStatus;
import com.example.Kyrcach_java_Yaroslavtser.security.CustomUserDetail;

import java.util.List;

public interface AccountsService {


  //  List<AccountsDTO> getOrdersByUserId(Long id);
    void add_Accounts_by_Summ(AccountsDTO accountsDTO, CustomUserDetail currUser);

    void add_Accounts_by_Purchase(AccountsDTO accountsDTO, CustomUserDetail currUser);

    List<AccountsDTO> getOrdersByUserIdAndStatus(Long id, OrderStatus status);



}
