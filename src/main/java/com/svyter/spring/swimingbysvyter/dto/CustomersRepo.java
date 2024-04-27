package com.svyter.spring.swimingbysvyter.dto;

import com.svyter.spring.swimingbysvyter.entity.Customers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomersRepo extends CrudRepository<Customers,Long> {
    List<Customers> findAll();
    Customers findByEmail(String email);
    Customers findByIdAndAdminIsTrue(Long id);
    Boolean existsAllByEmail(String email);
}
