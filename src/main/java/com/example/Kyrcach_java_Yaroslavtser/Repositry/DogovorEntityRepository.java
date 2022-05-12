package com.example.Kyrcach_java_Yaroslavtser.Repositry;

import com.example.Kyrcach_java_Yaroslavtser.model.BalanceEntity;
import com.example.Kyrcach_java_Yaroslavtser.model.DogovorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogovorEntityRepository extends JpaRepository<DogovorEntity, Long> {

    DogovorEntity findByOwnerId(Long id);
}
