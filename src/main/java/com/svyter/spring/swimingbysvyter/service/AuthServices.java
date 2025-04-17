package com.svyter.spring.swimingbysvyter.service;

import com.svyter.spring.swimingbysvyter.dto.CustomerLoginDTO;
import com.svyter.spring.swimingbysvyter.dto.CustomersGetDTO;
import com.svyter.spring.swimingbysvyter.dto.CustomersRegDTO;
import com.svyter.spring.swimingbysvyter.dto.base.ResponseDTO;

public interface AuthServices {
    ResponseDTO<CustomersGetDTO>  registrationCustomer(CustomersRegDTO regModel);
    void blockedCustomer(Long customerId);
    ResponseDTO<CustomersGetDTO> login(CustomerLoginDTO customerLoginDTO);
}
