package com.tan.java.hairhub.services.interfaces;

import java.util.List;

import com.tan.java.hairhub.dto.request.CreateProcessStepDTO;
import com.tan.java.hairhub.dto.request.UpdateProcessStepDTO;
import com.tan.java.hairhub.dto.response.ProcessStepResponse;
import com.tan.java.hairhub.entities.ProcessStep;

public interface ProcessStepService {
    List<ProcessStepResponse> getAllProcessStep(int pageIndex, int pageSize);

    ProcessStepResponse getProcessStepById(int processStepId);

    ProcessStepResponse createProcessStep(CreateProcessStepDTO createProcessStepDTO);

    ProcessStep updateProcessStep(UpdateProcessStepDTO updateProcessStepDTO) throws Exception;

    void deleteProcessStep(int processStepId) throws Exception;
}
