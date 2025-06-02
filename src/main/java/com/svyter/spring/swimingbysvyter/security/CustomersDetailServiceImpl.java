package com.svyter.spring.swimingbysvyter.security;

import com.svyter.spring.swimingbysvyter.repo.CustomersRepo;
import com.svyter.spring.swimingbysvyter.entity.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class CustomersDetailServiceImpl implements UserDetailsService {
    private CustomersRepo customersRepo;
    private MessageSource messageSource;
    @Autowired
    public CustomersDetailServiceImpl(CustomersRepo customersRepo, MessageSource messageSource) {
        this.customersRepo = customersRepo;
        this.messageSource = messageSource;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customers customers =  customersRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(messageSource.getMessage("error.customer.notfound",
                        new Object[]{"email " + email},
                        Locale.getDefault())));

        return User.withUsername(customers.getEmail())
                .password(customers.getPass())
                .authorities("ROLE_ADMIN")
                .build();
    }
}
