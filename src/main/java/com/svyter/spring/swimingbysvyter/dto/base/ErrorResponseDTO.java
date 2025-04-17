package com.svyter.spring.swimingbysvyter.dto.base;

import java.time.LocalDateTime;

public class ErrorResponseDTO {
    private String message;
    private String stackTrace;
    private LocalDateTime dateTime;

    public ErrorResponseDTO(String message, String stackTrace) {
        this.message = message;
        this.stackTrace = stackTrace;
        this.dateTime = LocalDateTime.now();
    }
}
