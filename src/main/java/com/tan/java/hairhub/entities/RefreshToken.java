package com.tan.java.hairhub.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "refresh_token")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int refreshTokenId;

    @Column(name = "refreshTokenValue", length = 2048)
    private String refreshTokenValue;

    @Column(name = "expiredDate")
    private LocalDateTime expiredDate;

    @Column(name = "createDate")
    private LocalDate CreateDate;

    @Column(name = "isUsed")
    private boolean isUsed;

    @Column(name = "isRevoked")
    private boolean isRevoked;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public RefreshToken() {}

    public RefreshToken(
            String refreshTokenValue,
            LocalDateTime expiredDate,
            LocalDate createDate,
            boolean isUsed,
            boolean isRevoked,
            User user) {
        this.refreshTokenValue = refreshTokenValue;
        this.expiredDate = expiredDate;
        CreateDate = createDate;
        this.isUsed = isUsed;
        this.isRevoked = isRevoked;
        this.user = user;
    }

    public int getRefreshTokenId() {
        return refreshTokenId;
    }

    public void setRefreshTokenId(int refreshTokenId) {
        this.refreshTokenId = refreshTokenId;
    }

    public String getRefreshTokenValue() {
        return refreshTokenValue;
    }

    public void setRefreshTokenValue(String refreshTokenValue) {
        this.refreshTokenValue = refreshTokenValue;
    }

    public LocalDateTime getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDateTime expiredDate) {
        this.expiredDate = expiredDate;
    }

    public LocalDate getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(LocalDate createDate) {
        CreateDate = createDate;
    }

    public boolean getUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public boolean getRevoked() {
        return isRevoked;
    }

    public void setRevoked(boolean revoked) {
        isRevoked = revoked;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
