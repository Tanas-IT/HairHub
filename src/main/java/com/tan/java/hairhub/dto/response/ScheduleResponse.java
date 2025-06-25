package com.tan.java.hairhub.dto.response;

import com.tan.java.hairhub.entities.Combo;
import com.tan.java.hairhub.entities.WorkLog;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleResponse {

    private int scheduleId;

    private String scheduleName;

    private String dayOfWeek;

    private LocalDate startDate;

    private LocalDate endDate;

    private Combo combo;

    private List<WorkLog> workLogs;
}
