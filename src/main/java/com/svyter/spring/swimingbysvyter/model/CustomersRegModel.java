package com.svyter.spring.swimingbysvyter.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomersRegModel {
    private String login;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    private String pass;
    private boolean isAdmin;
}
