package com.tan.java.hairhub.dto.request;

import java.time.LocalDate;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateScheduleDTO {

    private int scheduleId;

    private String scheduleName;

    private String dayOfWeek;

    private LocalDate startDate;

    private LocalDate endDate;

    private int comboId;
}
