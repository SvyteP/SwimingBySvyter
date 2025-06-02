package com.svyter.spring.swimingbysvyter.security;

import com.svyter.spring.swimingbysvyter.dto.CustomersGetWithTokenDTO;
import com.svyter.spring.swimingbysvyter.dto.base.ResponseDTO;

public interface AuthUtils {
    ResponseDTO<CustomersGetWithTokenDTO> authenticationUser(String login, String password);
}
