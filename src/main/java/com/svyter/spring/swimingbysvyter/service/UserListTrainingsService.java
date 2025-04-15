package com.svyter.spring.swimingbysvyter.service;

import com.svyter.spring.swimingbysvyter.model.TrainingsModel;
import com.svyter.spring.swimingbysvyter.model.UserListTrainingsGetModel;


import java.util.List;

public interface UserListTrainingsService {
    public void createUserListTrainings(Long idCustomers);
    public UserListTrainingsGetModel readOneUserListTrainings(Long userTrainingId);
    public List<UserListTrainingsGetModel> readUserListTrainings(Long idCustomers);
    public List<UserListTrainingsGetModel> readAllUserListTrainings();
    public void isLikeTraining(Long userTrainingId,  boolean isLike);
    public void isCompliteTraining(Long userTrainingId,  boolean isCompl);
    public void deleteOneUserListTrainings(Long userTrainingId);
    public List<TrainingsModel> isLikeTrainingsList(Long customersId);
    public List<TrainingsModel> isComplitedTrainingsList(Long customersId);


}
