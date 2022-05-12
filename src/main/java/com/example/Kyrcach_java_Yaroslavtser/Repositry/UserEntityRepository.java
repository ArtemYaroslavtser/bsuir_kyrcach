package com.example.Kyrcach_java_Yaroslavtser.Repositry;


import com.example.Kyrcach_java_Yaroslavtser.model.DogovorStatus;
import com.example.Kyrcach_java_Yaroslavtser.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT u.id FROM UserEntity u WHERE u.login = ?1")
    Long getIdUserByLogin(String login);


    @Query(value = "SELECT u.id FROM UserEntity u WHERE u.phoneNumber = ?1")
    Long getIdUserByPhoneNumber(String phoneNumber);

    List<UserEntity> findAllByRoleEntity_Role(String role);


    @Query(value = "SELECT u FROM UserEntity u WHERE u.dogovorEntity.status = ?1")
    List<UserEntity> findAllByDogovorEntity(DogovorStatus dogovorStatus);
    void deleteById(Long id);

    Optional<UserEntity> findById(Long id);

    UserEntity findByLogin(String login);

}
