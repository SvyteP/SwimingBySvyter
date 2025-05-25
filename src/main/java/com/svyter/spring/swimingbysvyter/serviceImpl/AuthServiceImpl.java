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
import com.svyter.spring.swimingbysvyter.service.AuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Locale;

@Service
public class AuthServiceImpl implements AuthServices {
    private final CustomersRepo customersRepo;
    private final MessageSource messageSource;

    @Autowired
    public AuthServiceImpl(CustomersRepo customersRepo, MessageSource messageSource) {
        this.customersRepo = customersRepo;
        this.messageSource = messageSource;
    }

    @Override
    public ResponseDTO<CustomersGetWithTokenDTO> registrationCustomer(CustomersRegDTO regModel) {
        try {
            if (!customersRepo.existsAllByEmail(regModel.getEmail())) {
                Customers customers = new Customers(regModel.getLogin(),
                        regModel.getPass(),
                        regModel.getEmail());
                CustomersGetWithTokenDTO customersGetWithTokenDTO = CustomersGetWithTokenDTO.convertCustomersToModel(customers,"token");
                customersRepo.save(customers);
                return new ResponseDTO<>(customersGetWithTokenDTO);
            } else {
                throw new DataAlreadyExistException(String.format(
                        messageSource.getMessage("error.customer.already.exist", null, Locale.getDefault()),regModel.getEmail()
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
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

        Customers customers = customersRepo.findByEmail(login)
                .orElseThrow(() -> new NotFoundDataException(
                        String.format(messageSource.getMessage("error.customer.notfound", null, Locale.getDefault()), "login: " + login)
                ));

        CustomersGetWithTokenDTO customersGetWithTokenDTO = CustomersGetWithTokenDTO.convertCustomersToModel(customers,"token");

        return new ResponseDTO<>(customersGetWithTokenDTO);
    }
}
