package com.svyter.spring.swimingbysvyter.serviceImpl;

import com.svyter.spring.swimingbysvyter.dto.CustomersRepo;
import com.svyter.spring.swimingbysvyter.entity.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyCustomersDetailServiceImpl implements UserDetailsService {
    private CustomersRepo customersRepo;
    @Autowired
    public MyCustomersDetailServiceImpl(CustomersRepo customersRepo) {
        this.customersRepo = customersRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customers customers =  customersRepo.findByEmail(email);
        if (customers == null){
            throw new UsernameNotFoundException("User with this email not found!");
        }
        return User.withUsername(customers.getEmail())
                .password(customers.getPass())
                .roles(customers.getAdmin())
                .build();
    }
}
