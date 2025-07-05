package com.tan.java.hairhub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tan.java.hairhub.entities.Process;

public interface ProcessRepository extends JpaRepository<Process, Integer> {
    @Query(value = "SELECT * From process LIMIT :pageSize OFFSET :pageIndex", nativeQuery = true)
    List<Process> getAllProcess(int pageIndex, int pageSize);
}
