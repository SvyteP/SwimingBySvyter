package com.svyter.spring.swimingbysvyter.service;

import com.svyter.spring.swimingbysvyter.dto.TrainingsDTO;
import com.svyter.spring.swimingbysvyter.dto.UserListTrainingsGetDTO;


import java.util.List;

public interface UserListTrainingsService {
    public void createUserTraining(Long idCustomers);
    public UserListTrainingsGetDTO readOneUserTraining(Long userTrainingId);
    public List<UserListTrainingsGetDTO> readUserTrainings(Long idCustomers);
    public List<UserListTrainingsGetDTO> readAllUserTrainings();
    public void isLikeTraining(Long userTrainingId,  boolean isLike);
    public void isCompliteTraining(Long userTrainingId,  boolean isCompl);
    public void deleteUserTraining(Long userTrainingId);
    public List<TrainingsDTO> isLikeUserTraining(Long customersId);
    public List<TrainingsDTO> isComplitedUserTraining(Long customersId);


}
