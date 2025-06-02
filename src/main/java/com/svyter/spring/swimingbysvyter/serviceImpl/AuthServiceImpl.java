package com.svyter.spring.swimingbysvyter.serviceImpl;

import com.svyter.spring.swimingbysvyter.dto.CustomerLoginDTO;
import com.svyter.spring.swimingbysvyter.dto.CustomersGetDTO;
import com.svyter.spring.swimingbysvyter.dto.CustomersGetWithTokenDTO;
import com.svyter.spring.swimingbysvyter.dto.CustomersRegDTO;
import com.svyter.spring.swimingbysvyter.dto.base.ResponseDTO;
import com.svyter.spring.swimingbysvyter.exception.DataAlreadyExistException;
import com.svyter.spring.swimingbysvyter.exception.NotFoundDataException;
import com.svyter.spring.swimingbysvyter.repo.CustomersRepo;
import com.svyter.spring.swimingbysvyter.entity.Customers;
import com.svyter.spring.swimingbysvyter.security.AuthUtils;
import com.svyter.spring.swimingbysvyter.service.AuthServices;
import com.svyter.spring.swimingbysvyter.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Locale;

@Service
public class AuthServiceImpl implements AuthServices {
    private final CustomersService customersService;
    private final AuthUtils authUtils;
    private final CustomersRepo customersRepo;
    private final MessageSource messageSource;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(CustomersService customersService, AuthUtils authUtils, CustomersRepo customersRepo, MessageSource messageSource, PasswordEncoder passwordEncoder) {
        this.customersService = customersService;
        this.authUtils = authUtils;
        this.customersRepo = customersRepo;
        this.messageSource = messageSource;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseDTO<CustomersGetWithTokenDTO> registrationCustomer(CustomersRegDTO regModel) {
        Customers customer = customersService.createCustomer(regModel);
        Base64.Decoder decoder = Base64.getDecoder();
        String password = new String(decoder.decode(regModel.getPass()), StandardCharsets.UTF_8);
        return authUtils.authenticationUser(customer.getEmail(),password);
    }

    @Override
    public void blockedCustomer(Long customerId) {

    }

    @Override
    public ResponseDTO<CustomersGetWithTokenDTO> login(CustomerLoginDTO customerLoginDTO) {
        Base64.Decoder decoder = Base64.getDecoder();
        // В качестве логина используется почта
        String login = new String(decoder.decode(customerLoginDTO.getLogin()), StandardCharsets.UTF_8);
        String password = new String(decoder.decode(customerLoginDTO.getPassword()), StandardCharsets.UTF_8);
        /*Аунтификация*/
        return authUtils.authenticationUser(login, password);
    }
}
