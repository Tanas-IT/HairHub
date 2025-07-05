package com.tan.java.hairhub.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "combo")
public class Combo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comboId;

    @Column(name = "comboName")
    private String comboName;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "combo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ImageResource> imageResources;

    @ManyToOne
    @JoinColumn(name = "parent_combo_id")
    private Combo parentCombo;

    @OneToMany(mappedBy = "parentCombo", cascade = CascadeType.ALL)
    private List<Combo> childCombos;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "process_id")
    @JsonManagedReference // Chiều cha
    private Process process;

    @OneToMany(mappedBy = "combo", fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "serviceId")
    @JsonBackReference // Chiều con
    private Service service;

    @Column(name = "timeOfService")
    private String timeOfSerivce;

    @Column(name = "price")
    private double price;

    public Combo() {}

    public Combo(
            String comboName,
            String description,
            double price,
            List<ImageResource> imageResources,
            Combo parentCombo,
            List<Combo> childCombos,
            Process process,
            String timeOfSerivce,
            List<OrderDetail> orderDetails,
            Service service) {
        this.comboName = comboName;
        this.description = description;
        this.imageResources = imageResources;
        this.parentCombo = parentCombo;
        this.childCombos = childCombos;
        this.process = process;
        this.orderDetails = orderDetails;
        this.service = service;
        this.price = price;
        this.timeOfSerivce = timeOfSerivce;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public int getComboId() {
        return comboId;
    }

    public void setComboId(int comboId) {
        this.comboId = comboId;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ImageResource> getImageResources() {
        return imageResources;
    }

    public void setImageResources(List<ImageResource> imageResources) {
        this.imageResources = imageResources;
    }

    public Combo getParentCombo() {
        return parentCombo;
    }

    public void setParentCombo(Combo parentCombo) {
        this.parentCombo = parentCombo;
    }

    public List<Combo> getChildCombos() {
        return childCombos;
    }

    public void setChildCombos(List<Combo> childCombos) {
        this.childCombos = childCombos;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public String getTimeOfSerivce() {
        return timeOfSerivce;
    }

    public void setTimeOfSerivce(String timeOfSerivce) {
        this.timeOfSerivce = timeOfSerivce;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
