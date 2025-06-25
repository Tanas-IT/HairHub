package com.tan.java.hairhub.services.interfaces;

import com.tan.java.hairhub.dto.request.CreateProcessDTO;
import com.tan.java.hairhub.dto.request.UpdateProcessDTO;
import com.tan.java.hairhub.dto.response.ProcessResponse;
import com.tan.java.hairhub.entities.Process;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProcessService {

    List<ProcessResponse> getAllProcess(int pageIndex, int pageSize);

    ProcessResponse getProcessById(int processId);
    ProcessResponse createProcess(CreateProcessDTO createProcessDTO);

    Process updateProcess(UpdateProcessDTO updateProcessDTO) throws Exception;
    void deleteProcess(int processId) throws Exception;
}
