package com.tan.java.hairhub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tan.java.hairhub.dto.request.CreateProfileDTO;
import com.tan.java.hairhub.dto.request.UpdateProfileDTO;
import com.tan.java.hairhub.dto.response.ApiResponse;
import com.tan.java.hairhub.dto.response.ProfileResponse;
import com.tan.java.hairhub.entities.Profile;
import com.tan.java.hairhub.services.interfaces.ProfileService;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<ProfileResponse>>> getAllProfile(
            @RequestParam int pageIndex, @RequestParam int pageSize) {
        List<ProfileResponse> listProfileResponse = this.profileService.getAllProfile(pageIndex, pageSize);
        ApiResponse<List<ProfileResponse>> apiResponse = new ApiResponse<>();
        if (!listProfileResponse.isEmpty()) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Get all profile success");
            apiResponse.setData(listProfileResponse);

            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiResponse.setMessage("Do not have any profile");
        apiResponse.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProfileResponse>> getProfileById(@PathVariable int id) {
        ProfileResponse profileResponse = this.profileService.getProfileById(id);
        ApiResponse<ProfileResponse> apiResponse = new ApiResponse<>();
        if (profileResponse != null) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Get profile by id success");
            apiResponse.setData(profileResponse);

            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }

        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiResponse.setMessage("Do not have any profile with that id");
        apiResponse.setData(null);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ProfileResponse>> createProfile(@RequestBody CreateProfileDTO createProfileDTO) {
        ProfileResponse profile = this.profileService.createProfile(createProfileDTO);
        ApiResponse<ProfileResponse> apiResponse = new ApiResponse<>();
        if (profile != null) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Create profile success");
            apiResponse.setData(profile);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage("Create profile failed");
        apiResponse.setData(null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<com.tan.java.hairhub.entities.Profile>> updateProfile(
            @RequestBody UpdateProfileDTO updateProfileDTO) throws Exception {
        var ProfileUpdate = this.profileService.updateProfile(updateProfileDTO);
        ApiResponse<Profile> apiResponse = new ApiResponse<>();
        if (ProfileUpdate != null) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Update profile success");
            apiResponse.setData(ProfileUpdate);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }

        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage("Update profile failed");
        apiResponse.setData(null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProfile(@PathVariable int id) throws Exception {
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        try {
            this.profileService.deleteProfile(id);
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Delete profile success");
            apiResponse.setData(null);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (Exception ex) {
            apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Delete profile failed");
            apiResponse.setData(null);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
    }
}
