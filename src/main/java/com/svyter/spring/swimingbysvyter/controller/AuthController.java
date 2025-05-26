package com.svyter.spring.swimingbysvyter.controller;

import com.svyter.spring.swimingbysvyter.dto.CustomerLoginDTO;
import com.svyter.spring.swimingbysvyter.dto.CustomersGetDTO;
import com.svyter.spring.swimingbysvyter.dto.CustomersGetWithTokenDTO;
import com.svyter.spring.swimingbysvyter.dto.CustomersRegDTO;
import com.svyter.spring.swimingbysvyter.dto.base.DTO;
import com.svyter.spring.swimingbysvyter.dto.base.ResponseDTO;
import com.svyter.spring.swimingbysvyter.service.AuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthServices authServices;

    @Autowired
    public AuthController(AuthServices authServices) {
        this.authServices = authServices;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<CustomersGetWithTokenDTO>> login(@RequestBody CustomerLoginDTO customerLoginDTO) {
            ResponseDTO<CustomersGetWithTokenDTO> response = authServices.login(customerLoginDTO);
            return ResponseEntity.ok().body(response);
    }
    @PostMapping("/reg")
    public ResponseEntity<ResponseDTO<CustomersGetWithTokenDTO>> registration(@RequestBody CustomersRegDTO customersRegDTO) {
        ResponseDTO<CustomersGetWithTokenDTO> response = authServices.registrationCustomer(customersRegDTO);
        return ResponseEntity.ok().body(response);
    }
}
