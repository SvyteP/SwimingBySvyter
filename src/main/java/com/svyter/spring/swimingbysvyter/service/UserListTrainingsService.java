package com.svyter.spring.swimingbysvyter.service;

import com.svyter.spring.swimingbysvyter.model.UserListTrainingsGetModel;


import java.util.List;

public interface UserListTrainingsService {
    public void createUserListTrainings(Long idCustomers);
    public UserListTrainingsGetModel readOneUserListTrainings(Long idTraining,Long idCustomer);
    public List<UserListTrainingsGetModel> readUserListTrainings(Long idCustomers);
    public List<UserListTrainingsGetModel> readAllUserListTrainings();
    public void isLikeTraining(Long idTraining,Long idCustomer,  boolean isLike);
    public void isCompliteTraining(Long idTraining, Long idCustomer,  boolean isCompl);
    public void deleteOneUserListTrainings(Long idTraining,Long idCustomer);

}
