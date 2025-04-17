package com.svyter.spring.swimingbysvyter.service;

import com.svyter.spring.swimingbysvyter.dto.CustomersGetDTO;
import com.svyter.spring.swimingbysvyter.dto.CustomersRegDTO;

import java.util.List;

public interface CustomersService {
   /* void regCustomers(CustomersRegDTO customersRegDTO);*/
    void delCustomers(Long idForDel,Long idUser);
    void editLogin(Long id,String login);
    void editPass(String email,String pass);
    List<CustomersGetDTO> getCustomers();
    CustomersGetDTO getCustomer(Long id);
}
