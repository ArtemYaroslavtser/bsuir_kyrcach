package com.example.Kyrcach_java_Yaroslavtser.service.serviceImpl;

import com.example.Kyrcach_java_Yaroslavtser.Repositry.RoleEntityRepository;
import com.example.Kyrcach_java_Yaroslavtser.Repositry.UserEntityRepository;
import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.UserDTO;
import com.example.Kyrcach_java_Yaroslavtser.model.*;
import com.example.Kyrcach_java_Yaroslavtser.service.UserService;
import com.example.Kyrcach_java_Yaroslavtser.exception.EditUsersParametersExistException;
import com.example.Kyrcach_java_Yaroslavtser.exception.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private final Double DEFAULT_SUM = 0D;

    @Autowired
    private UserEntityRepository userRepository;
    @Autowired
    private RoleEntityRepository roleRepository;

    @Transactional
    @Override
    public boolean save(UserDTO userDTO, String role) {


        Long idExistUser = userRepository.getIdUserByLogin(userDTO.getLogin());
        if (idExistUser != null) {
            return false;
        }

        idExistUser = userRepository.getIdUserByPhoneNumber(userDTO.getPhoneNumber());
        if (idExistUser != null) {
           return false;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(userDTO.getLogin());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setPhoneNumber(userDTO.getPhoneNumber());
        userEntity.setRoleEntity(roleRepository.findByRole(role));

        Date date = new Date();
        if (role.equals("ROLE_USER")) {
            userEntity.setWork_ipEntity(new Work_ipEntity("NOTHING","NOTHING",1));
            userEntity.setBalanceEntity(new BalanceEntity(0,0,0));
            userEntity.setDogovorEntity(new DogovorEntity("Бухгалтерская помощь ИП",500, date , DogovorStatus.Незаключен ));
        }

        userRepository.save(userEntity);

        return true;
    }

    @Override
    public List<UserDTO> getUsersByRole(String role) {

        List<UserEntity> userEntityList = userRepository.findAllByRoleEntity_Role(role);
        List<UserDTO> userDTOList = userEntityList.stream()
                .map(a -> new UserDTO(
                        a.getId(),
                        a.getLogin(),
                        a.getPassword(),
                        a.getFirstName(),
                        a.getLastName(),
                        a.getPhoneNumber(),
                        a.getRoleEntity().getRole()
                ))
                .collect(Collectors.toList());

        return userDTOList;

    }

    @Transactional
    @Override
    public void deleteUserById(Long id) throws UserNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findById(id);

        if (userEntity.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("User with id=" + id + " not found");
        }
    }

    @Override
    public UserDTO findUserById(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        return userEntity.isPresent() ? mapUserDTO(userEntity.get()) : null;
    }

    private UserDTO mapUserDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setLogin(userEntity.getLogin());
        userDTO.setPassword(userEntity.getPassword());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());;
        userDTO.setPhoneNumber(userEntity.getPhoneNumber());
        userDTO.setRole(userEntity.getRoleEntity().getRole());
        return userDTO;
    }

    @Transactional
    @Override
    public void update(UserDTO user) {

        Long idExistUser;


        idExistUser = userRepository.getIdUserByLogin(user.getLogin());
        if (idExistUser != null && !idExistUser.equals(user.getId())) {
            throw new EditUsersParametersExistException("This_login_is_exist", user);
        }

        idExistUser = userRepository.getIdUserByPhoneNumber(user.getPhoneNumber());
        if (idExistUser != null && !idExistUser.equals(user.getId())) {
            throw new EditUsersParametersExistException("This_phone_number_already_exist", user);
        }

        Optional<UserEntity> editUserEntity = userRepository.findById(user.getId());
        if (editUserEntity.isPresent()) {

            editUserEntity.get().setLogin(user.getLogin());
            editUserEntity.get().setPassword(user.getPassword());
            editUserEntity.get().setFirstName(user.getFirstName());
            editUserEntity.get().setLastName(user.getLastName());
            editUserEntity.get().setPhoneNumber(user.getPhoneNumber());

            userRepository.save(editUserEntity.get());
        } else {
            throw new UserNotFoundException("User with id=" + user.getId() + " not found");
        }

    }


    @Override
    public Optional<UserEntity> findByLogin(String login) {
        return Optional.ofNullable(userRepository.findByLogin(login));
    }

}
