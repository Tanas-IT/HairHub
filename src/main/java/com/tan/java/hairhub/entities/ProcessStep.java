package com.tan.java.hairhub.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "processStep")
public class ProcessStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int processStepId;

    @ManyToOne
    @JoinColumn(name = "processId")
    private Process process;

    @ManyToOne
    @JoinColumn(name = "stepId")
    private Step step;

    @Column(name = "orderNumber")
    private int orderNumber;

    public ProcessStep() {}

    public ProcessStep(Process process, Step step, int orderNumber) {
        this.process = process;
        this.step = step;
        this.orderNumber = orderNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getProcessStepId() {
        return processStepId;
    }

    public void setProcessStepId(int processStepId) {
        this.processStepId = processStepId;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }
}
