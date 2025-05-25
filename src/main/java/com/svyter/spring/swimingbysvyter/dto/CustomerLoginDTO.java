package com.svyter.spring.swimingbysvyter.dto;

import lombok.Data;

@Data
public class CustomerLoginDTO {
    private String login;
    private String password;

    public CustomerLoginDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
