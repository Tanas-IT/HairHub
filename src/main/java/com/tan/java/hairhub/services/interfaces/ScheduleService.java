package com.tan.java.hairhub.services.interfaces;

import com.tan.java.hairhub.dto.request.CreateScheduleDTO;
import com.tan.java.hairhub.dto.request.CreateStepDTO;
import com.tan.java.hairhub.dto.request.UpdateScheduleDTO;
import com.tan.java.hairhub.dto.request.UpdateStepDTO;
import com.tan.java.hairhub.dto.response.ScheduleResponse;
import com.tan.java.hairhub.dto.response.StepResponse;
import com.tan.java.hairhub.entities.Schedule;
import com.tan.java.hairhub.entities.Step;

import java.util.List;

public interface ScheduleService {
    List<ScheduleResponse> getAllSchedule(int pageIndex, int pageSize);

    ScheduleResponse getScheduleById(int scheduleId);
    ScheduleResponse createSchedule(CreateScheduleDTO createScheduleDTO);

    Schedule updateSchedule(UpdateScheduleDTO updateScheduleDTO) throws Exception;
    void deleteSchedule(int stepId) throws Exception;
}
