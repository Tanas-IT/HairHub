package com.tan.java.hairhub.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutRequest {
    private String accessToken;
    private String refreshToken;
}
