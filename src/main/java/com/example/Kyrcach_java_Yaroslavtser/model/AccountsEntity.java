package com.example.Kyrcach_java_Yaroslavtser.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "accounts")
public class AccountsEntity extends First_Entity {

    @Column(name = "Name_operat", length = 50)
    private String name_operat;

    @Column(name = "goal", length = 50)
    private String goal;

    @Column(name = "Symm", length = 15)
    private int Symm;

    @Column(name = "vibor", length = 8)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;



    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private UserEntity userEntity;


    @OneToMany(mappedBy = "accounts", fetch = FetchType.LAZY)
    private Set<Accounts_ychetEntity> accounts_ychetEntities;

    public Set<Accounts_ychetEntity> getAccounts_ychetEntities() {
        return accounts_ychetEntities;
    }

    public void setAccounts_ychetEntities(Set<Accounts_ychetEntity> accounts_ychetEntities) {
        this.accounts_ychetEntities = accounts_ychetEntities;
    }

    public String getName_operat() {
        return name_operat;
    }

    public void setName_operat(String name_operat) {
        this.name_operat = name_operat;
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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public AccountsEntity(String name_operat, String goal, int symm, OrderStatus status, UserEntity userEntity, Date date) {
        this.name_operat = name_operat;
        this.goal = goal;
        Symm = symm;
        this.status = status;
        this.userEntity = userEntity;
        this.date = date;
    }

    public AccountsEntity() {
    }

    public AccountsEntity(AccountsEntity accounts) {
        this.setId(accounts.getId());
        this.name_operat = accounts.getName_operat();
        this.goal = accounts.getGoal();
        this.Symm = accounts.getSymm();
        this.status = accounts.getStatus();
        this.userEntity = accounts.getUserEntity();
        this.date = accounts.getDate();
    }
}
