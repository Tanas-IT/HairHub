package com.tan.java.hairhub.entities;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "timeOfService")
    private String timeOfSerivce;

    @OneToMany(mappedBy = "service", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ImageResource> imageResources;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Combo> combos;

    public Service() {}

    public Service(
            String title,
            String description,
            String timeOfSerivce,
            List<ImageResource> imageResources,
            List<Combo> combos) {
        this.title = title;
        this.description = description;
        this.timeOfSerivce = timeOfSerivce;
        this.imageResources = imageResources;
        this.combos = combos;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeOfSerivce() {
        return timeOfSerivce;
    }

    public void setTimeOfSerivce(String timeOfSerivce) {
        this.timeOfSerivce = timeOfSerivce;
    }

    public List<ImageResource> getImageResources() {
        return imageResources;
    }

    public void setImageResources(List<ImageResource> imageResources) {
        this.imageResources = imageResources;
    }

    public List<Combo> getCombos() {
        return combos;
    }

    public void setCombos(List<Combo> combos) {
        this.combos = combos;
    }
}
