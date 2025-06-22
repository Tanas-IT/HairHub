package com.tan.java.hairhub.controllers;

import com.tan.java.hairhub.dto.request.CreateStoreDTO;
import com.tan.java.hairhub.dto.request.UpdateStoreDTO;
import com.tan.java.hairhub.dto.response.ApiResponse;
import com.tan.java.hairhub.dto.response.StoreResponse;
import com.tan.java.hairhub.entities.Store;
import com.tan.java.hairhub.services.interfaces.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    private StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<StoreResponse>>> getAllStore(@RequestParam int pageIndex, @RequestParam int pageSize) {
        List<StoreResponse> listStoreResponse = this.storeService.getAllStore(pageIndex, pageSize);
        ApiResponse<List<StoreResponse>> apiResponse = new ApiResponse<>();
        if(listStoreResponse.isEmpty()) {
            apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            apiResponse.setMessage("Does not have any store");
            apiResponse.setData(null);
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setMessage("Get list store success");
        apiResponse.setData(listStoreResponse);
        return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StoreResponse>> getStoreById(@PathVariable int id) {
        StoreResponse storeResponse = this.storeService.getStoreById(id);
        ApiResponse<StoreResponse> apiResponse = new ApiResponse<>();
        if(storeResponse != null) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Get store by id success");
            apiResponse.setData(storeResponse);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiResponse.setMessage("Do not have any store");
        apiResponse.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<StoreResponse>> createStore(@RequestBody CreateStoreDTO createStoreDTO) {
        StoreResponse storeResponse = this.storeService.createStore(createStoreDTO);
        ApiResponse<StoreResponse> apiResponse = new ApiResponse<>();
        if(storeResponse != null) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Create store success");
            apiResponse.setData(storeResponse);

            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage("Create store failed");
        apiResponse.setData(null);
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<Store>> updateStore(@RequestBody UpdateStoreDTO updateStoreDTO) throws Exception {
        Store store = this.storeService.updateStore(updateStoreDTO);
        ApiResponse<Store> apiResponse = new ApiResponse<>();
        if(store != null) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Update store success");
            apiResponse.setData(store);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage("Update store failed");
        apiResponse.setData(null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStore(@PathVariable int id) throws Exception {
        ApiResponse<Void> apiResponse = new ApiResponse<>();
       try {
           this.storeService.deleteStore(id);
           apiResponse.setStatusCode(HttpStatus.OK.value());
           apiResponse.setMessage("Delete stores success");
           apiResponse.setData(null);
           return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
       } catch (Exception e) {
            apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage(e.getMessage());
            apiResponse.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
       }
    }

}
