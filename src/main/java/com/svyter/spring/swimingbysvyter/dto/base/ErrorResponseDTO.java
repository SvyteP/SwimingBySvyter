package com.svyter.spring.swimingbysvyter.dto.base;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ErrorResponseDTO implements DTO {
    private String message;
    private String stackTrace;
    private LocalDateTime dateTime;

    public ErrorResponseDTO(String message, String stackTrace) {
        this.message = message;
        this.stackTrace = stackTrace;
        this.dateTime = LocalDateTime.now();
    }
}
