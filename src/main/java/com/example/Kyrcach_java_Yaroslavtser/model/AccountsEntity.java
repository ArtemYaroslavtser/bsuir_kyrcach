package com.example.Kyrcach_java_Yaroslavtser.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "accounts")
public class AccountsEntity extends First_Entity {

    @Column(name = "Name_operat", length = 50)
    private String name_operat;

    @Column(name = "goal", length = 50)
    @Enumerated(EnumType.STRING)
    private Operation goal;

    @Column(name = "Symm", length = 15)
    private int Symm;

    @Column(name = "vibor", length = 8)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "nalog")
    private int nalog;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column(name = "status_balance")
    @Enumerated(EnumType.STRING)
    private Order_balance order_balance;

    @Column(name = "NDS")
    @Enumerated(EnumType.STRING)
    private NDS nds;

    @Column(name = "NDS_oplata")
    @Enumerated(EnumType.STRING)
    private NDS_oplata nds_oplata;

    @Column(name = "NDS_YES")
    private int NDS_YES;

    @Column(name = "Pro_NDS")
    private int pronds;

    public NDS_oplata getNds_oplata() {
        return nds_oplata;
    }

    public void setNds_oplata(NDS_oplata nds_oplata) {
        this.nds_oplata = nds_oplata;
    }

    public int getNDS_YES() {
        return NDS_YES;
    }

    public void setNDS_YES(int NDS_YES) {
        this.NDS_YES = NDS_YES;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Order_balance getOrder_balance() {
        return order_balance;
    }

    public void setOrder_balance(Order_balance order_balance) {
        this.order_balance = order_balance;
    }

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private UserEntity userEntity;


    @OneToMany(mappedBy = "accounts", fetch = FetchType.LAZY)
    private Set<Accounts_ychetEntity> accounts_ychetEntities;

    public Set<Accounts_ychetEntity> getAccounts_ychetEntities() {
        return accounts_ychetEntities;
    }

    public void setAccounts_ychetEntities(Set<Accounts_ychetEntity> accounts_ychetEntities) {
        this.accounts_ychetEntities = accounts_ychetEntities;
    }


    public NDS getNds() {
        return nds;
    }

    public void setNds(NDS nds) {
        this.nds = nds;
    }

    public int getPronds() {
        return pronds;
    }

    public void setPronds(int pronds) {
        this.pronds = pronds;
    }

    public int getNalog() {
        return nalog;
    }

    public void setNalog(int nalog) {
        this.nalog = nalog;
    }

    public String getName_operat() {
        return name_operat;
    }

    public void setName_operat(String name_operat) {
        this.name_operat = name_operat;
    }


    public int getSymm() {
        return Symm;
    }

    public void setSymm(int symm) {
        Symm = symm;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Operation getGoal() {
        return goal;
    }

    public void setGoal(Operation goal) {
        this.goal = goal;
    }


    public AccountsEntity(String name_operat, Operation goal, int symm, OrderStatus status, UserEntity userEntity, Date date, int nalog, Order_balance order_balance, NDS nds, int pronds, NDS_oplata nds_oplata, int NDS_YES) {

        this.name_operat = name_operat;
        this.goal = goal;
        Symm = symm;
        this.status = status;
        this.userEntity = userEntity;
        this.date = date;
        this.nalog = nalog;
        this.order_balance = order_balance;
        this.nds = nds;
        this.pronds = pronds;
        this.nds_oplata = nds_oplata;
        this.NDS_YES = NDS_YES;
    }

    public AccountsEntity() {
    }

    public AccountsEntity(AccountsEntity accounts) {
        this.setId(accounts.getId());
        this.name_operat = accounts.getName_operat();
        this.goal = accounts.getGoal();
        this.Symm = accounts.getSymm();
        this.status = accounts.getStatus();
        this.userEntity = accounts.getUserEntity();
        this.date = accounts.getDate();
        this.nalog = accounts.getNalog();
        this.order_balance = accounts.getOrder_balance();
        this.nds = accounts.getNds();
        this.pronds = accounts.getPronds();
        this.nds_oplata = accounts.getNds_oplata();
        this.NDS_YES = accounts.getNDS_YES();
    }
}
