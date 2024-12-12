package com.example.library.handler;

import java.util.List;

public class ErrorResponse {

    private String errorCode;
    private int status;
    private String errorMessage;
    private List<String> details;

    public ErrorResponse(String errorCode, int status, String message) {
        this.errorCode = errorCode;
        this.status = status;
        this.errorMessage = message;
    }

    public ErrorResponse(String errorCode, int status, String message, List<String> details) {
        this.errorCode = errorCode;
        this.status = status;
        this.errorMessage = message;
        this.details = details;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

}
