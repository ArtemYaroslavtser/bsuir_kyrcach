package com.example.Kyrcach_java_Yaroslavtser.security;


import com.example.Kyrcach_java_Yaroslavtser.model.UserEntity;
import com.example.Kyrcach_java_Yaroslavtser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailService extends UserEntity implements UserDetailsService{

    @Autowired
    private UserService userService;

    /*public CustomUserDetailService(UserService userService) {
        this.userService = userService;
    }*/

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUser = userService.findByLogin(login);

        optionalUser
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return optionalUser
                .map(CustomUserDetail::new).get();
    }
}