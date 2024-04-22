package com.svyter.spring.swimingbysvyter.model;

import lombok.Data;

@Data
public class CustomersRegModel {
    private String login;
    private String email;
    private String pass;
    private boolean isAdmin;
}
