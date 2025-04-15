package com.svyter.spring.swimingbysvyter.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;


@Data
public class CustomersRegModel {
    @NotBlank
    private String login;
    @Email(message = "Email должен быть действительным")
    private String email;
    @NotBlank
    private String pass;

}
