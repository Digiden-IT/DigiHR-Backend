package com.digiHR.utility.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ApiErrorResponse {
    private int status;
    private String error;
    private String message;
    private String timestamp;

    public ApiErrorResponse( int status, String error, String message ) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }
}
