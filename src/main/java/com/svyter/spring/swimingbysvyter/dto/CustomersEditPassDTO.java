package com.svyter.spring.swimingbysvyter.dto;

import com.svyter.spring.swimingbysvyter.dto.base.DTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomersEditPassDTO implements DTO {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    private String pass;
}
