package com.tan.java.hairhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tan.java.hairhub.entities.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {

    @Modifying
    @Query(value = "Delete From RefreshToken r Where r.refreshTokenValue = ?1 ")
    void deleteByRefreshTokenValue(String refreshTokenValue);
}
