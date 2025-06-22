package com.tan.java.hairhub.controllers;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.nimbusds.jose.JOSEException;
import com.tan.java.hairhub.dto.request.AuthenticationRequest;
import com.tan.java.hairhub.dto.request.LogoutRequest;
import com.tan.java.hairhub.dto.request.RefreshTokenRequest;
import com.tan.java.hairhub.dto.request.ValidateTokenRequest;
import com.tan.java.hairhub.dto.response.ApiResponse;
import com.tan.java.hairhub.dto.response.TokenResponse;
import com.tan.java.hairhub.dto.response.ValidateTokenResponse;
import com.tan.java.hairhub.services.interfaces.AuthenService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/authen")
public class AuthenticationController {

    private AuthenService authenService;

    @Autowired
    public AuthenticationController(AuthenService authenService) {
        this.authenService = authenService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponse>> login(@RequestBody AuthenticationRequest authenticationRequest) {
        TokenResponse result = this.authenService.login(authenticationRequest);
        ApiResponse<TokenResponse> apiResponse = new ApiResponse<>();
        if (result.isAuthenticated()) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Login success");
            apiResponse.setData(result);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage("Login failed");
        apiResponse.setData(result);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @PostMapping("/validate-token")
    public ResponseEntity<ApiResponse<ValidateTokenResponse>> validateToken(
            @RequestBody ValidateTokenRequest validateTokenRequest) {
        ValidateTokenResponse validateTokenResponse =
                this.authenService.validateToken(validateTokenRequest.getAccessToken());
        ApiResponse<ValidateTokenResponse> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(200);
        apiResponse.setMessage("Validate token " + validateTokenResponse.isValid());
        apiResponse.setData(validateTokenResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<String> checkUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: " + authentication.getName());
        log.info("Author: " + authentication.getAuthorities());
        authentication.getAuthorities().forEach(x -> log.info(x.getAuthority()));
        return ResponseEntity.ok().body(authentication.getAuthorities().toString());
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(@RequestBody LogoutRequest logoutRequest)
            throws ParseException, JOSEException {
        this.authenService.logout(logoutRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse<TokenResponse>> refreshToken(
            @RequestBody RefreshTokenRequest refreshTokenRequest) {
        TokenResponse tokenResponse = this.authenService.refreshToken(refreshTokenRequest);
        if (tokenResponse != null) {
            ApiResponse<TokenResponse> apiResponse = new ApiResponse<>();
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Refresh token success");
            apiResponse.setData(tokenResponse);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
