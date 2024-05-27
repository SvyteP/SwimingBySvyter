package com.svyter.spring.swimingbysvyter.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomersRegModel {
    @NotBlank
    private String login;
    @Email(message = "Email должен быть действительным")
    private String email;
    @NotBlank
    private String pass;
    private String isAdmin;
}
