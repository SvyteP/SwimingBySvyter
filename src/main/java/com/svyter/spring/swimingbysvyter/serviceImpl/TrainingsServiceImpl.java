package com.svyter.spring.swimingbysvyter.serviceImpl;

import com.svyter.spring.swimingbysvyter.dto.TrainingsRepo;
import com.svyter.spring.swimingbysvyter.entity.Trainings;
import com.svyter.spring.swimingbysvyter.model.TrainingsModel;
import com.svyter.spring.swimingbysvyter.service.TrainingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingsServiceImpl implements TrainingsService {
    private final TrainingsRepo trainingsRepo;
    @Autowired
    public TrainingsServiceImpl(TrainingsRepo trainingsRepo) {
        this.trainingsRepo = trainingsRepo;
    }

    @Override
    public void createTrain(TrainingsModel trainingsModel) {
        try {
            Trainings trainings = new Trainings(trainingsModel.getName(), trainingsModel.getWarmUp(),
                                                trainingsModel.getMainTraining(),trainingsModel.getHitch());
            trainingsRepo.save(trainings);
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<TrainingsModel> readTrainings() {
        try {
            return trainingsRepo.findAll().stream().map(TrainingsModel::convertToModel).toList();
        }
        catch (Exception e)
        {
            throw  new RuntimeException(e.getMessage());
        }

    }

    @Override
    public TrainingsModel readTrain(Long id) {
        try {
            return TrainingsModel.convertToModel(trainingsRepo.findById(id).orElseThrow());
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void editTrainings(TrainingsModel trainingsModel, Long id) {
        try {
            Trainings trainings = trainingsRepo.findById(id).orElseThrow();
            trainings.setName(trainingsModel.getName());
            trainings.setWarmUp(trainingsModel.getWarmUp());
            trainings.setMainTraining(trainingsModel.getMainTraining());
            trainings.setHitch(trainingsModel.getHitch());
            trainingsRepo.save(trainings);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delTrainings(Long id) {
        try {
            trainingsRepo.deleteById(id);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }

    }
}
