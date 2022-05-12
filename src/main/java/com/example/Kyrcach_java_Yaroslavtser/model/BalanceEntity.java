package com.example.Kyrcach_java_Yaroslavtser.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "balance")
public class BalanceEntity extends First_Entity {

    @Column(name = "Balance")
    private int Balance;


    @Column(name = "Salary")
    private int Salary;

    @Column(name = "Expensive")
    private int Expensive;

    @OneToOne(mappedBy = "balanceEntity")
    private UserEntity owner;

    public BalanceEntity() {

    }

    public BalanceEntity(int i, int i1, int i2) {
        this.Balance = i;
        this.Expensive = i1;
        this.Salary = i2;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public int getBalance() {
        return Balance;
    }

    public void setBalance(int balance) {
        Balance = balance;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }

    public int getExpensive() {
        return Expensive;
    }

    public void setExpensive(int expensive) {
        Expensive = expensive;
    }


    public BalanceEntity(Long id, int balance, int salary, int expensive, UserEntity userEntity) {
        this.Balance = balance;
        this.Salary = salary;
        this.Expensive = expensive;
        this.owner = userEntity;
    }
}
