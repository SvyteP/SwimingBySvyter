package com.svyter.spring.swimingbysvyter.serviceImpl;

import com.svyter.spring.swimingbysvyter.dto.CustomersRegDTO;
import com.svyter.spring.swimingbysvyter.dto.QuestionerDTO;
import com.svyter.spring.swimingbysvyter.entity.Categories;
import com.svyter.spring.swimingbysvyter.entity.Questioner;
import com.svyter.spring.swimingbysvyter.exception.NotFoundDataException;
import com.svyter.spring.swimingbysvyter.repo.CategoriesRepo;
import com.svyter.spring.swimingbysvyter.repo.CustomersRepo;
import com.svyter.spring.swimingbysvyter.entity.Customers;
import com.svyter.spring.swimingbysvyter.dto.CustomersGetDTO;
import com.svyter.spring.swimingbysvyter.repo.QuestionerRepo;
import com.svyter.spring.swimingbysvyter.security.JwtUtils;
import com.svyter.spring.swimingbysvyter.security.JwtUtilsImpl;
import com.svyter.spring.swimingbysvyter.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Locale;

@Service
public class CustomersServiceImpl implements CustomersService {
    private final PasswordEncoder passwordEncoder;
    private final CustomersRepo customersRepo;
    private final CategoriesRepo categoriesRepo;
    private final MessageSource messageSource;
    private final QuestionerRepo questionerRepo;
    private final JwtUtils jwtUtils;

    @Autowired
    public CustomersServiceImpl(PasswordEncoder passwordEncoder, CustomersRepo customersRepo, CategoriesRepo categoriesRepo, MessageSource messageSource, QuestionerRepo questionerRepo, JwtUtils jwtUtils) {
        this.passwordEncoder = passwordEncoder;
        this.customersRepo = customersRepo;
        this.categoriesRepo = categoriesRepo;
        this.messageSource = messageSource;
        this.questionerRepo = questionerRepo;
        this.jwtUtils = jwtUtils;
    }

    public Customers createCustomer(CustomersRegDTO customersRegDTO) {
        Base64.Decoder decoder = Base64.getDecoder();
        String login = new String(decoder.decode(customersRegDTO.getLogin()), StandardCharsets.UTF_8);
        String password = new String(decoder.decode(customersRegDTO.getPass()), StandardCharsets.UTF_8);
        String email = new String(decoder.decode(customersRegDTO.getEmail()), StandardCharsets.UTF_8);

        if (!customersRepo.existsByEmail(email)) {


            Categories category = categoriesRepo.findById(1).orElseThrow(
                    () -> new NotFoundDataException(messageSource.getMessage(
                            "error.category.notfound", new Object[]{"id " + 1}, Locale.getDefault())));
            Questioner questioner = new Questioner();

            Customers customer = new Customers(login,
                    passwordEncoder.encode(password),
                    email);

            customer.setCategory(category);
            customer.setQuestioner(questioner);
            questioner.setCustomers(customer);

            questionerRepo.save(questioner);
            customersRepo.save(customer);
            return customer;
        } else {
            throw new RuntimeException("User with this email was registered earlier");
        }
    }

    @Override
    public void delCustomers(Long idForDel) {
        if (customersRepo.existsById(idForDel)) {
            customersRepo.deleteById(idForDel);
        } else {
            throw new NotFoundDataException(String.format(messageSource.getMessage("error.customer.notfound", new Object[]{"id " + idForDel}, Locale.getDefault())));
        }
    }

    @Override
    public void editLogin(String token, String login) {
        long id = jwtUtils.extractUserId(token);
        Customers customers = customersRepo.findById(id).orElseThrow(() -> new NotFoundDataException(
                String.format(messageSource.getMessage("error.customer.notfound", new Object[]{"id " + id}, Locale.getDefault()))
        ));
        customers.setName(login);
        customersRepo.save(customers);
    }

    @Override
    public void editPass(String token, String pass) {
        long id = jwtUtils.extractUserId(token);
        Customers customers = customersRepo.findById(id)
                .orElseThrow(() -> new NotFoundDataException(
                        String.format(messageSource.getMessage("error.customer.notfound", new Object[]{"id " + id}, Locale.getDefault()))
                ));
        customers.setPass(pass);
        customersRepo.save(customers);
    }

    @Override
    public List<CustomersGetDTO> getCustomers() {
        return customersRepo.findAll().stream().map(CustomersGetDTO::convertCustomersToModel).toList();
    }

    @Override
    public CustomersGetDTO getCustomer(String token) {
        long id = jwtUtils.extractUserId(token);
        return CustomersGetDTO.convertCustomersToModel(customersRepo.findById(id).orElseThrow(() -> new NotFoundDataException(
               messageSource.getMessage("error.customer.notfound", new Object[]{"id " + id}, Locale.getDefault())
        )));
    }
}
