package com.tan.java.hairhub.services.interfaces;

import java.util.List;

import com.tan.java.hairhub.dto.request.CreateScheduleDTO;
import com.tan.java.hairhub.dto.request.UpdateScheduleDTO;
import com.tan.java.hairhub.dto.response.ScheduleResponse;
import com.tan.java.hairhub.entities.Schedule;

public interface ScheduleService {
    List<ScheduleResponse> getAllSchedule(int pageIndex, int pageSize);

    ScheduleResponse getScheduleById(int scheduleId);

    ScheduleResponse createSchedule(CreateScheduleDTO createScheduleDTO);

    Schedule updateSchedule(UpdateScheduleDTO updateScheduleDTO) throws Exception;

    void deleteSchedule(int stepId) throws Exception;
}
