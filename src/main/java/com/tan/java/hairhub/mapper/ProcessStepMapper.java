package com.tan.java.hairhub.mapper;

import com.tan.java.hairhub.dto.request.CreateProcessStepDTO;
import com.tan.java.hairhub.dto.request.UpdateProcessStepDTO;
import com.tan.java.hairhub.dto.response.ProcessStepResponse;
import com.tan.java.hairhub.entities.ProcessStep;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProcessStepMapper {

    ProcessStepResponse toDto(ProcessStep entity);
  // tránh quay vòng
    ProcessStepResponse toProcessStepResponse(ProcessStep processStep);

    ProcessStep createProcess(CreateProcessStepDTO createProcessDTO);
    ProcessStep updateProcess(UpdateProcessStepDTO updateProcessDTO, @MappingTarget ProcessStep process);
}
