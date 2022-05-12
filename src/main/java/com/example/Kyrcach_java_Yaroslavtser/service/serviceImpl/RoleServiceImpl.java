package com.example.Kyrcach_java_Yaroslavtser.service.serviceImpl;


import com.example.Kyrcach_java_Yaroslavtser.Repositry.RoleEntityRepository;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.RoleDTO;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.UserDTO;
import com.example.Kyrcach_java_Yaroslavtser.model.RoleEntity;
import com.example.Kyrcach_java_Yaroslavtser.model.UserEntity;
import com.example.Kyrcach_java_Yaroslavtser.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleEntityRepository roleRepository;



    @Override
    public List<RoleDTO> findAllRoles() {
        List<RoleEntity> rolesEntity = roleRepository.findAll();
        return rolesEntity.stream().map(a -> new RoleDTO(a.getId(), a.getRole())).collect(Collectors.toList());
    }

    @Override
    public RoleEntity findByRole(String role) {
        return roleRepository.findByRole(role);
    }

    @Override
    public List<UserDTO> getUsersByRole(String role) {
        RoleEntity roleEntity = roleRepository.findByRole(role);
        List<UserEntity> users = roleEntity.getUsers();
        List<UserDTO> userDTOList = users.stream()
                .map(a -> new UserDTO(
                        a.getId(),
                        a.getLogin(),
                        a.getPassword(),
                        a.getFirstName(),
                        a.getLastName(),
                        a.getPhoneNumber(),
                        a.getRoleEntity().getRole()
                ))
                .collect(Collectors.toList());

        return userDTOList;
    }
}
