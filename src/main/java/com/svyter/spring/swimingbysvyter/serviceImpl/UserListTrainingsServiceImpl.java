package com.svyter.spring.swimingbysvyter.serviceImpl;

import com.svyter.spring.swimingbysvyter.dto.CustomersRepo;
import com.svyter.spring.swimingbysvyter.dto.TrainingsRepo;
import com.svyter.spring.swimingbysvyter.dto.UserListTrainingsRepo;
import com.svyter.spring.swimingbysvyter.entity.Customers;
import com.svyter.spring.swimingbysvyter.entity.Questioner;
import com.svyter.spring.swimingbysvyter.entity.Trainings;
import com.svyter.spring.swimingbysvyter.entity.UserListTrainings;
import com.svyter.spring.swimingbysvyter.joinClass.CustomersTrainingsId;
import com.svyter.spring.swimingbysvyter.model.UserListTrainingsGetModel;
import com.svyter.spring.swimingbysvyter.model.UserListTrainingsPostModel;
import com.svyter.spring.swimingbysvyter.service.UserListTrainingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserListTrainingsServiceImpl implements UserListTrainingsService {
     private final UserListTrainingsRepo userListTrainingsRepo;
     private final CustomersRepo customersRepo;
     private  final TrainingsRepo trainingsRepo;

    @Autowired
    public UserListTrainingsServiceImpl(UserListTrainingsRepo userListTrainingsRepo, CustomersRepo customersRepo, TrainingsRepo trainingsRepo) {
        this.userListTrainingsRepo = userListTrainingsRepo;
        this.customersRepo = customersRepo;
        this.trainingsRepo = trainingsRepo;
    }

    @Override
    public void createUserListTrainings(Long idCustomers) {
      try {
          Customers customers = customersRepo.findById(idCustomers).orElseThrow();
          Questioner questioner = customers.getQuestioner();
          List<Trainings> trainings = trainingsRepo.findAll();
          int allCountTrain = questioner.getCountWeek()*questioner.getCountTrainOneWeek();

          List<Trainings> customerTrain = trainings.stream()
                  .filter(train ->new HashSet<>(customers.getInventories()).containsAll(train.getInventoryList())
                          && train.getComplexity().getName().equals(questioner.getLevelTrain()))
                  .toList();
        if(customerTrain.isEmpty()){
            throw new RuntimeException("Тренировки по указанным критериям не найдены!");
        }
          for (Trainings train : customerTrain) {
              CustomersTrainingsId customersTrainingsId = new CustomersTrainingsId(customers.getId(), train.getId());
              UserListTrainings userListTrainings = new UserListTrainings(customersTrainingsId, train, customers, false, false);
              userListTrainingsRepo.save(userListTrainings);
          }
      }
      catch (Exception e){
          throw new RuntimeException(e.getMessage());
      }
    }

    @Override
    public UserListTrainingsGetModel readOneUserListTrainings(CustomersTrainingsId customersTrainingsId) {
        try {
            return UserListTrainingsGetModel.convertToModel(userListTrainingsRepo.findById(customersTrainingsId).orElseThrow());
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public List<UserListTrainingsGetModel> readUserListTrainings(Long idCustomers) {
      try {
          return userListTrainingsRepo.findAllByCustomers(idCustomers).stream()
                                                                        .map(UserListTrainingsGetModel::convertToModel)
                                                                        .toList();
      }
      catch (Exception e){
          throw new RuntimeException(e.getMessage());
      }
    }

    @Override
    public List<UserListTrainingsGetModel> readAllUserListTrainings() {
        try {
            Iterable<UserListTrainings> iterable = userListTrainingsRepo.findAll();
            return StreamSupport.stream(iterable.spliterator(), false)
                    .map(UserListTrainingsGetModel::convertToModel)
                    .toList();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void editUserListTrainings(CustomersTrainingsId customersTrainingsId, UserListTrainingsPostModel userListTrainingsPostModel) {
        try {
            UserListTrainings userListTrainings = userListTrainingsRepo.findById(customersTrainingsId).orElseThrow();
            userListTrainings.setLikeTrain(userListTrainingsPostModel.isLikeTrain());
            userListTrainings.setComplited(userListTrainings.isComplited());
            userListTrainingsRepo.save(userListTrainings);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteOneUserListTrainings(CustomersTrainingsId customersTrainingsId) {
        try {
            userListTrainingsRepo.deleteById(customersTrainingsId);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }
}
