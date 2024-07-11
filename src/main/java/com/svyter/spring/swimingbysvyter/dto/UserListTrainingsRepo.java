package com.svyter.spring.swimingbysvyter.dto;

import com.svyter.spring.swimingbysvyter.entity.Customers;
import com.svyter.spring.swimingbysvyter.entity.UserListTrainings;
import com.svyter.spring.swimingbysvyter.joinClass.CustomersTrainingsId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserListTrainingsRepo extends CrudRepository<UserListTrainings,CustomersTrainingsId> {
    public List<UserListTrainings> findAllByCustomers(Customers customers);
}
