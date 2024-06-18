package com.svyter.spring.swimingbysvyter.dto;

import com.svyter.spring.swimingbysvyter.entity.UserListTrainings;
import com.svyter.spring.swimingbysvyter.joinClass.CustomersTrainingsId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserListTrainingsRepo extends CrudRepository<UserListTrainings,CustomersTrainingsId> {
}
