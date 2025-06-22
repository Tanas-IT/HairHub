package com.tan.java.hairhub.entities;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notificationId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "link")
    private String link;

    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<UserNotication> userNotications;

    public Notification() {}

    public Notification(String title, String content, String link, List<UserNotication> userNotications) {
        this.title = title;
        this.content = content;
        this.link = link;
        this.userNotications = userNotications;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<UserNotication> getUserNotications() {
        return userNotications;
    }

    public void setUserNotications(List<UserNotication> userNotications) {
        this.userNotications = userNotications;
    }
}
