package com.example.Kyrcach_java_Yaroslavtser.Repositry;

import com.example.Kyrcach_java_Yaroslavtser.model.BalanceEntity;
import com.example.Kyrcach_java_Yaroslavtser.model.Work_ipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BalanceEntityRepository extends JpaRepository<BalanceEntity, Long> {

    BalanceEntity findByOwnerId(Long id);
}
