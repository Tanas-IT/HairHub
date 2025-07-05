package com.tan.java.hairhub.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "image_resource")
public class ImageResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imageId")
    private int imageId;

    @Column(name = "imageURL")
    private String imageURL;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "storeId")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "serviceId")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "comboId")
    private Combo combo;

    @ManyToOne
    @JoinColumn(name = "processId")
    private Process process;

    public ImageResource() {}

    public ImageResource(
            String imageURL, String description, Store store, Service service, Combo combo, Process process) {
        this.imageURL = imageURL;
        this.description = description;
        this.store = store;
        this.service = service;
        this.combo = combo;
        this.process = process;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public Combo getCombo() {
        return combo;
    }

    public void setCombo(Combo combo) {
        this.combo = combo;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
