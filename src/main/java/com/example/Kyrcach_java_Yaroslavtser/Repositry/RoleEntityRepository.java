package com.example.Kyrcach_java_Yaroslavtser.Repositry;


import com.example.Kyrcach_java_Yaroslavtser.model.RoleEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByRole(String role);

    List<RoleEntity> findAll();


}
