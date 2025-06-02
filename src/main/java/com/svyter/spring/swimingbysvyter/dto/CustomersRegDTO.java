package com.svyter.spring.swimingbysvyter.dto;

import com.svyter.spring.swimingbysvyter.dto.base.DTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;


@Data
public class CustomersRegDTO implements DTO {
    @NotBlank
    private String login;
    @Email(message = "Email должен быть действительным")
    private String email;
    @NotBlank
    private String pass;

}
