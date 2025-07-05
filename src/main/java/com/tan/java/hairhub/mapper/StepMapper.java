package com.tan.java.hairhub.mapper;

import com.tan.java.hairhub.dto.request.CreateStepDTO;
import com.tan.java.hairhub.dto.request.UpdateStepDTO;
import com.tan.java.hairhub.dto.response.StepResponse;
import com.tan.java.hairhub.entities.Step;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StepMapper {
    StepResponse toStepResponse(Step step);

    Step toStep(CreateStepDTO createStepDTO);

    Step updateStep(UpdateStepDTO updateStepDTO, @MappingTarget Step step);
}
