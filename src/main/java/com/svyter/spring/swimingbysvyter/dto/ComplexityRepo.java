package com.svyter.spring.swimingbysvyter.dto;

import com.svyter.spring.swimingbysvyter.entity.Complexity;
import com.svyter.spring.swimingbysvyter.entity.Customers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplexityRepo extends CrudRepository<Complexity,Long> {
    boolean existsByName(String name);
    List<Complexity> findAll(); // нужно это убрать переопределяет метод родителя не хорошо так делать
    Complexity findByName(String name);
}
