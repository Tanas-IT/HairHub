package com.tan.java.hairhub.mapper;

import com.tan.java.hairhub.dto.request.CreateProcessDTO;
import com.tan.java.hairhub.dto.request.CreateProcessStepDTO;
import com.tan.java.hairhub.dto.request.UpdateProcessDTO;
import com.tan.java.hairhub.dto.request.UpdateProcessStepDTO;
import com.tan.java.hairhub.dto.response.ProcessResponse;
import com.tan.java.hairhub.dto.response.ProcessStepResponse;
import com.tan.java.hairhub.entities.Process;
import com.tan.java.hairhub.entities.ProcessStep;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProcessStepMapper {

    ProcessStepResponse toProcessStepResponse(ProcessStep processStep);

    ProcessStep createProcess(CreateProcessStepDTO createProcessDTO);
    ProcessStep updateProcess(UpdateProcessStepDTO updateProcessDTO, @MappingTarget ProcessStep process);
}
