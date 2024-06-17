package com.svyter.spring.swimingbysvyter.service;

import com.svyter.spring.swimingbysvyter.model.TrainingsModel;

import java.util.List;

public interface TrainingsService {
    void createTrain(TrainingsModel trainingsModel);
    List<TrainingsModel> readTrainings();
    TrainingsModel readTrain(Long id);
    void editTrainings(TrainingsModel trainingsModel,Long id);
    void delTrainings(Long id);

}
