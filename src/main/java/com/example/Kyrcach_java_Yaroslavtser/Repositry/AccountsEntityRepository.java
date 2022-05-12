package com.example.Kyrcach_java_Yaroslavtser.Repositry;

import com.example.Kyrcach_java_Yaroslavtser.model.AccountsEntity;
import com.example.Kyrcach_java_Yaroslavtser.model.OrderStatus;
import com.example.Kyrcach_java_Yaroslavtser.model.RoleEntity;
import com.example.Kyrcach_java_Yaroslavtser.model.UserEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AccountsEntityRepository extends JpaRepository<AccountsEntity, Long> {


    @Query(value = "SELECT u FROM AccountsEntity u WHERE u.userEntity.id = ?1")
    List<AccountsEntity> findAllByUserEntityId(Long id);

    @Query(value = "SELECT u FROM AccountsEntity u WHERE u.date = ?1")
    List<AccountsEntity> findAllByDate(Date date);

    @Query(value = "SELECT u FROM AccountsEntity u WHERE u.date >= ?1 AND u.date <= ?2")
    List<AccountsEntity> findAllByDateFirstandSecond(Date dateFirst, Date Second);

    List<AccountsEntity> findAllByUserEntityIdAndStatus(Long id, OrderStatus status, Sort sort);

}
