package com.tan.java.hairhub.services.implementation;

import com.tan.java.hairhub.dto.request.CreateProcessStepDTO;
import com.tan.java.hairhub.dto.request.UpdateProcessStepDTO;
import com.tan.java.hairhub.dto.response.ProcessResponse;
import com.tan.java.hairhub.dto.response.ProcessStepResponse;
import com.tan.java.hairhub.entities.Process;
import com.tan.java.hairhub.entities.ProcessStep;
import com.tan.java.hairhub.mapper.ProcessStepMapper;
import com.tan.java.hairhub.repositories.ProcessStepRepository;
import com.tan.java.hairhub.services.interfaces.ProcessStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProcessStepServiceImpl implements ProcessStepService {

    private ProcessStepRepository processStepRepository;
    private ProcessStepMapper processStepMapper;

    @Autowired
    public ProcessStepServiceImpl(ProcessStepRepository processStepRepository, ProcessStepMapper processStepMapper) {
        this.processStepRepository = processStepRepository;
        this.processStepMapper = processStepMapper;
    }

    @Override
    public List<ProcessStepResponse> getAllProcessStep(int pageIndex, int pageSize) {
        List<ProcessStep> processSteps = this.processStepRepository.getAllProcessStep(pageIndex, pageSize);
        List<ProcessStepResponse> processStepResponses = new ArrayList<>();
        if(processSteps.isEmpty()) {
            processSteps.forEach(processStep -> {
                ProcessStepResponse processStepResponse = this.processStepMapper.toProcessStepResponse(processStep);
                processStepResponses.add(processStepResponse);
            });
        }
        return processStepResponses;
    }

    @Override
    public ProcessStepResponse getProcessStepById(int processStepId) {
        Optional<ProcessStep> checkExistProcess = this.processStepRepository.findById(processStepId);
        if(checkExistProcess.isPresent()) {
            ProcessStepResponse processStepResponse = this.processStepMapper.toProcessStepResponse(checkExistProcess.get());
            return processStepResponse;
        }
        return null;
    }

    @Override
    public ProcessStepResponse createProcessStep(CreateProcessStepDTO createProcessStepDTO) {
        ProcessStep processStep = this.processStepMapper.createProcess(createProcessStepDTO);
        if(processStep != null) {
            ProcessStep result = this.processStepRepository.save(processStep);
            ProcessStepResponse processResponse = this.processStepMapper.toProcessStepResponse(result);
            return processResponse;
        }
        return null;
    }

    @Override
    public ProcessStep updateProcessStep(UpdateProcessStepDTO updateProcessStepDTO) throws Exception {
        Optional<ProcessStep> checkExistProcess = this.processStepRepository.findById(updateProcessStepDTO.getProcessStepId());
        if(!checkExistProcess.isPresent()) {
            throw new Exception("Process Step does not exist");
        }
        ProcessStep updateProcess = this.processStepMapper.updateProcess(updateProcessStepDTO, checkExistProcess.get());
        this.processStepRepository.save(updateProcess);
        return updateProcess;
    }

    @Override
    public void deleteProcessStep(int processStepId) throws Exception {
        Optional<ProcessStep> checkExistProcess = this.processStepRepository.findById(processStepId);
        if(!checkExistProcess.isPresent()) {
            throw new Exception("Process Step does not exist");
        }
        this.processStepRepository.delete(checkExistProcess.get());
    }
}
