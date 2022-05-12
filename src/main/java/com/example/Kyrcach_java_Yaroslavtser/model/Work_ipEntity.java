package com.example.Kyrcach_java_Yaroslavtser.model;

import javax.persistence.*;

@Entity
@Table(name = "work_1")
public class Work_ipEntity extends First_Entity {

    @Column(name = "name_work", length = 15)
    private String name_work;

    @Column(name = "adress", length = 50)
    private String adress;

    @Column(name = "Number_registration", length = 15)
    private int Number_registration;

    @OneToOne(mappedBy = "work_ipEntity")
    private UserEntity owner;
    public Work_ipEntity() {

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

    public UserEntity getUserEntity() {
        return owner;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.owner = userEntity;
    }

    public Work_ipEntity(String name_work, String adress, int number_registration) {
        this.name_work = name_work;
        this.adress = adress;
        this.Number_registration = number_registration;
    }

    public Work_ipEntity(String name_work, String adress, int number_registration, UserEntity owner) {
        this.name_work = name_work;
        this.adress = adress;
        Number_registration = number_registration;
        this.owner = owner;
    }
}
