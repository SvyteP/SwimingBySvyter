package com.svyter.spring.swimingbysvyter.serviceImpl;

import com.svyter.spring.swimingbysvyter.dto.base.ResponseDTO;
import com.svyter.spring.swimingbysvyter.exception.NotFoundDataException;
import com.svyter.spring.swimingbysvyter.repo.CustomersRepo;
import com.svyter.spring.swimingbysvyter.repo.TrainingsRepo;
import com.svyter.spring.swimingbysvyter.repo.UserTrainingsRepo;
import com.svyter.spring.swimingbysvyter.entity.*;
import com.svyter.spring.swimingbysvyter.dto.TrainingsDTO;
import com.svyter.spring.swimingbysvyter.dto.UserListTrainingsGetDTO;
import com.svyter.spring.swimingbysvyter.security.JwtUtils;
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
    private final JwtUtils jwtUtils;

    @Autowired
    public UserTrainingServiceImpl(UserTrainingsRepo userListTrainingsRepo, CustomersRepo customersRepo, TrainingsRepo trainingsRepo, MessageSource messageSource, JwtUtils jwtUtils) {
        this.userListTrainingsRepo = userListTrainingsRepo;
        this.customersRepo = customersRepo;
        this.trainingsRepo = trainingsRepo;
        this.messageSource = messageSource;
        this.jwtUtils = jwtUtils;

    }

    @Override
    public ResponseDTO<List<UserListTrainingsGetDTO>> createUserTraining(String token) {
        Long id = jwtUtils.extractUserId(token);
        Customers customers = customersRepo.findById(id).orElseThrow(
                () -> new NotFoundDataException(String.format(messageSource.getMessage("error.customer.notfound", null, Locale.getDefault()), "id " + id))
        );
        Questioner questioner = customers.getQuestioner();
        List<Long> inventoriesId = customers.getInventories().stream().map(Inventory::getId).collect(Collectors.toCollection(ArrayList::new));
        List<Trainings> trainings;
        if (!inventoriesId.isEmpty()) {
            trainings = trainingsRepo.findByInventoriesIdAndComplexityIdAndCountInventoriesId(inventoriesId, questioner.getComplexity().getId(), inventoriesId.size());
        } else {
            trainings = trainingsRepo.findByComplexityIdAndCountInventoriesId(questioner.getComplexity().getId());
        }
        int allCountTrain = questioner.getCountWeek() * questioner.getCountTrainOneWeek();
        if (trainings.isEmpty()) {
            throw new NotFoundDataException(messageSource.getMessage("error.training.notfound.for.user", null, Locale.getDefault()));
        }
        List<UserTrainings> userTrainings = new ArrayList<>();
        for (Trainings train : trainings) {
            UserTrainings userTraining = new UserTrainings(train, customers, false, false);
            userTrainings.add(userTraining);
            train.getUserTrainings().add(userTraining);
            customers.getUserTrainings().add(userTraining);

            trainingsRepo.save(train);
            customersRepo.save(customers);
            userListTrainingsRepo.save(userTraining);
        }
        return new ResponseDTO<>(userTrainings.stream().map(UserListTrainingsGetDTO::convertToModel)
                .collect(Collectors.toCollection(ArrayList::new)));
    }

    @Override
    public ResponseDTO<UserListTrainingsGetDTO> getOneUserTraining(Long userTrainingId) {
        return new ResponseDTO<>(UserListTrainingsGetDTO.convertToModel(userListTrainingsRepo.findById(userTrainingId).orElseThrow(
                () -> new NotFoundDataException(String.format(messageSource.getMessage("error.user.training.notfound ", null, Locale.getDefault()), "id " + userTrainingId))
        )));
    }

    @Override
    public ResponseDTO<List<UserListTrainingsGetDTO>> getUserTrainings(String token) {
        Long id = jwtUtils.extractUserId(token);
        Customers customers = customersRepo.findById(id).orElseThrow(
                () -> new NotFoundDataException(String.format(messageSource.getMessage("error.customer.notfound", null, Locale.getDefault()), "id " + id))
        );
        return new ResponseDTO<>(userListTrainingsRepo.findAllByCustomers(customers).stream()
                .map(UserListTrainingsGetDTO::convertToModel)
                .collect(Collectors.toCollection(ArrayList::new)));
    }

    @Override
    public ResponseDTO<List<UserListTrainingsGetDTO>> getAllUserTrainings() {
        Iterable<UserTrainings> iterable = userListTrainingsRepo.findAll();
        return new ResponseDTO<>(StreamSupport.stream(iterable.spliterator(), false)
                .map(UserListTrainingsGetDTO::convertToModel)
                .collect(Collectors.toCollection(ArrayList::new)));
    }

    @Override
    public void isLikeTraining(Long userTrainingId, boolean isLike) {
        UserTrainings userListTrainings = userListTrainingsRepo.findById(userTrainingId).orElseThrow(
                () -> new NotFoundDataException(String.format(messageSource.getMessage("error.user.training.notfound ", null, Locale.getDefault()), "userTrainingId " + userTrainingId))
        );
        userListTrainings.setLikeTrain(isLike);
        userListTrainingsRepo.save(userListTrainings);
    }

    @Override
    public void isCompliteTraining(Long userTrainingId, boolean isCompl) {
        UserTrainings userListTrainings = userListTrainingsRepo.findById(userTrainingId).orElseThrow(
                () -> new NotFoundDataException(String.format(messageSource.getMessage("error.user.training.notfound ", null, Locale.getDefault()), "userTrainingId " + userTrainingId))
        );
        userListTrainings.setCompleted(isCompl);
        userListTrainingsRepo.save(userListTrainings);
    }

    @Override
    public void deleteUserTraining(Long userTrainingId) {
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
    }

    /*Подумать над типизацией метода и слиянием isLike и isComplete*/
    @Override
    public ResponseDTO<List<TrainingsDTO>> isLikeUserTraining(String token) {
        Long id = jwtUtils.extractUserId(token);
        return new ResponseDTO<>(userListTrainingsRepo.findAllByCustomers(customersRepo.findById(id).orElseThrow(
                        () -> new NotFoundDataException(String.format(messageSource.getMessage("error.customer.notfound", null, Locale.getDefault()), "id " + id))
                )).
                stream().filter(UserTrainings::isLikeTrain).map(UserTrainings::getTrainings).map(TrainingsDTO::convertToModel).toList());
    }

    @Override
    public ResponseDTO<List<TrainingsDTO>> isComplitedUserTraining(String token) {
        Long id = jwtUtils.extractUserId(token);
        return new ResponseDTO<>(userListTrainingsRepo.findAllByCustomers(customersRepo.findById(id).orElseThrow(
                        () -> new NotFoundDataException(String.format(messageSource.getMessage("error.customer.notfound", null, Locale.getDefault()), "id " + id))
                ))
                .stream().filter(UserTrainings::isCompleted).map(UserTrainings::getTrainings).map(TrainingsDTO::convertToModel).toList());
    }
}
