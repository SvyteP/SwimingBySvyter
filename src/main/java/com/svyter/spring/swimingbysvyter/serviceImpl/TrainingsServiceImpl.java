package com.svyter.spring.swimingbysvyter.serviceImpl;

import com.svyter.spring.swimingbysvyter.dto.ComplexityRepo;
import com.svyter.spring.swimingbysvyter.dto.InventoryRepo;
import com.svyter.spring.swimingbysvyter.dto.TrainingsRepo;
import com.svyter.spring.swimingbysvyter.entity.Inventory;
import com.svyter.spring.swimingbysvyter.entity.Trainings;
import com.svyter.spring.swimingbysvyter.model.TrainingsModel;
import com.svyter.spring.swimingbysvyter.service.TrainingsService;
import lombok.extern.log4j.Log4j;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class TrainingsServiceImpl implements TrainingsService {
    private final TrainingsRepo trainingsRepo;
    private final InventoryRepo inventoryRepo;
    private final ComplexityRepo complexityRepo;

    @Autowired
    public TrainingsServiceImpl(TrainingsRepo trainingsRepo, InventoryRepo inventoryRepo, ComplexityRepo complexityRepo) {
        this.trainingsRepo = trainingsRepo;
        this.inventoryRepo = inventoryRepo;
        this.complexityRepo = complexityRepo;
    }

    @Override
    public void createTrain(TrainingsModel trainingsModel) {
        try {
            Trainings trainings = new Trainings(trainingsModel.getName(), trainingsModel.getWarmUp(),
                    trainingsModel.getMainTraining(), trainingsModel.getHitch(),
                    trainingsModel.getInventories().stream().map(
                            idInv -> {
                                try {
                                    return inventoryRepo.findById(idInv).orElseThrow();
                                } catch (NoSuchElementException e) {
                                    throw new RuntimeException(e.getMessage());
                                }
                            }).toList(),
                    complexityRepo.findByName(trainingsModel.getComplexity()));
            trainingsRepo.save(trainings);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<TrainingsModel> readTrainings() {
        try {
            return trainingsRepo.findAll().stream().map(TrainingsModel::convertToModel).toList();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public TrainingsModel readTrain(Long id) {
        try {
            return TrainingsModel.convertToModel(trainingsRepo.findById(id).orElseThrow());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void editTrainings(TrainingsModel trainingsModel, Long id) {
        try {
            List<Inventory> inventories = trainingsModel.getInventories().stream()
                    .map(inventoryRepo::findById)
                    .map(Optional::orElseThrow)
                    .collect(Collectors.toCollection(ArrayList::new));

            Trainings trainings = trainingsRepo.findById(id).orElseThrow();
            trainings.setName(trainingsModel.getName());
            trainings.setWarmUp(trainingsModel.getWarmUp());
            trainings.setMainTraining(trainingsModel.getMainTraining());
            trainings.setHitch(trainingsModel.getHitch());
            trainings.setInventories(inventories);
            trainingsRepo.save(trainings);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delTrainings(Long id) {
        try {
            Trainings trainings = trainingsRepo.findById(id).orElseThrow();
            trainings.getInventories().stream().map(inventory -> {
                inventory.getTrainingsList().remove(trainings);
                inventoryRepo.save(inventory);
                return inventory;
            });
            trainingsRepo.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
