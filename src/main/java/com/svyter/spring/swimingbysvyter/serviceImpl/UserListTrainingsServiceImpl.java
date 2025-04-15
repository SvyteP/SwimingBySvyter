package com.svyter.spring.swimingbysvyter.serviceImpl;

import com.svyter.spring.swimingbysvyter.dto.CustomersRepo;
import com.svyter.spring.swimingbysvyter.dto.TrainingsRepo;
import com.svyter.spring.swimingbysvyter.dto.UserTrainingsRepo;
import com.svyter.spring.swimingbysvyter.entity.*;
import com.svyter.spring.swimingbysvyter.model.TrainingsModel;
import com.svyter.spring.swimingbysvyter.model.UserListTrainingsGetModel;
import com.svyter.spring.swimingbysvyter.service.UserListTrainingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserListTrainingsServiceImpl implements UserListTrainingsService {
    private final UserTrainingsRepo userListTrainingsRepo;
    private final CustomersRepo customersRepo;
    private final TrainingsRepo trainingsRepo;

    @Autowired
    public UserListTrainingsServiceImpl(UserTrainingsRepo userListTrainingsRepo, CustomersRepo customersRepo, TrainingsRepo trainingsRepo) {
        this.userListTrainingsRepo = userListTrainingsRepo;
        this.customersRepo = customersRepo;
        this.trainingsRepo = trainingsRepo;
    }

    @Override
    public void createUserListTrainings(Long idCustomers) {
        try {
            Customers customers = customersRepo.findById(idCustomers).orElseThrow();
            Questioner questioner = customers.getQuestioner();
            List<Long> inventoriesId = customers.getInventories().stream().map(Inventory::getId).collect(Collectors.toCollection(ArrayList::new));
            List<Trainings> trainings = trainingsRepo.findByInventoriesIdAndComplexityIdAndCountInventoriesId(inventoriesId, questioner.getComplexity().getId(), inventoriesId.size());
            int allCountTrain = questioner.getCountWeek() * questioner.getCountTrainOneWeek();
            if (trainings.isEmpty()) {
                throw new RuntimeException("Тренировки по указанным критериям не найдены!");
            }
            for (Trainings train : trainings) {
                UserTrainings userTraining = new UserTrainings(train, customers, false, false);
                train.getUserTrainings().add(userTraining);
                customers.getUserTrainings().add(userTraining);

                trainingsRepo.save(train);
                customersRepo.save(customers);
                userListTrainingsRepo.save(userTraining);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public UserListTrainingsGetModel readOneUserListTrainings(Long userTrainingId) {
        try {
            return UserListTrainingsGetModel.convertToModel(userListTrainingsRepo.findById(userTrainingId).orElseThrow());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public List<UserListTrainingsGetModel> readUserListTrainings(Long idCustomers) {
        try {
            Customers customers = customersRepo.findById(idCustomers).orElseThrow();
            return userListTrainingsRepo.findAllByCustomers(customers).stream()
                    .map(UserListTrainingsGetModel::convertToModel)
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<UserListTrainingsGetModel> readAllUserListTrainings() {
        try {
            Iterable<UserTrainings> iterable = userListTrainingsRepo.findAll();
            return StreamSupport.stream(iterable.spliterator(), false)
                    .map(UserListTrainingsGetModel::convertToModel)
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void isLikeTraining(Long userTrainingId, boolean isLike) {
        try {
            UserTrainings userListTrainings = userListTrainingsRepo.findById(userTrainingId).orElseThrow();
            userListTrainings.setLikeTrain(isLike);
            userListTrainingsRepo.save(userListTrainings);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void isCompliteTraining(Long userTrainingId, boolean isCompl) {
        try {
            UserTrainings userListTrainings = userListTrainingsRepo.findById(userTrainingId).orElseThrow();
            userListTrainings.setCompleted(isCompl);
            userListTrainingsRepo.save(userListTrainings);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteOneUserListTrainings(Long userTrainingId) {
        try {
            UserTrainings userTrainings = userListTrainingsRepo.findById(userTrainingId).orElseThrow();
            Customers customers = customersRepo.findById(userTrainings.getCustomers().getId()).orElseThrow();
            Trainings trainings = trainingsRepo.findById(userTrainings.getTrainings().getId()).orElseThrow();

            customers.getUserTrainings().remove(userTrainings);
            trainings.getUserTrainings().remove(userTrainings);

            customersRepo.save(customers);
            trainingsRepo.save(trainings);
            userListTrainingsRepo.deleteById(userTrainingId);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<TrainingsModel> isLikeTrainingsList(Long customersId) {
        try {

            return userListTrainingsRepo.findAllByCustomers(customersRepo.findById(customersId).orElseThrow()).
                    stream().filter(UserTrainings::isLikeTrain).map(UserTrainings::getTrainings).map(TrainingsModel::convertToModel).toList();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<TrainingsModel> isComplitedTrainingsList(Long customersId) {
        try {
            return userListTrainingsRepo.findAllByCustomers(customersRepo.findById(customersId).orElseThrow())
                    .stream().filter(UserTrainings::isCompleted).map(UserTrainings::getTrainings).map(TrainingsModel::convertToModel).toList();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
