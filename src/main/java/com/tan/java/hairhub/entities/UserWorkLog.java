package com.tan.java.hairhub.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "userWorkLog")
public class UserWorkLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userWorkLogId;

    @ManyToOne
    @JoinColumn(name = "workLogId")
    private WorkLog workLog;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(name = "statusOfUser")
    private String statusOfUser;

    public UserWorkLog() {}

    public UserWorkLog(WorkLog workLog, User user, String statusOfUser) {
        this.workLog = workLog;
        this.user = user;
        this.statusOfUser = statusOfUser;
    }

    public int getUserWorkLogId() {
        return userWorkLogId;
    }

    public void setUserWorkLogId(int userWorkLogId) {
        this.userWorkLogId = userWorkLogId;
    }

    public WorkLog getWorkLog() {
        return workLog;
    }

    public void setWorkLog(WorkLog workLog) {
        this.workLog = workLog;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatusOfUser() {
        return statusOfUser;
    }

    public void setStatusOfUser(String statusOfUser) {
        this.statusOfUser = statusOfUser;
    }
}
