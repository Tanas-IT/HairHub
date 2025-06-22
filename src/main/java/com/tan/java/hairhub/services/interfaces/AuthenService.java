package com.tan.java.hairhub.services.interfaces;

import java.text.ParseException;

import com.nimbusds.jose.JOSEException;
import com.tan.java.hairhub.dto.request.AuthenticationRequest;
import com.tan.java.hairhub.dto.request.LogoutRequest;
import com.tan.java.hairhub.dto.request.RefreshTokenRequest;
import com.tan.java.hairhub.dto.response.TokenResponse;
import com.tan.java.hairhub.dto.response.ValidateTokenResponse;

public interface AuthenService {
    TokenResponse login(AuthenticationRequest authenticationRequest);

    ValidateTokenResponse validateToken(String accessToken);

    void logout(LogoutRequest logoutRequest) throws JOSEException, ParseException;

    TokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
