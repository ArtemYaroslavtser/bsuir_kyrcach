package com.example.Kyrcach_java_Yaroslavtser.controller.DTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BalanceDTO {

    private Long id_mon;

    @Size(min = 3, max = 200)
    private int balance;

    @Size(min = 3, max = 300)
    private int salary;

    @Size(min = 3, max = 140)
    private int expensive;

    private UserDTO userDTO;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public Long getId_mon() {
        return id_mon;
    }

    public void setId_mon(Long id_mon) {
        this.id_mon = id_mon;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        balance = balance;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        salary = salary;
    }

    public int getExpensive() {
        return expensive;
    }

    public void setExpensive(int expensive) {
        expensive = expensive;
    }

    public BalanceDTO(Long id_mon, int balance, int salary, int expensive, UserDTO userDTO) {
        this.id_mon = id_mon;
        this.balance = balance;
        this.salary = salary;
        this.expensive = expensive;
        this.userDTO = userDTO;
    }

    @Override
    public String toString() {
        return "BalanceDTO{" +
                "id_mon=" + id_mon +
                ", Balance=" + balance +
                ", Salary=" + salary +
                ", Expensive=" + expensive +
                '}';
    }
}
