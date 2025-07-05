package com.tan.java.hairhub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tan.java.hairhub.dto.request.CreateStepDTO;
import com.tan.java.hairhub.dto.request.UpdateStepDTO;
import com.tan.java.hairhub.dto.response.ApiResponse;
import com.tan.java.hairhub.dto.response.StepResponse;
import com.tan.java.hairhub.entities.Step;
import com.tan.java.hairhub.services.interfaces.StepService;

@RestController
@RequestMapping("/api/step")
public class StepController {

    private StepService stepService;

    @Autowired
    public StepController(StepService stepService) {
        this.stepService = stepService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<StepResponse>>> getAllStep(
            @RequestParam int pageIndex, @RequestParam int pageSize) {
        List<StepResponse> listStepResponse = this.stepService.getAllStep(pageIndex, pageSize);
        ApiResponse<List<StepResponse>> apiResponse = new ApiResponse<>();
        if (!listStepResponse.isEmpty()) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Get all dtep success");
            apiResponse.setData(listStepResponse);

            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiResponse.setMessage("Do not have any step");
        apiResponse.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StepResponse>> getStepById(@PathVariable int id) {
        StepResponse StepResponse = this.stepService.getStepById(id);
        ApiResponse<StepResponse> apiResponse = new ApiResponse<>();
        if (StepResponse != null) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Get step by id success");
            apiResponse.setData(StepResponse);

            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }

        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiResponse.setMessage("Do not have any Step with that id");
        apiResponse.setData(null);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<StepResponse>> createStep(@RequestBody CreateStepDTO createStepDTO) {
        StepResponse step = this.stepService.createStep(createStepDTO);
        ApiResponse<StepResponse> apiResponse = new ApiResponse<>();
        if (step != null) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Create step success");
            apiResponse.setData(step);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage("Create step failed");
        apiResponse.setData(null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<com.tan.java.hairhub.entities.Step>> updateStep(
            @RequestBody UpdateStepDTO updateStepDTO) throws Exception {
        var stepUpdate = this.stepService.updateStep(updateStepDTO);
        ApiResponse<Step> apiResponse = new ApiResponse<>();
        if (stepUpdate != null) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Update step success");
            apiResponse.setData(stepUpdate);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }

        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage("Update step failed");
        apiResponse.setData(null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStep(@PathVariable int id) throws Exception {
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        try {
            this.stepService.deleteStep(id);
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Delete step success");
            apiResponse.setData(null);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (Exception ex) {
            apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Delete step failed");
            apiResponse.setData(null);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
    }
}
