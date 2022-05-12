package com.example.Kyrcach_java_Yaroslavtser.service.serviceImpl;

import com.example.Kyrcach_java_Yaroslavtser.Repositry.DogovorEntityRepository;
import com.example.Kyrcach_java_Yaroslavtser.Repositry.UserEntityRepository;
import com.example.Kyrcach_java_Yaroslavtser.Repositry.WorkEntityRepository;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.DogovorDTO;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.UserDTO;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.WorkDTO;
import com.example.Kyrcach_java_Yaroslavtser.exception.UserNotFoundException;
import com.example.Kyrcach_java_Yaroslavtser.model.DogovorEntity;
import com.example.Kyrcach_java_Yaroslavtser.model.DogovorStatus;
import com.example.Kyrcach_java_Yaroslavtser.model.Work_ipEntity;
import com.example.Kyrcach_java_Yaroslavtser.service.DogovorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DogovorServiceImpl implements DogovorService {

    @Autowired
    private UserEntityRepository userRepository;
    @Autowired
    private DogovorEntityRepository dogovorEntityRepository;

    @Override
    public DogovorDTO findByUserId(Long id) {
        return mapWalletDTO(dogovorEntityRepository.findByOwnerId(id));
    }


    private DogovorDTO mapWalletDTO(DogovorEntity work_ipEntity) {
        return new DogovorDTO(work_ipEntity.getId(), work_ipEntity.getName_dogovor(),work_ipEntity.getSumm(), work_ipEntity.getDate(), new UserDTO(work_ipEntity.getOwner().getId()), work_ipEntity.getStatus());
    }

    @Override
    public void update(DogovorDTO dogovor) {

        Optional<DogovorEntity> editUserEntity = dogovorEntityRepository.findById(dogovor.getId_dogovor());
        if (editUserEntity.isPresent()) {
            editUserEntity.get().setStatus(DogovorStatus.Заключен);

            dogovorEntityRepository.save(editUserEntity.get());
        } else {
            throw new UserNotFoundException("User with id=" + dogovor.getId_dogovor() + " not found");
        }

    }
}
