package com.tan.java.hairhub.services.implementation;

import com.tan.java.hairhub.dto.request.CreateStepDTO;
import com.tan.java.hairhub.dto.request.UpdateStepDTO;
import com.tan.java.hairhub.dto.response.StepResponse;
import com.tan.java.hairhub.entities.Step;
import com.tan.java.hairhub.mapper.StepMapper;
import com.tan.java.hairhub.repositories.StepRepository;
import com.tan.java.hairhub.services.interfaces.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StepServiceImpl implements StepService {

    private StepRepository stepRepository;
    private StepMapper stepMapper;

    @Autowired
    public StepServiceImpl(StepRepository stepRepository, StepMapper stepMapper) {
        this.stepRepository = stepRepository;
        this.stepMapper = stepMapper;
    }


    @Override
    public List<StepResponse> getAllStep(int pageIndex, int pageSize) {
        List<Step> listStep = this.stepRepository.getAllStep(pageIndex, pageSize);
        List<StepResponse> listStepResponse = new ArrayList<>();
        if(!listStep.isEmpty()) {
            listStep.forEach(step -> {
                StepResponse stepResponse = this.stepMapper.toStepResponse(step);
                listStepResponse.add(stepResponse);
            });

        }
        return listStepResponse;
    }

    @Override
    public StepResponse getStepById(int stepId) {
        Optional<Step> step = this.stepRepository.findById(stepId);
        if(step.isPresent()) {
            StepResponse stepResponse = this.stepMapper.toStepResponse(step.get());
            return stepResponse;
        }
        return null;
    }

    @Override
    public StepResponse createStep(CreateStepDTO createStepDTO) {
        Step createStep = this.stepMapper.toStep(createStepDTO);
        if(createStep != null) {
           Step result = this.stepRepository.save(createStep);
           StepResponse stepResponse = this.stepMapper.toStepResponse(result);
           return stepResponse;
        }
        return null;
    }

    @Override
    public Step updateStep(UpdateStepDTO updateStepDTO) throws Exception {
        Optional<Step> checkStepExist = this.stepRepository.findById(updateStepDTO.getStepId());
        if(!checkStepExist.isPresent()) {
            throw new Exception("Step does not exist");
        }
        Step updateStep = this.stepMapper.updateStep(updateStepDTO, checkStepExist.get());
       this.stepRepository.save(updateStep);
        return updateStep;
    }

    @Override
    public void deleteStep(int stepId) throws Exception {
        Optional<Step> checkStepExist = this.stepRepository.findById(stepId);
        if(!checkStepExist.isPresent()) {
            throw new Exception("Step does not exist");
        }
        this.stepRepository.delete(checkStepExist.get());
    }
}
