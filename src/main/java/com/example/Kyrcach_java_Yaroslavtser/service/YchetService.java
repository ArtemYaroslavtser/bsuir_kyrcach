package com.example.Kyrcach_java_Yaroslavtser.service;

import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.AccountsDTO;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.YchetDTO;
import com.example.Kyrcach_java_Yaroslavtser.model.YchetEntity;
import com.example.Kyrcach_java_Yaroslavtser.security.CustomUserDetail;

public interface YchetService {

    YchetEntity add_Ychet_by_Byx(YchetDTO ychetDTO, CustomUserDetail currUser);

    YchetEntity add_Ychet_by_oper(YchetDTO ychetDTO, CustomUserDetail currUser);
}
