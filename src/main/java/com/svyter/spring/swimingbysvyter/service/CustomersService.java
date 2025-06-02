package com.svyter.spring.swimingbysvyter.service;

import com.svyter.spring.swimingbysvyter.dto.CustomersGetDTO;
import com.svyter.spring.swimingbysvyter.dto.CustomersRegDTO;
import com.svyter.spring.swimingbysvyter.entity.Customers;

import java.util.List;

public interface CustomersService {
    Customers createCustomer(CustomersRegDTO customersRegDTO);
    void delCustomers(Long idForDel);
    void editLogin(String token,String login);
    void editPass(String token,String pass);
    List<CustomersGetDTO> getCustomers();
    CustomersGetDTO getCustomer(String token);
}
