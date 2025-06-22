package com.tan.java.hairhub.repositories;

import com.tan.java.hairhub.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
    @Query(value = "Select * from service LIMIT :pageSize OFFSET :pageIndex", nativeQuery = true)
    List<Service> getAllService(int pageIndex, int pageSize);
}
