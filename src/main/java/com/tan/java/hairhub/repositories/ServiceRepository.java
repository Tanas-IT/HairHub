package com.tan.java.hairhub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tan.java.hairhub.entities.Service;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
    @Query(value = "Select * from service LIMIT :pageSize OFFSET :pageIndex", nativeQuery = true)
    List<Service> getAllService(int pageIndex, int pageSize);
}
