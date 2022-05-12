package com.example.Kyrcach_java_Yaroslavtser.Repositry;

import com.example.Kyrcach_java_Yaroslavtser.model.YchetEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface YchetEntityRepository extends JpaRepository<YchetEntity, Long> {


    @Query(value = "SELECT u FROM AccountsEntity u WHERE u.userEntity.id = ?1")
    List<YchetEntity> findAllByUserEntityId(Long id);

    Optional<YchetEntity> findById(@NotNull Long ID);

}