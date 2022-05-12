package com.example.Kyrcach_java_Yaroslavtser.service.serviceImpl;

import com.example.Kyrcach_java_Yaroslavtser.Repositry.UserEntityRepository;
import com.example.Kyrcach_java_Yaroslavtser.Repositry.WorkEntityRepository;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.UserDTO;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.WorkDTO;
import com.example.Kyrcach_java_Yaroslavtser.exception.UserNotFoundException;
import com.example.Kyrcach_java_Yaroslavtser.model.UserEntity;
import com.example.Kyrcach_java_Yaroslavtser.model.Work_ipEntity;
import com.example.Kyrcach_java_Yaroslavtser.security.CustomUserDetail;
import com.example.Kyrcach_java_Yaroslavtser.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class WorkServiceImpl1 implements WorkService {

    private final Double DEFAULT_SUM = 0D;

    @Autowired
    private UserEntityRepository userRepository;
    @Autowired
    private WorkEntityRepository workEntityRepository;


    @Transactional
    @Override
      public boolean save_work(WorkDTO workDTO, CustomUserDetail currUser){

        Work_ipEntity work_ipEntity = workEntityRepository.findByOwnerId(currUser.getId());

        work_ipEntity.setName_work(workDTO.getName_work());
        work_ipEntity.setAdress(workDTO.getAdress());
        work_ipEntity.setNumber_registration(workDTO.getNumber_registration());

        workEntityRepository.save(work_ipEntity);

        return true;
    }

    @Override
    public WorkDTO findByUserId(Long id) {
        return mapWalletDTO(workEntityRepository.findByOwnerId(id));
    }


    private WorkDTO mapWalletDTO(Work_ipEntity work_ipEntity) {
        return new WorkDTO(work_ipEntity.getId(), work_ipEntity.getName_work(),work_ipEntity.getAdress(), work_ipEntity.getNumber_registration(), new UserDTO(work_ipEntity.getUserEntity().getId()));
    }

    @Transactional
    @Override
    public void update(WorkDTO workDTO) {

            Optional<Work_ipEntity> editUserEntity = workEntityRepository.findById(workDTO.getId_work());
            if (editUserEntity.isPresent()) {

            editUserEntity.get().setName_work(workDTO.getName_work());
            editUserEntity.get().setAdress(workDTO.getAdress());
            editUserEntity.get().setNumber_registration(workDTO.getNumber_registration());

            workEntityRepository.save(editUserEntity.get());
        } else {
            throw new UserNotFoundException("User with id=" + workDTO.getId_work() + " not found");
        }

    }
}
