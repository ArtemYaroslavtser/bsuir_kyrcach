package com.example.Kyrcach_java_Yaroslavtser.model;

import javax.persistence.*;

@Entity
@Table(name = "accounts_ychet")
public class Accounts_ychetEntity extends First_Entity {


    @ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_accounts")
    private AccountsEntity accounts;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ychet")
    private YchetEntity ychetEntity;

    @JoinColumn(name = "viruchka")
    private int viruchka;

    @JoinColumn(name = "byx_balance")
    private int byx_balance;


    public int getByx_balance() {
        return byx_balance;
    }

    public void setByx_balance(int byx_balance) {
        this.byx_balance = byx_balance;
    }

    public int getViruchka() {
        return viruchka;
    }

    public void setViruchka(int viruchka) {
        this.viruchka = viruchka;
    }

    public YchetEntity getYchetEntity() {
        return ychetEntity;
    }

    public void setYchetEntity(YchetEntity ychetEntity) {
        this.ychetEntity = ychetEntity;
    }

    public AccountsEntity getAccounts() {
        return accounts;
    }

    public void setAccounts(AccountsEntity accounts) {
        this.accounts = accounts;
    }

    public Accounts_ychetEntity(){

    }

    public Accounts_ychetEntity(Long id, AccountsEntity accounts, YchetEntity ychetEntity) {
        super(id);
        this.accounts = accounts;
        this.ychetEntity = ychetEntity;
    }

    public Accounts_ychetEntity(AccountsEntity accounts, YchetEntity ychetEntity, int viruchka) {
        this.accounts = accounts;
        this.ychetEntity = ychetEntity;
        this.viruchka = viruchka;
    }
}
