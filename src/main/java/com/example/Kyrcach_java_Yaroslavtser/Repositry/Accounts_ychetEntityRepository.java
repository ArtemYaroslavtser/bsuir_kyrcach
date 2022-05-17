package com.example.Kyrcach_java_Yaroslavtser.Repositry;

import com.example.Kyrcach_java_Yaroslavtser.model.*;
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
    @Query(value = "SELECT u FROM Accounts_ychetEntity u WHERE u.ychetEntity.userEntity1.id = ?1")
    List<Accounts_ychetEntity> findByYchetEntityId_user(Long id);
    @Query(value = "SELECT u FROM Accounts_ychetEntity u WHERE u.ychetEntity.id = ?1 AND u.accounts.goal = ?2")
    List<Accounts_ychetEntity> findByYchetEntityId_Vit(Long id, Operation operation1);

    @Query(value = "SELECT u FROM Accounts_ychetEntity u WHERE u.ychetEntity.userEntity1.id = ?1 AND u.accounts.goal = ?2 OR u.accounts.goal = ?3")
    List<Accounts_ychetEntity> findByYchetEntityId_AndRole(Long id, Operation operation, Operation operation1);

    @Query(value = "SELECT u FROM Accounts_ychetEntity u WHERE u.ychetEntity.userEntity1.id = ?1 AND u.accounts.nds_oplata = ?2 AND u.accounts.NDS_YES = ?3")
    List<Accounts_ychetEntity> findByYchetEntityId_(Long id, NDS_oplata nds_oplata, int NDS_YES);
    @Query(value = "SELECT u FROM Accounts_ychetEntity u WHERE u.ychetEntity.userEntity1.id = ?1 AND u.accounts.goal = ?2 AND u.accounts.nds_oplata = ?3")
    List<Accounts_ychetEntity> findByYchetEntityId(Long id, Operation operation, NDS_oplata nds_oplata);
    @Query(value = "SELECT u FROM Accounts_ychetEntity u WHERE u.ychetEntity.userEntity1.id = ?1 AND u.accounts.NDS_YES = ?2")
    List<Accounts_ychetEntity> findByYchetEntityId_1(Long id, int NDS_YES);


}
