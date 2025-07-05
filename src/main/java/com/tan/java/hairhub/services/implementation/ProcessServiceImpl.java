package com.tan.java.hairhub.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tan.java.hairhub.dto.request.CreateProcessDTO;
import com.tan.java.hairhub.dto.request.UpdateProcessDTO;
import com.tan.java.hairhub.dto.response.ProcessResponse;
import com.tan.java.hairhub.entities.Process;
import com.tan.java.hairhub.mapper.ProcessMapper;
import com.tan.java.hairhub.repositories.ProcessRepository;
import com.tan.java.hairhub.services.interfaces.ProcessService;

@Service
public class ProcessServiceImpl implements ProcessService {

    private ProcessRepository processRepository;
    private ProcessMapper processMapper;

    @Autowired
    public ProcessServiceImpl(ProcessRepository processRepository, ProcessMapper processMapper) {
        this.processRepository = processRepository;
        this.processMapper = processMapper;
    }

    @Override
    public List<ProcessResponse> getAllProcess(int pageIndex, int pageSize) {
        List<Process> processes = this.processRepository.getAllProcess((pageIndex - 1) * pageSize, pageSize);
        List<ProcessResponse> processResponses = new ArrayList<>();
        processes.forEach(process -> {
            ProcessResponse processResponse = this.processMapper.toProcessResponse(process);
            processResponses.add(processResponse);
        });
        return processResponses;
    }

    @Override
    public ProcessResponse getProcessById(int processId) {
        Optional<Process> getProcess = this.processRepository.findById(processId);
        if (getProcess.isPresent()) {
            ProcessResponse processResponse = this.processMapper.toProcessResponse(getProcess.get());
            return processResponse;
        }
        return null;
    }

    @Override
    public ProcessResponse createProcess(CreateProcessDTO createProcessDTO) {
        Process createProcess = this.processMapper.toProcess(createProcessDTO);
        if (createProcess != null) {
            Process result = this.processRepository.save(createProcess);
            ProcessResponse processResponse = this.processMapper.toProcessResponse(result);
            return processResponse;
        }
        return null;
    }

    @Override
    public Process updateProcess(UpdateProcessDTO updateProcessDTO) throws Exception {
        Optional<Process> checkExistProcess = this.processRepository.findById(updateProcessDTO.getProcessId());
        if (!checkExistProcess.isPresent()) {
            throw new Exception("Process does not exist");
        }
        Process updateProcess = this.processMapper.updateProcess(updateProcessDTO, checkExistProcess.get());
        this.processRepository.save(updateProcess);
        return updateProcess;
    }

    @Override
    public void deleteProcess(int processId) throws Exception {
        Optional<Process> checkExistProcess = this.processRepository.findById(processId);
        if (!checkExistProcess.isPresent()) {
            throw new Exception("Process does not exist");
        }
        this.processRepository.delete(checkExistProcess.get());
    }
}
