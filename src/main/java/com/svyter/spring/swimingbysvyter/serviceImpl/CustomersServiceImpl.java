package com.svyter.spring.swimingbysvyter.serviceImpl;

import com.svyter.spring.swimingbysvyter.dto.CustomersRepo;
import com.svyter.spring.swimingbysvyter.entity.Customers;
import com.svyter.spring.swimingbysvyter.model.CustomersRegModel;
import com.svyter.spring.swimingbysvyter.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomersServiceImpl implements CustomersService {
    private CustomersRepo customersDto;
    @Autowired
    public CustomersServiceImpl(CustomersRepo customersDto) {
        this.customersDto = customersDto;
    }

    @Override
    public void regCustomers(CustomersRegModel customersRegModel) {
        try {
            Customers customers = new Customers(customersRegModel.getLogin(),customersRegModel.getPass(),
                    customersRegModel.getEmail(), customersRegModel.getIsAdmin());
            customersDto.save(customers);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delCustomers(Long idForDel, Long idUser) {
        try {
          Customers admin = customersDto.findById(idUser).orElseThrow();
          customersDto.deleteById(idForDel);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void editLogin(Long id, String login) {
        try {
            Customers customers = customersDto.findById(id).orElseThrow();
            customers.setName(login);
            customersDto.save(customers);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void editPass(String email, String pass) {
        try {
            Customers customers = customersDto.findByEmail(email);
            customers.setPass(pass);
            customersDto.save(customers);
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
