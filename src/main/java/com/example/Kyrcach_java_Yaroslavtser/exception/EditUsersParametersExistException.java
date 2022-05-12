package com.example.Kyrcach_java_Yaroslavtser.exception;

import com.example.Kyrcach_java_Yaroslavtser.controller.DTO.UserDTO;


public class EditUsersParametersExistException extends RuntimeException {

    UserDTO userDTO;

    public EditUsersParametersExistException(String message, UserDTO userDTO) {
        super(message);
        this.userDTO = userDTO;
    }
}
