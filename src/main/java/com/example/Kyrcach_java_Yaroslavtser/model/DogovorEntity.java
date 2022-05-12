package com.example.Kyrcach_java_Yaroslavtser.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "dogovor")
public class DogovorEntity extends First_Entity {

    @Column(name = "name_dogovor")
    private String name_dogovor;

    @Column(name = "summ")
    private int summ;

    @Column(name = "data")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date date;

    @OneToOne(mappedBy = "dogovorEntity")
    private UserEntity owner;

    @Column(name = "status", length = 8)
    @Enumerated(EnumType.STRING)
    private DogovorStatus status;

    public DogovorStatus getStatus() {
        return status;
    }

    public void setStatus(DogovorStatus status) {
        this.status = status;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public String getName_dogovor() {
        return name_dogovor;
    }

    public void setName_dogovor(String name_dogovor) {
        this.name_dogovor = name_dogovor;
    }

    public int getSumm() {
        return summ;
    }

    public void setSumm(int summ) {
        this.summ = summ;
    }


    public DogovorEntity(Long id, String name_dogovor, int summ, java.util.Date date, DogovorStatus status) {
        super(id);
        this.name_dogovor = name_dogovor;
        this.summ = summ;
        this.date = date;
        this.status = status;
    }

    public DogovorEntity(String name_dogovor, int summ, java.util.Date date, DogovorStatus status) {
        this.name_dogovor = name_dogovor;
        this.summ = summ;
        this.date = date;
        this.status = status;
    }

    public DogovorEntity(){

    }
}
