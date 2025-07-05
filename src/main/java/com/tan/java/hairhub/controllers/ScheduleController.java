package com.tan.java.hairhub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tan.java.hairhub.dto.request.CreateScheduleDTO;
import com.tan.java.hairhub.dto.request.UpdateScheduleDTO;
import com.tan.java.hairhub.dto.response.ApiResponse;
import com.tan.java.hairhub.dto.response.ScheduleResponse;
import com.tan.java.hairhub.entities.Schedule;
import com.tan.java.hairhub.services.interfaces.ScheduleService;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    private ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<ScheduleResponse>>> getAllSchedule(
            @RequestParam int pageIndex, @RequestParam int pageSize) {
        List<ScheduleResponse> listScheduleResponse = this.scheduleService.getAllSchedule(pageIndex, pageSize);
        ApiResponse<List<ScheduleResponse>> apiResponse = new ApiResponse<>();
        if (!listScheduleResponse.isEmpty()) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Get all schedule success");
            apiResponse.setData(listScheduleResponse);

            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiResponse.setMessage("Do not have any Schedule");
        apiResponse.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ScheduleResponse>> getScheduleById(@PathVariable int id) {
        ScheduleResponse scheduleResponse = this.scheduleService.getScheduleById(id);
        ApiResponse<ScheduleResponse> apiResponse = new ApiResponse<>();
        if (scheduleResponse != null) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Get schedule by id success");
            apiResponse.setData(scheduleResponse);

            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }

        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiResponse.setMessage("Do not have any schedule with that id");
        apiResponse.setData(null);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ScheduleResponse>> createSchedule(
            @RequestBody CreateScheduleDTO createScheduleDTO) {
        ScheduleResponse schedule = this.scheduleService.createSchedule(createScheduleDTO);
        ApiResponse<ScheduleResponse> apiResponse = new ApiResponse<>();
        if (schedule != null) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Create schedule success");
            apiResponse.setData(schedule);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage("Create schedule failed");
        apiResponse.setData(null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<com.tan.java.hairhub.entities.Schedule>> updateSchedule(
            @RequestBody UpdateScheduleDTO updateScheduleDTO) throws Exception {
        var scheduleUpdate = this.scheduleService.updateSchedule(updateScheduleDTO);
        ApiResponse<Schedule> apiResponse = new ApiResponse<>();
        if (scheduleUpdate != null) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Update schedule success");
            apiResponse.setData(scheduleUpdate);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }

        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage("Update schedule failed");
        apiResponse.setData(null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSchedule(@PathVariable int id) throws Exception {
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        try {
            this.scheduleService.deleteSchedule(id);
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Delete schedule success");
            apiResponse.setData(null);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (Exception ex) {
            apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Delete schedule failed");
            apiResponse.setData(null);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
    }
}
