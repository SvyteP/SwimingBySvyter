package com.svyter.spring.swimingbysvyter.service;

import com.svyter.spring.swimingbysvyter.dto.TrainingsDTO;

import java.util.List;

public interface TrainingsService {
    void createTrain(TrainingsDTO trainingsDTO);
    List<TrainingsDTO> readTrainings();
    TrainingsDTO readTrain(Long id);
    void editTrainings(TrainingsDTO trainingsDTO, Long id);
    void delTrainings(Long id);

}
