package com.tan.java.hairhub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tan.java.hairhub.entities.ProcessStep;

public interface ProcessStepRepository extends JpaRepository<ProcessStep, Integer> {

    @Query(value = "SELECT * FROM ProcessStep LIMIT :pageSize OFFSET :pageIndex", nativeQuery = true)
    List<ProcessStep> getAllProcessStep(int pageIndex, int pageSize);
}
