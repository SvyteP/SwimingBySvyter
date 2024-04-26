package com.svyter.spring.swimingbysvyter.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomersRegModel {
    private String login;
    @NotBlank(message = "Email is required")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",message = "Invalid email format")
    private String email;
    private String pass;
    private String admin;
}
