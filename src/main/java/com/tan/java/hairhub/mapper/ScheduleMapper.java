package com.tan.java.hairhub.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.tan.java.hairhub.dto.request.CreateScheduleDTO;
import com.tan.java.hairhub.dto.request.UpdateScheduleDTO;
import com.tan.java.hairhub.dto.response.ScheduleResponse;
import com.tan.java.hairhub.entities.Schedule;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ScheduleMapper {

    ScheduleResponse toScheduleResponse(Schedule schedule);

    Schedule createSchedule(CreateScheduleDTO createScheduleDTO);

    Schedule updateSchedule(UpdateScheduleDTO updateScheduleDTO, @MappingTarget Schedule schedule);
}
