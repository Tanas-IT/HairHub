package com.tan.java.hairhub.controllers;

import com.tan.java.hairhub.dto.request.CreateProcessStepDTO;
import com.tan.java.hairhub.dto.request.UpdateProcessStepDTO;
import com.tan.java.hairhub.dto.response.ApiResponse;
import com.tan.java.hairhub.dto.response.ProcessStepResponse;
import com.tan.java.hairhub.entities.ProcessStep;
import com.tan.java.hairhub.services.interfaces.ProcessStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/processStepStep")
public class ProcessStepController {
    
    private ProcessStepService processStepService;
    
    @Autowired
    public ProcessStepController(ProcessStepService processStepService) {
        this.processStepService = processStepService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<ProcessStepResponse>>> getAllprocessStep(@RequestParam int pageIndex, @RequestParam int pageSize) {
        List<ProcessStepResponse> listprocessStepResponse = this.processStepService.getAllProcessStep(pageIndex, pageSize);
        ApiResponse<List<ProcessStepResponse>> apiResponse = new ApiResponse<>();
        if(!listprocessStepResponse.isEmpty()) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Get all processStep success");
            apiResponse.setData(listprocessStepResponse);

            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiResponse.setMessage("Do not have any processStep");
        apiResponse.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProcessStepResponse>> getprocessStepById(@PathVariable int id) {
        ProcessStepResponse processStepResponse = this.processStepService.getProcessStepById(id);
        ApiResponse<ProcessStepResponse> apiResponse = new ApiResponse<>();
        if(processStepResponse != null) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Get processStep by id success");
            apiResponse.setData(processStepResponse);

            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }

        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiResponse.setMessage("Do not have any processStep with that id");
        apiResponse.setData(null);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ProcessStepResponse>> createprocessStep(@RequestBody CreateProcessStepDTO createProcessStepDTO) {
        ProcessStepResponse processStep = this.processStepService.createProcessStep(createProcessStepDTO);
        ApiResponse<ProcessStepResponse> apiResponse = new ApiResponse<>();
        if(processStep != null) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Create processStep success");
            apiResponse.setData(processStep);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage("Create processStep failed");
        apiResponse.setData(null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<ProcessStep>> updateprocessStep(@RequestBody UpdateProcessStepDTO updateProcessStepDTO) throws Exception {
        var processStepUpdate = this.processStepService.updateProcessStep(updateProcessStepDTO);
        ApiResponse<ProcessStep> apiResponse = new ApiResponse<>();
        if(processStepUpdate != null) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Update processStep success");
            apiResponse.setData(processStepUpdate);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }

        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage("Update processStep failed");
        apiResponse.setData(null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteprocessStep(@PathVariable int id) throws Exception {
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        try {
            this.processStepService.deleteProcessStep(id);
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Delete processStep success");
            apiResponse.setData(null);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (Exception ex) {
            apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Delete processStep failed");
            apiResponse.setData(null);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
    }
}
