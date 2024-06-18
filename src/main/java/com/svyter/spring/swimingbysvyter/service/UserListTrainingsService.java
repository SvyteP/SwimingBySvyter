package com.svyter.spring.swimingbysvyter.service;

import com.svyter.spring.swimingbysvyter.entity.UserListTrainings;
import com.svyter.spring.swimingbysvyter.joinClass.CustomersTrainingsId;
import com.svyter.spring.swimingbysvyter.model.UserListTrainingsGetModel;
import com.svyter.spring.swimingbysvyter.model.UserListTrainingsPostModel;

import java.util.List;

public interface UserListTrainingsService {
    public void createUserListTrainings(Long idCustomers, UserListTrainingsPostModel userListTrainingsPostModel);
    public UserListTrainingsGetModel readOneUserListTrainings(CustomersTrainingsId customersTrainingsId);
    public List<UserListTrainingsGetModel> readUserListTrainings(Long idCustomers);
    public List<UserListTrainingsGetModel> readAllUserListTrainings();
    public void editUserListTrainings(CustomersTrainingsId customersTrainingsId, UserListTrainingsPostModel userListTrainingsPostModel);
    public void deleteOneUserListTrainings(CustomersTrainingsId customersTrainingsId);

}
