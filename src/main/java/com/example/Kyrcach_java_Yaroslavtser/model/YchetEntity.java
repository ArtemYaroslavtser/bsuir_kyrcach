package com.example.Kyrcach_java_Yaroslavtser.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "ychet")
public class YchetEntity extends First_Entity {

    @Column(name = "work")
    private String work;

    @Column(name = "ychet")
    @Enumerated(EnumType.STRING)
    private YchetStatus status;


    @Column(name = "dateFirst")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFirst;

    @Column(name = "dateSecond")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateSecond;


    @ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private UserEntity userEntity1;

    @Column(name = "status_ychet", length = 8)
    @Enumerated(EnumType.STRING)
    private YchetStatus_admin ychetStatus_admin;


    @OneToMany(mappedBy = "ychetEntity", fetch = FetchType.LAZY)
    private Set<Accounts_ychetEntity> accounts_ychetEntities;


    public YchetStatus_admin getYchetStatus_admin() {
        return ychetStatus_admin;
    }

    public void setYchetStatus_admin(YchetStatus_admin ychetStatus_admin) {
        this.ychetStatus_admin = ychetStatus_admin;
    }

    public Set<Accounts_ychetEntity> getAccounts_ychetEntities() {
        return accounts_ychetEntities;
    }

    public void setAccounts_ychetEntities(Set<Accounts_ychetEntity> accounts_ychetEntities) {
        this.accounts_ychetEntities = accounts_ychetEntities;
    }

    public YchetEntity() {

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



    public UserEntity getUserEntity1() {
        return userEntity1;
    }

    public void setUserEntity1(UserEntity userEntity1) {
        this.userEntity1 = userEntity1;
    }


    public YchetEntity(Long id, String work, YchetStatus status, YchetStatus_admin ychet, Date dateFirst, Date dateSecond,  UserEntity userEntity1) {
        super(id);
        this.work = work;
        this.status = status;
        this.ychetStatus_admin = ychet;
        this.dateFirst = dateFirst;
        this.dateSecond = dateSecond;

        this.userEntity1 = userEntity1;
    }

    public YchetEntity(String work, YchetStatus status,YchetStatus_admin ychet, Date dateFirst, Date dateSecond,  UserEntity userEntity1) {
        this.work = work;
        this.status = status;
        this.ychetStatus_admin = ychet;
        this.dateFirst = dateFirst;
        this.dateSecond = dateSecond;

        this.userEntity1 = userEntity1;
    }
}
