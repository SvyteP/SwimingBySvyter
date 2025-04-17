package com.svyter.spring.swimingbysvyter.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;


@Data
public class CustomersRegDTO {
    @NotBlank
    private String login;
    @Email(message = "Email должен быть действительным")
    private String email;
    @NotBlank
    private String pass;

}
