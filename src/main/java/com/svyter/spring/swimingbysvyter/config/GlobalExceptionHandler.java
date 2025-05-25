package com.svyter.spring.swimingbysvyter.config;

import com.svyter.spring.swimingbysvyter.dto.base.ErrorResponseDTO;
import com.svyter.spring.swimingbysvyter.dto.base.ResponseDTO;
import com.svyter.spring.swimingbysvyter.exception.DataAlreadyExistException;
import com.svyter.spring.swimingbysvyter.exception.NotFoundDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundDataException.class)
    public ResponseEntity<ErrorResponseDTO> notFoundDataException (Exception e) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(e.getMessage(), getStackTrace(e));
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
    }

    @ExceptionHandler(DataAlreadyExistException.class)
    public ResponseEntity<ErrorResponseDTO> dataAlreadyExistException(Exception e) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(e.getMessage(), getStackTrace(e));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponseDTO);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponseDTO> ThrowableException(Exception e) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(e.getMessage(), getStackTrace(e));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDTO);
    }


    public static String getStackTrace(Exception e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
