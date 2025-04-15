package com.svyter.spring.swimingbysvyter.dto;

import com.svyter.spring.swimingbysvyter.entity.Customers;
import com.svyter.spring.swimingbysvyter.entity.Trainings;
import com.svyter.spring.swimingbysvyter.entity.UserTrainings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTrainingsRepo extends CrudRepository<UserTrainings,Long> {
    List<UserTrainings> findAllByCustomers(Customers customers);

    Optional<UserTrainings> findByCustomersIdAndTrainingsId(Long customersId, Long trainingsId);
}
