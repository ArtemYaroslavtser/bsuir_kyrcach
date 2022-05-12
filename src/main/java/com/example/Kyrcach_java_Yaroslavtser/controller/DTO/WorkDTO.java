package com.example.Kyrcach_java_Yaroslavtser.controller.DTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class WorkDTO {

    private Long id_work;

    @Size(min = 3, max = 20)
    private String name_work;

    @Size(min = 8, max = 30)
    private String adress;

    @Size(min = 3, max = 14)
    private int Number_registration;

    private UserDTO userDTO;



    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public Long getId_work() {
        return id_work;
    }

    public void setId_work(Long id_work) {
        this.id_work = id_work;
    }

    public String getName_work() {
        return name_work;
    }

    public void setName_work(String name_work) {
        this.name_work = name_work;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getNumber_registration() {
        return Number_registration;
    }

    public void setNumber_registration(int number_registration) {
        Number_registration = number_registration;
    }

    public WorkDTO(){

    }
    public WorkDTO(Long id_work, String name_work, String adress, int number_registration, UserDTO userDTO) {
        this.id_work = id_work;
        this.name_work = name_work;
        this.adress = adress;
        this.Number_registration = number_registration;
        this.userDTO = userDTO;
    }

    @Override
    public String toString() {
        return "WorkDTO{" +
                "id_work=" + id_work +
                ", name_work='" + name_work + '\'' +
                ", adress='" + adress + '\'' +
                ", Number_registration=" + Number_registration +
                '}';
    }
}
