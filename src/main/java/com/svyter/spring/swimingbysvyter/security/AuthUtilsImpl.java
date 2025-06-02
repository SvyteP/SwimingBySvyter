package com.svyter.spring.swimingbysvyter.security;

import com.svyter.spring.swimingbysvyter.dto.CustomersGetWithTokenDTO;
import com.svyter.spring.swimingbysvyter.dto.base.ResponseDTO;
import com.svyter.spring.swimingbysvyter.entity.Customers;
import com.svyter.spring.swimingbysvyter.exception.NotFoundDataException;
import com.svyter.spring.swimingbysvyter.repo.CustomersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class AuthUtilsImpl implements AuthUtils{
    private final AuthenticationManager authenticationManager;
    private final CustomersRepo customersRepo;
    private final MessageSource messageSource;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthUtilsImpl(AuthenticationManager authenticationManager, CustomersRepo customersRepo, MessageSource messageSource, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.customersRepo = customersRepo;
        this.messageSource = messageSource;
        this.jwtUtils = jwtUtils;
    }
    @Override
    public ResponseDTO<CustomersGetWithTokenDTO> authenticationUser(String login, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));

        Customers customers = customersRepo.findByEmail(login).orElseThrow(() -> new NotFoundDataException(messageSource.getMessage(
                "error.customer.notfound",
                new Object[]{"login " + login},
                Locale.getDefault())));

        String token = jwtUtils.generateToken(customers);
        return new ResponseDTO<>(CustomersGetWithTokenDTO.convertCustomersToModel(customers, token));
    }
}
