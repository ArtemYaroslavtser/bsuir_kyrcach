package com.example.Kyrcach_java_Yaroslavtser.Repositry;

import com.example.Kyrcach_java_Yaroslavtser.model.UserEntity;
import com.example.Kyrcach_java_Yaroslavtser.model.Work_ipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface WorkEntityRepository extends JpaRepository<Work_ipEntity, Long> {

    Work_ipEntity findByOwnerId(Long id);

    @Query(value = "SELECT u.name_work FROM Work_ipEntity u WHERE u.owner.id = ?1")
    String getname_workByOwnerId(Long i);
}
