package com.example.Kyrcach_java_Yaroslavtser.controller.DTO;

import com.example.Kyrcach_java_Yaroslavtser.model.*;
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
    private Operation goal;


    @Size(min = 3, max = 14)
    private int Symm;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private int nalog;
    private OrderStatus status;

    private Order_balance balance;
    private UserDTO userDTO;

    private NDS nds;

    private int pronds;

    private NDS_oplata nds_oplata;

    public int getNalog() {
        return nalog;
    }

    public NDS_oplata getNds_oplata() {
        return nds_oplata;
    }

    public void setNds_oplata(NDS_oplata nds_oplata) {
        this.nds_oplata = nds_oplata;
    }

    public void setNalog(int nalog) {
        this.nalog = nalog;
    }

    public Order_balance getBalance() {
        return balance;
    }

    public void setBalance(Order_balance balance) {
        this.balance = balance;
    }

private int NDS_YES;

    public int getNDS_YES() {
        return NDS_YES;
    }

    public void setNDS_YES(int NDS_YES) {
        this.NDS_YES = NDS_YES;
    }

    public NDS getNds() {
        return nds;
    }

    public void setNds(NDS nds) {
        this.nds = nds;
    }

    public int getPronds() {
        return pronds;
    }

    public void setPronds(int pronds) {
        this.pronds = pronds;
    }

    public AccountsDTO(){

    }
    public AccountsDTO(Long id_accounts, String name_operat, Operation goal, int symm, UserDTO userDTO, OrderStatus status, Date date, int nalog) {
        this.id_accounts = id_accounts;
        this.Name_operat = name_operat;
        this.goal = goal;
        this.Symm = symm;
        this.status = status;
        this.userDTO = userDTO;
        this.date = date;
        this.nalog = nalog;
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

    public Operation getGoal() {
        return goal;
    }

    public void setGoal(Operation goal) {
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
