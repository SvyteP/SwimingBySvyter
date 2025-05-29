package com.svyter.spring.swimingbysvyter.service;

import com.svyter.spring.swimingbysvyter.dto.TrainingsDTO;
import com.svyter.spring.swimingbysvyter.dto.UserListTrainingsGetDTO;
import com.svyter.spring.swimingbysvyter.dto.base.ResponseDTO;


import java.util.List;

public interface UserListTrainingsService {
    ResponseDTO<List<UserListTrainingsGetDTO>> createUserTraining(String token);

    ResponseDTO<UserListTrainingsGetDTO> getOneUserTraining(Long userTrainingId);

    ResponseDTO<List<UserListTrainingsGetDTO>> getUserTrainings(String token);

    ResponseDTO<List<UserListTrainingsGetDTO>> getAllUserTrainings();

    void isLikeTraining(Long userTrainingId, boolean isLike);

    void isCompliteTraining(Long userTrainingId, boolean isCompl);

    void deleteUserTraining(Long userTrainingId);

    ResponseDTO<List<TrainingsDTO>> isLikeUserTraining(String token);

    ResponseDTO<List<TrainingsDTO>> isCompletedUserTraining(String token);

    ResponseDTO<List<UserListTrainingsGetDTO>> getIsCompletedUserTrainings(String token, Boolean isCompleted);

    ResponseDTO<List<UserListTrainingsGetDTO>> getIsLikeUserTrainings(String token, Boolean isFavorite);

}
