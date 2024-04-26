package com.svyter.spring.swimingbysvyter.dto;

import com.svyter.spring.swimingbysvyter.entity.Customers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepo extends CrudRepository<Customers,Long> {

    Customers findByEmail(String email);
    Customers findByIdAndAdminIsTrue(Long id);
    Boolean existsAllByEmail(String email);
}
