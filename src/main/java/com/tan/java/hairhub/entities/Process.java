package com.tan.java.hairhub.entities;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "process")
public class Process {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "processId")
    private int processId;

    private String processName;

    private String description;

    @OneToOne(mappedBy = "process")
    private Combo combo;

    @OneToMany(mappedBy = "process", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ProcessStep> processSteps;

    @OneToMany(mappedBy = "process", fetch = FetchType.EAGER)
    private List<ImageResource> imageResources;

    public Process() {}

    public Process(
            String processName,
            String description,
            Combo combo,
            List<ProcessStep> processSteps,
            List<ImageResource> imageResources) {
        this.processName = processName;
        this.description = description;
        this.combo = combo;
        this.processSteps = processSteps;
        this.imageResources = imageResources;
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Combo getCombo() {
        return combo;
    }

    public void setCombo(Combo combo) {
        this.combo = combo;
    }

    public List<ProcessStep> getProcessSteps() {
        return processSteps;
    }

    public void setProcessSteps(List<ProcessStep> processSteps) {
        this.processSteps = processSteps;
    }

    public List<ImageResource> getImageResources() {
        return imageResources;
    }

    public void setImageResources(List<ImageResource> imageResources) {
        this.imageResources = imageResources;
    }
}
