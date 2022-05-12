package com.example.Kyrcach_java_Yaroslavtser.service;

import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.UserDTO;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.WorkDTO;
import com.example.Kyrcach_java_Yaroslavtser.model.UserEntity;
import com.example.Kyrcach_java_Yaroslavtser.model.Work_ipEntity;
import com.example.Kyrcach_java_Yaroslavtser.security.CustomUserDetail;

import java.util.List;
import java.util.Optional;

public interface WorkService {

    boolean save_work(WorkDTO workDTO, CustomUserDetail currUser);

    WorkDTO findByUserId(Long id);

    void update(WorkDTO work);
}
