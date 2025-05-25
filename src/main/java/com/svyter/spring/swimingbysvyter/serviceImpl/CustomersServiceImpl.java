package com.svyter.spring.swimingbysvyter.serviceImpl;

import com.svyter.spring.swimingbysvyter.exception.NotFoundDataException;
import com.svyter.spring.swimingbysvyter.repo.CustomersRepo;
import com.svyter.spring.swimingbysvyter.entity.Customers;
import com.svyter.spring.swimingbysvyter.dto.CustomersGetDTO;
import com.svyter.spring.swimingbysvyter.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class CustomersServiceImpl implements CustomersService {
    private final CustomersRepo customersRepo;
    private final MessageSource messageSource;

    @Autowired
    public CustomersServiceImpl(CustomersRepo customersRepo, MessageSource messageSource) {
        this.customersRepo = customersRepo;
        this.messageSource = messageSource;
    }

   /* @Override
    public void regCustomers(CustomersRegDTO customersRegDTO) {
        try {
            if (!customersRepo.existsAllByEmail(customersRegDTO.getEmail())) {
                Customers customers = new Customers(customersRegDTO.getLogin(), customersRegDTO.getPass(),
                        customersRegDTO.getEmail());
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
    }*/

    @Override
    public void delCustomers(Long idForDel, Long idUser) {
        try {
            if (customersRepo.existsById(idForDel)) {
                customersRepo.deleteById(idForDel);
            } else {
                throw new NotFoundDataException(String.format(messageSource.getMessage("error.customer.notfound", null, Locale.getDefault()), "id " + idForDel));
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void editLogin(Long id, String login) {
        try {
            Customers customers = customersRepo.findById(id).orElseThrow(() -> new NotFoundDataException(
                    String.format(messageSource.getMessage("error.customer.notfound", null, Locale.getDefault()), "id " + id)
            ));
            customers.setName(login);
            customersRepo.save(customers);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void editPass(String email, String pass) {
        Customers customers = customersRepo.findByEmail(email)
                .orElseThrow(() -> new NotFoundDataException(
                        String.format(messageSource.getMessage("error.customer.notfound", null, Locale.getDefault()), "email " + email)
                ));
        customers.setPass(pass);
        customersRepo.save(customers);
    }

    @Override
    public List<CustomersGetDTO> getCustomers() {
        try {
            return customersRepo.findAll().stream().map(CustomersGetDTO::convertCustomersToModel).toList();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public CustomersGetDTO getCustomer(Long id) {
        try {
            return CustomersGetDTO.convertCustomersToModel(customersRepo.findById(id).orElseThrow(() -> new NotFoundDataException(
                    String.format(messageSource.getMessage("error.customer.notfound", null, Locale.getDefault()), "id " + id)
            )));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
