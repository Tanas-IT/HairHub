package com.tan.java.hairhub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tan.java.hairhub.dto.request.CreateProcessDTO;
import com.tan.java.hairhub.dto.request.UpdateProcessDTO;
import com.tan.java.hairhub.dto.response.ApiResponse;
import com.tan.java.hairhub.dto.response.ProcessResponse;
import com.tan.java.hairhub.entities.Process;
import com.tan.java.hairhub.services.interfaces.ProcessService;

@RestController
@RequestMapping("/api/process")
public class ProcessController {

    private ProcessService processService;

    @Autowired
    public ProcessController(ProcessService processService) {
        this.processService = processService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<ProcessResponse>>> getAllProcess(
            @RequestParam int pageIndex, @RequestParam int pageSize) {
        List<ProcessResponse> listProcessResponse = this.processService.getAllProcess(pageIndex, pageSize);
        ApiResponse<List<ProcessResponse>> apiResponse = new ApiResponse<>();
        if (!listProcessResponse.isEmpty()) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Get all process success");
            apiResponse.setData(listProcessResponse);

            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiResponse.setMessage("Do not have any process");
        apiResponse.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProcessResponse>> getProcessById(@PathVariable int id) {
        ProcessResponse processResponse = this.processService.getProcessById(id);
        ApiResponse<ProcessResponse> apiResponse = new ApiResponse<>();
        if (processResponse != null) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Get process by id success");
            apiResponse.setData(processResponse);

            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }

        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiResponse.setMessage("Do not have any Process with that id");
        apiResponse.setData(null);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ProcessResponse>> createProcess(@RequestBody CreateProcessDTO createProcessDTO) {
        ProcessResponse process = this.processService.createProcess(createProcessDTO);
        ApiResponse<ProcessResponse> apiResponse = new ApiResponse<>();
        if (process != null) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Create process success");
            apiResponse.setData(process);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage("Create process failed");
        apiResponse.setData(null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<Process>> updateProcess(@RequestBody UpdateProcessDTO updateProcessDTO)
            throws Exception {
        var processUpdate = this.processService.updateProcess(updateProcessDTO);
        ApiResponse<Process> apiResponse = new ApiResponse<>();
        if (processUpdate != null) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Update process success");
            apiResponse.setData(processUpdate);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }

        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage("Update process failed");
        apiResponse.setData(null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProcess(@PathVariable int id) throws Exception {
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        try {
            this.processService.deleteProcess(id);
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Delete process success");
            apiResponse.setData(null);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (Exception ex) {
            apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Delete process failed");
            apiResponse.setData(null);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
    }
}
