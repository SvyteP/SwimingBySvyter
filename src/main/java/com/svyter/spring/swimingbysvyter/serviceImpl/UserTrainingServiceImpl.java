package com.svyter.spring.swimingbysvyter.serviceImpl;

import com.svyter.spring.swimingbysvyter.exception.NotFoundDataException;
import com.svyter.spring.swimingbysvyter.repo.CustomersRepo;
import com.svyter.spring.swimingbysvyter.repo.TrainingsRepo;
import com.svyter.spring.swimingbysvyter.repo.UserTrainingsRepo;
import com.svyter.spring.swimingbysvyter.entity.*;
import com.svyter.spring.swimingbysvyter.dto.TrainingsDTO;
import com.svyter.spring.swimingbysvyter.dto.UserListTrainingsGetDTO;
import com.svyter.spring.swimingbysvyter.service.UserListTrainingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserTrainingServiceImpl implements UserListTrainingsService {
    private final UserTrainingsRepo userListTrainingsRepo;
    private final CustomersRepo customersRepo;
    private final TrainingsRepo trainingsRepo;
    private final MessageSource messageSource;

    @Autowired
    public UserTrainingServiceImpl(UserTrainingsRepo userListTrainingsRepo, CustomersRepo customersRepo, TrainingsRepo trainingsRepo, MessageSource messageSource) {
        this.userListTrainingsRepo = userListTrainingsRepo;
        this.customersRepo = customersRepo;
        this.trainingsRepo = trainingsRepo;
        this.messageSource = messageSource;
    }

    @Override
    public void createUserTraining(Long idCustomers) {
        try {
            Customers customers = customersRepo.findById(idCustomers).orElseThrow(
                    () -> new NotFoundDataException(String.format(messageSource.getMessage("error.customer.notfound", null, Locale.getDefault()), "idCustomers " + idCustomers))
            );
            Questioner questioner = customers.getQuestioner();
            List<Long> inventoriesId = customers.getInventories().stream().map(Inventory::getId).collect(Collectors.toCollection(ArrayList::new));
            List<Trainings> trainings = trainingsRepo.findByInventoriesIdAndComplexityIdAndCountInventoriesId(inventoriesId, questioner.getComplexity().getId(), inventoriesId.size());
            int allCountTrain = questioner.getCountWeek() * questioner.getCountTrainOneWeek();
            if (trainings.isEmpty()) {
                throw new NotFoundDataException(messageSource.getMessage("error.training.notfound.for.user", null, Locale.getDefault()));
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
    public UserListTrainingsGetDTO readOneUserTraining(Long userTrainingId) {
        try {
            return UserListTrainingsGetDTO.convertToModel(userListTrainingsRepo.findById(userTrainingId).orElseThrow(
                    () -> new NotFoundDataException(String.format(messageSource.getMessage("error.user.training.notfound ", null, Locale.getDefault()), "userTrainingId " + userTrainingId))
            ));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public List<UserListTrainingsGetDTO> readUserTrainings(Long idCustomers) {
        try {
            Customers customers = customersRepo.findById(idCustomers).orElseThrow(
                    () -> new NotFoundDataException(String.format(messageSource.getMessage("error.customer.notfound", null, Locale.getDefault()), "idCustomers " + idCustomers))
            );
            return userListTrainingsRepo.findAllByCustomers(customers).stream()
                    .map(UserListTrainingsGetDTO::convertToModel)
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<UserListTrainingsGetDTO> readAllUserTrainings() {
        try {
            Iterable<UserTrainings> iterable = userListTrainingsRepo.findAll();
            return StreamSupport.stream(iterable.spliterator(), false)
                    .map(UserListTrainingsGetDTO::convertToModel)
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void isLikeTraining(Long userTrainingId, boolean isLike) {
        try {
            UserTrainings userListTrainings = userListTrainingsRepo.findById(userTrainingId).orElseThrow(
                    () -> new NotFoundDataException(String.format(messageSource.getMessage("error.user.training.notfound ", null, Locale.getDefault()), "userTrainingId " + userTrainingId))
            );
            userListTrainings.setLikeTrain(isLike);
            userListTrainingsRepo.save(userListTrainings);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void isCompliteTraining(Long userTrainingId, boolean isCompl) {
        try {
            UserTrainings userListTrainings = userListTrainingsRepo.findById(userTrainingId).orElseThrow(
                    () -> new NotFoundDataException(String.format(messageSource.getMessage("error.user.training.notfound ", null, Locale.getDefault()), "userTrainingId " + userTrainingId))
            );
            userListTrainings.setCompleted(isCompl);
            userListTrainingsRepo.save(userListTrainings);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteUserTraining(Long userTrainingId) {
        try {
            UserTrainings userTrainings = userListTrainingsRepo.findById(userTrainingId).orElseThrow();
            Customers customers = customersRepo.findById(userTrainings.getCustomers().getId()).orElseThrow(
                    () -> new NotFoundDataException(String.format(messageSource.getMessage("error.customer.notfound", null, Locale.getDefault()), "id " + userTrainings.getCustomers().getId()))
            );
            Trainings trainings = trainingsRepo.findById(userTrainings.getTrainings().getId()).orElseThrow(
                    () -> new NotFoundDataException(String.format(messageSource.getMessage("error.training.notfound ", null, Locale.getDefault()), "id " + userTrainings.getTrainings().getId()))

            );

            customers.getUserTrainings().remove(userTrainings);
            trainings.getUserTrainings().remove(userTrainings);

            customersRepo.save(customers);
            trainingsRepo.save(trainings);
            userListTrainingsRepo.deleteById(userTrainingId);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    /*Подумать над типизацией метода и слиянием isLike и isComplete*/
    @Override
    public List<TrainingsDTO> isLikeUserTraining(Long idCustomer) {
        try {
            return userListTrainingsRepo.findAllByCustomers(customersRepo.findById(idCustomer).orElseThrow(
                            () -> new NotFoundDataException(String.format(messageSource.getMessage("error.customer.notfound", null, Locale.getDefault()), "idCustomer " + idCustomer))
                    )).
                    stream().filter(UserTrainings::isLikeTrain).map(UserTrainings::getTrainings).map(TrainingsDTO::convertToModel).toList();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<TrainingsDTO> isComplitedUserTraining(Long idCustomer) {
        try {
            return userListTrainingsRepo.findAllByCustomers(customersRepo.findById(idCustomer).orElseThrow(
                            () -> new NotFoundDataException(String.format(messageSource.getMessage("error.customer.notfound", null, Locale.getDefault()), "idCustomer " + idCustomer))
                    ))
                    .stream().filter(UserTrainings::isCompleted).map(UserTrainings::getTrainings).map(TrainingsDTO::convertToModel).toList();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
