package com.svyter.spring.swimingbysvyter.dto;

import com.svyter.spring.swimingbysvyter.entity.Customers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomersRepo extends CrudRepository<Customers,Long> {
    List<Customers> findAll();
    Optional<Customers> findByEmail(String email);
    Boolean existsAllByEmail(String email);
}
