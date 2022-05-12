package com.example.Kyrcach_java_Yaroslavtser.service.serviceImpl;

import com.example.Kyrcach_java_Yaroslavtser.Repositry.AccountsEntityRepository;
import com.example.Kyrcach_java_Yaroslavtser.Repositry.UserEntityRepository;
import com.example.Kyrcach_java_Yaroslavtser.Repositry.WorkEntityRepository;
import com.example.Kyrcach_java_Yaroslavtser.Repositry.YchetEntityRepository;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.AccountsDTO;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.YchetDTO;
import com.example.Kyrcach_java_Yaroslavtser.model.*;
import com.example.Kyrcach_java_Yaroslavtser.security.CustomUserDetail;
import com.example.Kyrcach_java_Yaroslavtser.service.YchetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YchetServiceImpl implements YchetService {

    @Autowired
    private UserEntityRepository userRepository;

    @Autowired
    private AccountsEntityRepository accountsEntityRepository;

    @Autowired
    private WorkEntityRepository workEntityRepository;

    @Autowired
    private YchetEntityRepository ychetEntityRepository;

    @Override
    public YchetEntity add_Ychet_by_Byx(YchetDTO ychetDTO, CustomUserDetail currUser) {

        YchetEntity ychetEntity = new YchetEntity();
        ychetEntity.setWork(workEntityRepository.getname_workByOwnerId(currUser.getId()));
        ychetEntity.setUserEntity1(userRepository.findByLogin(currUser.getLogin()));
        ychetEntity.setStatus(YchetStatus.Бухгалтерский);
        ychetEntity.setDateFirst(ychetDTO.getDateFirst());
        ychetEntity.setDateSecond(ychetDTO.getDateSecond());
        ychetEntity.setYchetStatus_admin(YchetStatus_admin.Не_оформлен);
        ychetEntityRepository.save(ychetEntity);
        return ychetEntity;
    }

    @Override
    public YchetEntity add_Ychet_by_oper(YchetDTO ychetDTO, CustomUserDetail currUser) {
        YchetEntity ychetEntity = new YchetEntity();
        ychetEntity.setWork(workEntityRepository.getname_workByOwnerId(currUser.getId()));
        ychetEntity.setUserEntity1(userRepository.findByLogin(currUser.getLogin()));
        ychetEntity.setStatus(YchetStatus.Оперативный);
        ychetEntity.setDateFirst(ychetDTO.getDateFirst());
        ychetEntity.setDateSecond(ychetDTO.getDateSecond());
        ychetEntity.setYchetStatus_admin(YchetStatus_admin.Не_оформлен);
        ychetEntityRepository.save(ychetEntity);
        return ychetEntity;
    }
}
