package com.svyter.spring.swimingbysvyter.service;

import com.svyter.spring.swimingbysvyter.dto.CustomerLoginDTO;
import com.svyter.spring.swimingbysvyter.dto.CustomersGetDTO;
import com.svyter.spring.swimingbysvyter.dto.CustomersGetWithTokenDTO;
import com.svyter.spring.swimingbysvyter.dto.CustomersRegDTO;
import com.svyter.spring.swimingbysvyter.dto.base.ResponseDTO;

public interface AuthServices {
    ResponseDTO<CustomersGetWithTokenDTO>  registrationCustomer(CustomersRegDTO regModel);
    void blockedCustomer(Long customerId);
    ResponseDTO<CustomersGetWithTokenDTO> login(CustomerLoginDTO customerLoginDTO);
}
