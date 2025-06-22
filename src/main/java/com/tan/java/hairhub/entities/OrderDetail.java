package com.tan.java.hairhub.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "orderDetail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderDetailId;

    @Column(name = "orderDetailName")
    private String orderDetailName;

    @Column(name = "note")
    private String note;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "comboId")
    private Combo combo;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

    public OrderDetail() {}

    public OrderDetail(String orderDetailName, String note, double price, Combo combo, Order order) {
        this.orderDetailName = orderDetailName;
        this.note = note;
        this.price = price;
        this.combo = combo;
        this.order = order;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getOrderDetailName() {
        return orderDetailName;
    }

    public void setOrderDetailName(String orderDetailName) {
        this.orderDetailName = orderDetailName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Combo getCombo() {
        return combo;
    }

    public void setCombo(Combo combo) {
        this.combo = combo;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
