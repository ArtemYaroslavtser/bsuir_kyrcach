package com.example.Kyrcach_java_Yaroslavtser.Repositry;

import com.example.Kyrcach_java_Yaroslavtser.model.AccountsEntity;
import com.example.Kyrcach_java_Yaroslavtser.model.Accounts_ychetEntity;
import com.example.Kyrcach_java_Yaroslavtser.model.Operation;
import com.example.Kyrcach_java_Yaroslavtser.model.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Accounts_ychetEntityRepository extends JpaRepository<Accounts_ychetEntity, Long> {

    Page<Accounts_ychetEntity> findByIdContaining(Long id, Pageable pageable);

    List<Accounts_ychetEntity> findAll();

    @Query(value = "SELECT u.accounts FROM Accounts_ychetEntity u WHERE u.id = ?1")
    List<AccountsEntity> findAccounts_entityByAccounts(Long id);


    @Query(value = "SELECT u FROM Accounts_ychetEntity u WHERE u.ychetEntity.id = ?1")
    List<Accounts_ychetEntity> findByYchetEntityId(Long id);

    @Query(value = "SELECT u FROM Accounts_ychetEntity u WHERE u.ychetEntity.id = ?1 AND u.accounts.goal = ?2 OR u.accounts.goal = ?3")
    List<Accounts_ychetEntity> findByYchetEntityId_AndRole(Long id, Operation operation, Operation operation1);

}
