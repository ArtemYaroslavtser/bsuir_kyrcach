package com.example.Kyrcach_java_Yaroslavtser.service;


import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.RoleDTO;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.UserDTO;
import com.example.Kyrcach_java_Yaroslavtser.model.RoleEntity;

import java.util.List;

public interface RoleService {


    List<RoleDTO> findAllRoles();

    RoleEntity findByRole(String role);

    List<UserDTO> getUsersByRole(String role);
}
