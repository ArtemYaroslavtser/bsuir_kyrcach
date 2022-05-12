package com.example.Kyrcach_java_Yaroslavtser.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Entity
@Table(name = "users")
public class UserEntity extends First_Entity {

    @NotNull
    @Column(name = "login", length = 15)
    private String login;

    @NotNull
    @Column(name = "password", length = 15)
    private String password;

    @NotNull
    @Column(name = "name", length = 15)
    private String firstName;

    @NotNull
    @Column(name = "surname", length = 15)
    private String lastName;

    @NotNull
    @Column(name = "phone", length = 15)
    private String phoneNumber;


    @ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "roles_id")
    private RoleEntity roleEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "work_id")
    private Work_ipEntity work_ipEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "balance_id")
    private BalanceEntity balanceEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dogovor_id")
    private DogovorEntity dogovorEntity;


    public DogovorEntity getDogovorEntity() {
        return dogovorEntity;
    }

    public void setDogovorEntity(DogovorEntity dogovorEntity) {
        this.dogovorEntity = dogovorEntity;
    }

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    private Set<AccountsEntity> accountsEntities;

    @OneToMany(mappedBy = "userEntity1", fetch = FetchType.LAZY)
    private Set<YchetEntity> ychetEntities;


    public Set<YchetEntity> getYchetEntities() {
        return ychetEntities;
    }

    public void setYchetEntities(Set<YchetEntity> ychetEntities) {
        this.ychetEntities = ychetEntities;
    }

    public Set<AccountsEntity> getAccountsEntities() {
        return accountsEntities;
    }

    public void setAccountsEntities(Set<AccountsEntity> accountsEntities) {
        this.accountsEntities = accountsEntities;
    }

    public Work_ipEntity getWork_ipEntity() {
        return work_ipEntity;
    }

    public void setWork_ipEntity(Work_ipEntity work_ipEntity) {
        this.work_ipEntity = work_ipEntity;
    }

    public BalanceEntity getBalanceEntity() {
        return balanceEntity;
    }

    public void setBalanceEntity(BalanceEntity balanceEntity) {
        this.balanceEntity = balanceEntity;
    }

    public UserEntity() {
    }

    public UserEntity(UserEntity user) {
        this.setId(user.getId());
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.roleEntity = user.getRoleEntity();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phoneNumber = user.getPhoneNumber();

    }




    public UserEntity(@NotNull String login, @NotNull String password, @NotNull String firstName, @NotNull String lastName, @NotNull String phoneNumber, RoleEntity roleEntity, Set<AccountsEntity> accountsEntity, Set<YchetEntity> ychetEntities) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.roleEntity = roleEntity;
        this.accountsEntities = accountsEntity;
        this.ychetEntities = ychetEntities;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity role) {
        this.roleEntity = role;
    }




}