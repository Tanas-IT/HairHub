package com.tan.java.hairhub.repositories;

import com.tan.java.hairhub.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    @Query(value = "Select * From Schedule LIMIT :pageSize OFFSET :pageIndex", nativeQuery = true)
    List<Schedule> getAllSchedule(int pageIndex, int pageSize);
}
