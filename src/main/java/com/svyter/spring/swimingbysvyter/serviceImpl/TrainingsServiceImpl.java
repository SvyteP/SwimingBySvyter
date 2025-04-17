package com.svyter.spring.swimingbysvyter.serviceImpl;

import com.svyter.spring.swimingbysvyter.exception.DataAlreadyExistException;
import com.svyter.spring.swimingbysvyter.exception.NotFoundDataException;
import com.svyter.spring.swimingbysvyter.repo.ComplexityRepo;
import com.svyter.spring.swimingbysvyter.repo.InventoryRepo;
import com.svyter.spring.swimingbysvyter.repo.TrainingsRepo;
import com.svyter.spring.swimingbysvyter.entity.Inventory;
import com.svyter.spring.swimingbysvyter.entity.Trainings;
import com.svyter.spring.swimingbysvyter.dto.TrainingsDTO;
import com.svyter.spring.swimingbysvyter.service.TrainingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TrainingsServiceImpl implements TrainingsService {
    private final TrainingsRepo trainingsRepo;
    private final InventoryRepo inventoryRepo;
    private final ComplexityRepo complexityRepo;
    private final MessageSource messageSource;

    @Autowired
    public TrainingsServiceImpl(TrainingsRepo trainingsRepo, InventoryRepo inventoryRepo, ComplexityRepo complexityRepo, MessageSource messageSource) {
        this.trainingsRepo = trainingsRepo;
        this.inventoryRepo = inventoryRepo;
        this.complexityRepo = complexityRepo;
        this.messageSource = messageSource;
    }

    @Override
    public void createTrain(TrainingsDTO trainingsDTO) {
        try {
            if (trainingsRepo.existsByName(trainingsDTO.getName())) {
                Trainings trainings = new Trainings(trainingsDTO.getName(), trainingsDTO.getWarmUp(),
                        trainingsDTO.getMainTraining(), trainingsDTO.getHitch(),
                        trainingsDTO.getInventories().stream().map(
                                idInv -> {
                                    try {
                                        return inventoryRepo.findById(idInv).orElseThrow();
                                    } catch (NoSuchElementException e) {
                                        throw new RuntimeException(e.getMessage());
                                    }
                                }).toList(),
                        complexityRepo.findByName(trainingsDTO.getComplexity()));
                trainingsRepo.save(trainings);
            } else {
                throw new DataAlreadyExistException(
                        String.format(messageSource.getMessage("error.training.already.exist",null, Locale.getDefault()), "name " + trainingsDTO.getName()));
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<TrainingsDTO> readTrainings() {
        try {
            return trainingsRepo.findAll().stream().map(TrainingsDTO::convertToModel).toList();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public TrainingsDTO readTrain(Long id) {
        try {
            return TrainingsDTO.convertToModel(trainingsRepo.findById(id).orElseThrow(
                    ()-> new NotFoundDataException(String.format(messageSource.getMessage("error.training.notfound",null,Locale.getDefault()),"id " + id))
            ));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void editTrainings(TrainingsDTO trainingsDTO, Long id) {
        try {
            List<Inventory> inventories = trainingsDTO.getInventories().stream()
                    .map(inventoryRepo::findById)
                    .map(Optional::orElseThrow)
                    .collect(Collectors.toCollection(ArrayList::new));

            Trainings trainings = trainingsRepo.findById(id).orElseThrow(
                    ()-> new NotFoundDataException(String.format(messageSource.getMessage("error.training.notfound",null,Locale.getDefault()),"id " + id))
            );
            trainings.setName(trainingsDTO.getName());
            trainings.setWarmUp(trainingsDTO.getWarmUp());
            trainings.setMainTraining(trainingsDTO.getMainTraining());
            trainings.setHitch(trainingsDTO.getHitch());
            trainings.setInventories(inventories);
            trainingsRepo.save(trainings);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delTrainings(Long id) {
        try {
            Trainings trainings = trainingsRepo.findById(id).orElseThrow(
                    ()-> new NotFoundDataException(String.format(messageSource.getMessage("error.training.notfound",null,Locale.getDefault()),"id " + id))
            );
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
