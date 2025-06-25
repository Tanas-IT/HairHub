package com.tan.java.hairhub.repositories;

import com.tan.java.hairhub.entities.Process;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProcessRepository extends JpaRepository<Process, Integer> {
    @Query(value = "SELECT * From process LIMIT :pageSize OFFSET :pageIndex", nativeQuery = true)
    List<Process> getAllProcess(int pageIndex, int pageSize);
}
