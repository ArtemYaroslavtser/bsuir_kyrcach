package com.example.Kyrcach_java_Yaroslavtser.controller.DTO;

import com.example.Kyrcach_java_Yaroslavtser.model.DogovorStatus;
import com.example.Kyrcach_java_Yaroslavtser.model.OrderStatus;
import com.example.Kyrcach_java_Yaroslavtser.service.DogovorService;
import com.example.Kyrcach_java_Yaroslavtser.service.serviceImpl.DogovorServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Timestamp;

public class DogovorDTO {
    private Long id_dogovor;

    @Size(min = 3, max = 20)
    private String name_dogovor;

    private int summ;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date data;

    private UserDTO userDTO;

    private DogovorStatus status;

    public DogovorDTO(Long id_dogovor, String name_dogovor, int summ, java.util.Date data, UserDTO userDTO, DogovorStatus status) {
        this.id_dogovor = id_dogovor;
        this.name_dogovor = name_dogovor;
        this.summ = summ;
        this.data = data;
        this.userDTO = userDTO;
        this.status = status;
    }

    public DogovorStatus getStatus() {
        return status;
    }

    public void setStatus(DogovorStatus status) {
        this.status = status;
    }

    public Long getId_dogovor() {
        return id_dogovor;
    }

    public void setId_dogovor(Long id_dogovor) {
        this.id_dogovor = id_dogovor;
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

    public java.util.Date getData() {
        return data;
    }

    public void setData(java.util.Date data) {
        this.data = data;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
