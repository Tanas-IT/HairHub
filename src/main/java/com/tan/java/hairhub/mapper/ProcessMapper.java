package com.tan.java.hairhub.mapper;

import com.tan.java.hairhub.dto.request.CreateProcessDTO;
import com.tan.java.hairhub.dto.request.UpdateProcessDTO;
import com.tan.java.hairhub.dto.response.ProcessResponse;
import com.tan.java.hairhub.entities.Process;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProcessMapper {
    Process toProcess(CreateProcessDTO createProcessDTO);

    ProcessResponse toProcessResponse(Process process);
    Process updateProcess(UpdateProcessDTO updateProcessDTO, @MappingTarget  Process process);
}
