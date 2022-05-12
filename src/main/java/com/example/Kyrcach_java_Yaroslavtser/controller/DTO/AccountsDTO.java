package com.example.Kyrcach_java_Yaroslavtser.controller.DTO;

import com.example.Kyrcach_java_Yaroslavtser.model.OrderStatus;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Date;


public class AccountsDTO {

    private Long id_accounts;


    @Size(min = 3, max = 20)
    private String Name_operat;


    @Size(min = 8, max = 30)
    private String goal;


    @Size(min = 3, max = 14)
    private int Symm;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private OrderStatus status;

    private UserDTO userDTO;



    public AccountsDTO(){

    }
    public AccountsDTO(Long id_accounts, String name_operat, String goal, int symm, UserDTO userDTO, OrderStatus status, Date date) {
        this.id_accounts = id_accounts;
        this.Name_operat = name_operat;
        this.goal = goal;
        this.Symm = symm;
        this.status = status;
        this.userDTO = userDTO;
        this.date = date;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public Long getId_accounts() {
        return id_accounts;
    }

    public void setId_accounts(Long id_accounts) {
        this.id_accounts = id_accounts;
    }

    public String getName_operat() {
        return Name_operat;
    }

    public void setName_operat(String name_operat) {
        Name_operat = name_operat;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public int getSymm() {
        return Symm;
    }

    public void setSymm(int symm) {
        Symm = symm;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
