package com.tan.java.hairhub.repositories;
import com.tan.java.hairhub.entities.ProcessStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProcessStepRepository extends JpaRepository<ProcessStep, Integer> {

    @Query(value = "SELECT * FROM ProcessStep LIMIT :pageSize OFFSET :pageIndex", nativeQuery = true)
    List<ProcessStep> getAllProcessStep(int pageIndex, int pageSize);
}
