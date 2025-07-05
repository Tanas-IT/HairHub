package com.tan.java.hairhub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tan.java.hairhub.dto.request.CreateComboDTO;
import com.tan.java.hairhub.dto.request.UpdateComboDTO;
import com.tan.java.hairhub.dto.response.ApiResponse;
import com.tan.java.hairhub.dto.response.ComboResponse;
import com.tan.java.hairhub.entities.Combo;
import com.tan.java.hairhub.services.interfaces.ComboService;

@RestController
@RequestMapping("/api/combo")
public class ComboController {

    private ComboService comboService;

    @Autowired
    public ComboController(ComboService comboService) {
        this.comboService = comboService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<ComboResponse>>> getAllCombo(
            @RequestParam int pageIndex, @RequestParam int pageSize) {
        List<ComboResponse> listComboResponse = this.comboService.getAllCombo(pageIndex, pageSize);
        ApiResponse<List<ComboResponse>> apiResponse = new ApiResponse<>();
        if (!listComboResponse.isEmpty()) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Get all combo success");
            apiResponse.setData(listComboResponse);

            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiResponse.setMessage("Do not have any Combo");
        apiResponse.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ComboResponse>> getComboById(@PathVariable int id) {
        ComboResponse ComboResponse = this.comboService.getComboById(id);
        ApiResponse<ComboResponse> apiResponse = new ApiResponse<>();
        if (ComboResponse != null) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Get combo by id success");
            apiResponse.setData(ComboResponse);

            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }

        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiResponse.setMessage("Do not have any combo with that id");
        apiResponse.setData(null);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ComboResponse>> createCombo(@RequestBody CreateComboDTO createComboDTO) {
        ComboResponse Combo = this.comboService.createCombo(createComboDTO);
        ApiResponse<ComboResponse> apiResponse = new ApiResponse<>();
        if (Combo != null) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Create combo success");
            apiResponse.setData(Combo);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage("Create combo failed");
        apiResponse.setData(null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<Combo>> updateCombo(@RequestBody UpdateComboDTO updateComboDTO) throws Exception {
        var ComboUpdate = this.comboService.updateCombo(updateComboDTO);
        ApiResponse<Combo> apiResponse = new ApiResponse<>();
        if (ComboUpdate != null) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Update combo success");
            apiResponse.setData(ComboUpdate);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }

        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage("Update combo failed");
        apiResponse.setData(null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCombo(@PathVariable int id) throws Exception {
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        try {
            this.comboService.deleteCombo(id);
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Delete combo success");
            apiResponse.setData(null);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (Exception ex) {
            apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Delete combo failed");
            apiResponse.setData(null);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
    }
}
