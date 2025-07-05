package com.tan.java.hairhub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tan.java.hairhub.dto.request.CreateServiceDTO;
import com.tan.java.hairhub.dto.request.UpdateServiceDTO;
import com.tan.java.hairhub.dto.response.ApiResponse;
import com.tan.java.hairhub.dto.response.ServiceResponse;
import com.tan.java.hairhub.entities.Service;
import com.tan.java.hairhub.services.interfaces.ServiceService;

@RestController
@RequestMapping("/api/service")
public class ServiceController {

    private ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<ServiceResponse>>> getAllService(
            @RequestParam int pageIndex, @RequestParam int pageSize) {
        List<ServiceResponse> listServiceResponse = this.serviceService.getAllService(pageIndex, pageSize);
        ApiResponse<List<ServiceResponse>> apiResponse = new ApiResponse<>();
        if (!listServiceResponse.isEmpty()) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Get all service success");
            apiResponse.setData(listServiceResponse);

            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiResponse.setMessage("Do not have any service");
        apiResponse.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ServiceResponse>> getServiceById(@PathVariable int id) {
        ServiceResponse serviceResponse = this.serviceService.getServiceById(id);
        ApiResponse<ServiceResponse> apiResponse = new ApiResponse<>();
        if (serviceResponse != null) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Get service by id success");
            apiResponse.setData(serviceResponse);

            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }

        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiResponse.setMessage("Do not have any service with that id");
        apiResponse.setData(null);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ServiceResponse>> createService(@RequestBody CreateServiceDTO createServiceDTO) {
        ServiceResponse service = this.serviceService.createService(createServiceDTO);
        ApiResponse<ServiceResponse> apiResponse = new ApiResponse<>();
        if (service != null) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Create service success");
            apiResponse.setData(service);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage("Create service failed");
        apiResponse.setData(null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<Service>> updateService(@RequestBody UpdateServiceDTO updateServiceDTO)
            throws Exception {
        var serviceUpdate = this.serviceService.updateService(updateServiceDTO);
        ApiResponse<Service> apiResponse = new ApiResponse<>();
        if (serviceUpdate != null) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Update service success");
            apiResponse.setData(serviceUpdate);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }

        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage("Update service failed");
        apiResponse.setData(null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteService(@PathVariable int id) throws Exception {
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        try {
            this.serviceService.deleteService(id);
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Delete service success");
            apiResponse.setData(null);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (Exception ex) {
            apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Delete service failed");
            apiResponse.setData(null);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
    }
}
