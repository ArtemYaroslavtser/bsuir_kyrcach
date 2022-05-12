package com.example.Kyrcach_java_Yaroslavtser.service;

import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.BalanceDTO;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.WorkDTO;
import com.example.Kyrcach_java_Yaroslavtser.security.CustomUserDetail;

public interface BalanceService {

    boolean save_balance(BalanceDTO balanceDTO, CustomUserDetail currUser);

    BalanceDTO findByUserId(Long id);

    void update(BalanceDTO work);

    void minus(BalanceDTO balanceDTO);
}
