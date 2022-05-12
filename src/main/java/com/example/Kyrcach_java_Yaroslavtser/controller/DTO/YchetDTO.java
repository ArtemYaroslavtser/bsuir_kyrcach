package com.example.Kyrcach_java_Yaroslavtser.controller.DTO;

import com.example.Kyrcach_java_Yaroslavtser.model.OrderStatus;
import com.example.Kyrcach_java_Yaroslavtser.model.YchetStatus;
import com.example.Kyrcach_java_Yaroslavtser.model.YchetStatus_admin;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.util.Date;

public class YchetDTO {

    private Long id_ychet;

    private String work;

    private YchetStatus status;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFirst;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateSecond;


    private UserDTO userDTO;

    private YchetStatus_admin ychetStatus_admin;
    public YchetDTO(){

    }

    public YchetDTO(Long id_ychet, String work, YchetStatus status, YchetStatus_admin ychetStatus_admin, Date dateFirst, Date dateSecond,  UserDTO userDTO) {
        this.id_ychet = id_ychet;
        this.work = work;
        this.status = status;

        this.dateFirst = dateFirst;
        this.dateSecond = dateSecond;
        this.ychetStatus_admin = ychetStatus_admin;
        this.userDTO = userDTO;
    }

    public YchetStatus_admin getYchetStatus_admin() {
        return ychetStatus_admin;
    }

    public void setYchetStatus_admin(YchetStatus_admin ychetStatus_admin) {
        this.ychetStatus_admin = ychetStatus_admin;
    }

    public Long getId_ychet() {
        return id_ychet;
    }

    public void setId_ychet(Long id_ychet) {
        this.id_ychet = id_ychet;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public YchetStatus getStatus() {
        return status;
    }

    public void setStatus(YchetStatus status) {
        this.status = status;
    }





    public Date getDateFirst() {
        return dateFirst;
    }

    public void setDateFirst(Date dateFirst) {
        this.dateFirst = dateFirst;
    }

    public Date getDateSecond() {
        return dateSecond;
    }

    public void setDateSecond(Date dateSecond) {
        this.dateSecond = dateSecond;
    }



    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
