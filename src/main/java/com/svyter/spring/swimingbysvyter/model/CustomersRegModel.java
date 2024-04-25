package com.svyter.spring.swimingbysvyter.model;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class CustomersRegModel {
    private String login;
    @Email(message = "Email должен быть действительным")
    private String email;
    private String pass;
    private boolean isAdmin;
}
