package com.tan.java.hairhub.services.interfaces;

import java.util.List;

import com.tan.java.hairhub.dto.request.CreateStepDTO;
import com.tan.java.hairhub.dto.request.UpdateStepDTO;
import com.tan.java.hairhub.dto.response.StepResponse;
import com.tan.java.hairhub.entities.Step;

public interface StepService {
    List<StepResponse> getAllStep(int pageIndex, int pageSize);

    StepResponse getStepById(int stepId);

    StepResponse createStep(CreateStepDTO createStepDTO);

    Step updateStep(UpdateStepDTO updateStepDTO) throws Exception;

    void deleteStep(int stepId) throws Exception;
}
