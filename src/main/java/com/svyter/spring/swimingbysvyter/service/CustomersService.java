package com.svyter.spring.swimingbysvyter.service;

import com.svyter.spring.swimingbysvyter.entity.Customers;
import com.svyter.spring.swimingbysvyter.model.CustomersGetModel;
import com.svyter.spring.swimingbysvyter.model.CustomersRegModel;

import java.util.List;

public interface CustomersService {
    void regCustomers(CustomersRegModel customersRegModel);
    void delCustomers(Long idForDel,Long idUser);
    void editLogin(Long id,String login);
    void editPass(String email,String pass);
    List<CustomersGetModel> getCustomers();
    CustomersGetModel getCustomer(Long id);
}
