package com.tan.java.hairhub.services.interfaces;

import com.tan.java.hairhub.dto.request.CreateProcessStepDTO;
import com.tan.java.hairhub.dto.request.UpdateProcessStepDTO;
import com.tan.java.hairhub.dto.response.ProcessResponse;
import com.tan.java.hairhub.dto.response.ProcessStepResponse;
import com.tan.java.hairhub.entities.ProcessStep;

import java.util.List;

public interface ProcessStepService {
    List<ProcessStepResponse> getAllProcessStep(int pageIndex, int pageSize);

    ProcessStepResponse getProcessStepById(int processStepId);
    ProcessStepResponse createProcessStep(CreateProcessStepDTO createProcessStepDTO);

    ProcessStep updateProcessStep(UpdateProcessStepDTO updateProcessStepDTO) throws Exception;
    void deleteProcessStep(int processStepId) throws Exception;
}
