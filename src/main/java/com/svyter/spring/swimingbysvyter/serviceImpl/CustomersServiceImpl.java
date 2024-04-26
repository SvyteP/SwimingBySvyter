package com.svyter.spring.swimingbysvyter.serviceImpl;

import com.svyter.spring.swimingbysvyter.dto.CustomersRepo;
import com.svyter.spring.swimingbysvyter.entity.Customers;
import com.svyter.spring.swimingbysvyter.model.CustomersRegModel;
import com.svyter.spring.swimingbysvyter.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomersServiceImpl implements CustomersService {
    private CustomersRepo customersRepo;
    @Autowired
    public CustomersServiceImpl(CustomersRepo customersRepo) {
        this.customersRepo = customersRepo;
    }

    @Override
    public void regCustomers(CustomersRegModel customersRegModel) {
        try {
            if (!customersRepo.existsAllByEmail(customersRegModel.getEmail())) {
                Customers customers = new Customers();
                customers.setEmail(customersRegModel.getEmail());
                customers.setName(customersRegModel.getLogin());
                customers.setPass(customersRegModel.getPass());
                customers.setAdmin(customersRegModel.getAdmin());
                customersRepo.save(customers);
            }
            else{
                throw new RuntimeException("User with this email was registered earlier");
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delCustomers(Long idForDel, Long idUser) {
        try {
          Customers admin = customersRepo.findById(idUser).orElseThrow();
          if (admin.getAdmin().equals("admin") && customersRepo.existsById(idForDel)){
              customersRepo.deleteById(idForDel);
          }
          else {
              throw new RuntimeException("User is not admin or this account isn't found!");
          }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void editLogin(Long id, String login) {
        try {
            Customers customers = customersRepo.findById(id).orElseThrow();
            customers.setName(login);
            customersRepo.save(customers);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void editPass(String email, String pass) {
        try {
            Customers customers = customersRepo.findByEmail(email);
            customers.setPass(pass);
            customersRepo.save(customers);
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
