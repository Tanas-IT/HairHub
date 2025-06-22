package com.tan.java.hairhub.entities;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "workLog")
public class WorkLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int workLogId;

    @Column(name = "workLogName")
    private String workLogName;

    private String status;

    private Boolean isCompleted;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "startTime")
    private Time startTime;

    @Column(name = "endTime")
    private Time endTime;

    @ManyToOne
    @JoinColumn(name = "scheduleId")
    private Schedule schedule;

    @OneToMany(mappedBy = "workLog", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<UserWorkLog> userWorkLogs;

    public WorkLog() {}

    public WorkLog(
            String workLogName,
            String status,
            Boolean isCompleted,
            LocalDate date,
            Time startTime,
            Time endTime,
            Schedule schedule,
            List<UserWorkLog> userWorkLogs) {
        this.workLogName = workLogName;
        this.status = status;
        this.isCompleted = isCompleted;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.schedule = schedule;
        this.userWorkLogs = userWorkLogs;
    }

    public int getWorkLogId() {
        return workLogId;
    }

    public void setWorkLogId(int workLogId) {
        this.workLogId = workLogId;
    }

    public String getWorkLogName() {
        return workLogName;
    }

    public void setWorkLogName(String workLogName) {
        this.workLogName = workLogName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public List<UserWorkLog> getUserWorkLogs() {
        return userWorkLogs;
    }

    public void setUserWorkLogs(List<UserWorkLog> userWorkLogs) {
        this.userWorkLogs = userWorkLogs;
    }
}
