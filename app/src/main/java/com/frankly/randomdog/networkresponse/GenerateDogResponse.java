package com.frankly.randomdog.networkresponse;

public class GenerateDogResponse {

    private String message;
    private String status;

    public GenerateDogResponse(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
