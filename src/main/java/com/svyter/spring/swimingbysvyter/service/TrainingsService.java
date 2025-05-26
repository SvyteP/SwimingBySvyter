package com.svyter.spring.swimingbysvyter.service;

import com.svyter.spring.swimingbysvyter.dto.TrainingsDTO;
import com.svyter.spring.swimingbysvyter.dto.base.ResponseDTO;

import java.util.List;

public interface TrainingsService {
    void createTrain(TrainingsDTO trainingsDTO);
    ResponseDTO<List<TrainingsDTO>> readTrainings();
    ResponseDTO<TrainingsDTO> readTrain(Long id);
    void editTrainings(TrainingsDTO trainingsDTO, Long id);
    void delTrainings(Long id);

}
