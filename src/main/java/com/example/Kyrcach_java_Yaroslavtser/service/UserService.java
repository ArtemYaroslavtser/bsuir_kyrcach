package com.example.Kyrcach_java_Yaroslavtser.service;

import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.UserDTO;
import com.example.Kyrcach_java_Yaroslavtser.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    boolean save(UserDTO userDTO, String role);

    List<UserDTO> getUsersByRole(String role);

    void deleteUserById(Long id);

    UserDTO findUserById(Long id);

    void update(UserDTO user);


    Optional<UserEntity> findByLogin(String login);
}
