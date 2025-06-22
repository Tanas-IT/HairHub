package com.tan.java.hairhub.dto.response;

public enum ErrorResponse {
    USER_EXISTED(1001, "User existed");

    private int errorCode;

    private String messageError;

    ErrorResponse() {}

    ErrorResponse(int errorCode, String messageError) {
        this.errorCode = errorCode;
        this.messageError = messageError;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }
}
