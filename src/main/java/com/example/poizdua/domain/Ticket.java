package com.example.poizdua.domain;

import javax.persistence.*;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column(name="valueNum")
    private Integer num;
    @Column(name="valueFrom")
    private String from;
    @Column(name="valueTo")
    private String to;
    @Column(name="valueDate")
    private String date;
    @ManyToOne(fetch = FetchType.EAGER)
    private User buyer;

    public Ticket() {}

    public Ticket(Integer num, String from, String to, String date, User buyer) {
        this.num = num;
        this.from = from;
        this.to = to;
        this.date = date;
        this.buyer = buyer;
    }

    public String getBuyerName() {
        return buyer != null ? buyer.getUsername() : "<none>";
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}