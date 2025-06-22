package com.tan.java.hairhub.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tan.java.hairhub.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "Select * From user LIMIT :pageSize OFFSET :pageIndex", nativeQuery = true)
    public List<User> getAllUser(int pageIndex, int pageSize);

    Optional<User> findByEmail(String email);
}
