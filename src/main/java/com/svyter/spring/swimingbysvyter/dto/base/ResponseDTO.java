package com.svyter.spring.swimingbysvyter.dto.base;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseDTO <T> implements DTO{
    private T data;
    private LocalDateTime dateTime;

    public ResponseDTO(T data) {
        this.data = data;
        this.dateTime = LocalDateTime.now();
    }
}
