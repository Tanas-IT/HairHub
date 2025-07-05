package com.tan.java.hairhub.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "typeOfService")
    private String typeOfService;

    @OneToMany(mappedBy = "service", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ImageResource> imageResources;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference // Chiều cha
    private List<Combo> combos;

    public Service() {}

    public Service(
            String title,
            String description,
            String typeOfService,
            List<ImageResource> imageResources,
            List<Combo> combos) {
        this.title = title;
        this.description = description;
        this.imageResources = imageResources;
        this.combos = combos;
        this.typeOfService = typeOfService;
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

    public String getTypeOfService() {
        return typeOfService;
    }

    public void setTypeOfService(String typeOfService) {
        this.typeOfService = typeOfService;
    }

    @Override
    public String toString() {
        return "Service{" +
                "serviceId=" + serviceId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageResources=" + imageResources +
                ", combos=" + combos +
                ", typeOfservices=" + typeOfService +
                '}';
    }
}
