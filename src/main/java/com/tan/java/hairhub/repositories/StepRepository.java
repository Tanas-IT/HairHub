package com.tan.java.hairhub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tan.java.hairhub.entities.Step;

public interface StepRepository extends JpaRepository<Step, Integer> {

    @Query(value = "SELECT * From step LIMIT :pageSize OFFSET :pageIndex", nativeQuery = true)
    List<Step> getAllStep(int pageIndex, int pageSize);
}
