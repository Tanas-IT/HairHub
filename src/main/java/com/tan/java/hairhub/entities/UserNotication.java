package com.tan.java.hairhub.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "userNotification")
public class UserNotication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userNotificationId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "notificationId")
    private Notification notification;

    public UserNotication() {}

    public UserNotication(int userNotificationId, User user, Notification notification) {
        this.userNotificationId = userNotificationId;
        this.user = user;
        this.notification = notification;
    }

    public int getUserNotificationId() {
        return userNotificationId;
    }

    public void setUserNotificationId(int userNotificationId) {
        this.userNotificationId = userNotificationId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }
}
