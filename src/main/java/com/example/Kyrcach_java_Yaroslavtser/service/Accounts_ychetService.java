package com.example.Kyrcach_java_Yaroslavtser.service;


import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.AccountsDTO;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.YchetDTO;
import com.example.Kyrcach_java_Yaroslavtser.model.Accounts_ychetEntity;
import com.example.Kyrcach_java_Yaroslavtser.model.YchetEntity;
import com.example.Kyrcach_java_Yaroslavtser.security.CustomUserDetail;
import org.springframework.data.domain.Page;

public interface Accounts_ychetService {

    void add_Oper(YchetDTO ychetDTO, YchetEntity ychetEntity);

    void add_Byx(YchetDTO ychetDTO, YchetEntity ychetEntity);

    void change_status(Long id);

}
