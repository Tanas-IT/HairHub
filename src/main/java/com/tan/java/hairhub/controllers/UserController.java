package com.tan.java.hairhub.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.tan.java.hairhub.dto.request.CreateUserDTO;
import com.tan.java.hairhub.dto.request.UpdateUserDTO;
import com.tan.java.hairhub.dto.response.ApiResponse;
import com.tan.java.hairhub.dto.response.UserDTO;
import com.tan.java.hairhub.services.interfaces.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAllUser(
            @RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "10") int pageSize) {
        List<UserDTO> listUser = this.userService.getAllUser(pageIndex, pageSize);
        if (listUser != null && !listUser.isEmpty()) {
            ApiResponse<List<UserDTO>> apiResponse = new ApiResponse<>();
            apiResponse.setStatusCode(200);
            apiResponse.setMessage("Get all user success");
            apiResponse.setData(listUser);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserById(@PathVariable int id) {
        UserDTO userDTO = this.userService.getUserById(id);
        if (userDTO != null) {
            ApiResponse<UserDTO> apiResponse = new ApiResponse<>();
            apiResponse.setStatusCode(200);
            apiResponse.setMessage("Get user By Id success");
            apiResponse.setData(userDTO);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/myInfo")
    public ResponseEntity<ApiResponse<UserDTO>> getMyInfo() throws Exception {
        UserDTO userDTO = this.userService.getUserInfo();
        if (userDTO != null) {
            ApiResponse<UserDTO> apiResponse = new ApiResponse<>();
            apiResponse.setStatusCode(200);
            apiResponse.setMessage("Get my info success");
            apiResponse.setData(userDTO);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CreateUserDTO>> createUser(@RequestBody CreateUserDTO createUserDTO)
            throws Exception {
        log.info("Controller: In method createUser");
        CreateUserDTO result = this.userService.createUser(createUserDTO);
        ApiResponse<CreateUserDTO> apiResponse = new ApiResponse<>();
        if (result != null) {
            apiResponse.setStatusCode(201);
            apiResponse.setMessage("Create user success");
            apiResponse.setData(createUserDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        }
        apiResponse.setStatusCode(400);
        apiResponse.setMessage("Create user failed");
        apiResponse.setData(null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<UpdateUserDTO>> updateUser(@RequestBody UpdateUserDTO updateUserDTO) {
        UpdateUserDTO updateUser = this.userService.updateUser(updateUserDTO);
        if (updateUser != null) {
            ApiResponse<UpdateUserDTO> apiResponse = new ApiResponse<>();
            apiResponse.setStatusCode(200);
            apiResponse.setMessage("Update user success");
            apiResponse.setData(updateUser);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteUser(@PathVariable int id) {
        boolean deleteUser = this.userService.deleteUser(id);
        if (deleteUser) {
            ApiResponse<Boolean> apiResponse = new ApiResponse<>();
            apiResponse.setStatusCode(200);
            apiResponse.setMessage("Delete user success");
            apiResponse.setData(true);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
