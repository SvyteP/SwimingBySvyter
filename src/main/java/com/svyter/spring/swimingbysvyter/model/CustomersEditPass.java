package com.svyter.spring.swimingbysvyter.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomersEditPass {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    private String pass;
    private String admin;
}
