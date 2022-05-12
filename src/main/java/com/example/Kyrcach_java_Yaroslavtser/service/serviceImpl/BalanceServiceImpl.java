package com.example.Kyrcach_java_Yaroslavtser.service.serviceImpl;

import com.example.Kyrcach_java_Yaroslavtser.Repositry.BalanceEntityRepository;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.BalanceDTO;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.UserDTO;
import com.example.Kyrcach_java_Yaroslavtser.exception.UserNotFoundException;
import com.example.Kyrcach_java_Yaroslavtser.model.BalanceEntity;
import com.example.Kyrcach_java_Yaroslavtser.model.Work_ipEntity;
import com.example.Kyrcach_java_Yaroslavtser.security.CustomUserDetail;
import com.example.Kyrcach_java_Yaroslavtser.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
public class BalanceServiceImpl implements BalanceService {

    @Autowired
    private BalanceEntityRepository balanceRepository;


    @Transactional
    @Override
    public boolean save_balance(BalanceDTO balanceDTO, CustomUserDetail currUser) {


        BalanceEntity balanceEntity = balanceRepository.findByOwnerId(currUser.getId());

        balanceEntity.setBalance(balanceDTO.getBalance());
        balanceEntity.setSalary(balanceDTO.getSalary());
        balanceEntity.setExpensive(balanceDTO.getExpensive());

        balanceRepository.save(balanceEntity);

        return true;
    }

    @Override
    public BalanceDTO findByUserId(Long id) {
        return mapWalletDTO(balanceRepository.findByOwnerId(id));
    }

    private BalanceDTO mapWalletDTO(BalanceEntity walletEntity) {
        return new BalanceDTO(walletEntity.getId(), walletEntity.getBalance(), walletEntity.getSalary(), walletEntity.getExpensive(), new UserDTO(walletEntity.getOwner().getId()));
    }

    @Transactional
    @Override
    public void update(BalanceDTO balanceDTO) {
        Optional<BalanceEntity> editUserEntity = balanceRepository.findById(balanceDTO.getId_mon());
        if (editUserEntity.isPresent()) {

            editUserEntity.get().setBalance(balanceDTO.getBalance());
            editUserEntity.get().setExpensive(balanceDTO.getExpensive());
            editUserEntity.get().setSalary(balanceDTO.getSalary());

            balanceRepository.save(editUserEntity.get());
        } else {
            throw new UserNotFoundException("User with id=" + balanceDTO.getId_mon() + " not found");
        }
    }

    @Override
    public void minus(BalanceDTO balanceDTO) {
        Optional<BalanceEntity> editUserEntity = balanceRepository.findById(balanceDTO.getId_mon());
        if (editUserEntity.isPresent()) {
            editUserEntity.get().setBalance(balanceDTO.getBalance() - 500);
            balanceRepository.save(editUserEntity.get());
        }
    }
}
