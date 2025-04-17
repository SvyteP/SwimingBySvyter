package com.svyter.spring.swimingbysvyter.dto.base;

import java.time.LocalDateTime;
import java.util.Date;

public class ResponseDTO <T>{
    private T data;
    private LocalDateTime dateTime;

    public ResponseDTO(T data) {
        this.data = data;
        this.dateTime = LocalDateTime.now();
    }
}
