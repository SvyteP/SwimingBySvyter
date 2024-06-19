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
        Customers customers = customersRepo.findById(idCustomers).orElseThrow();
        Questioner questioner = customers.getQuestioner();
        List<Trainings> trainings = trainingsRepo.findAll();
        int allCountTrain = questioner.getCountWeek()*questioner.getCountTrainOneWeek();

        List<Trainings> customerTrain = trainings.stream()
                .filter(train ->new HashSet<>(train.getInventoryList()).containsAll(customers.getInventories())
                                                && train.getComplexity().getName().equals(questioner.getLevelTrain()))
                .toList();
        Iterator<Trainings> iterator = customerTrain.iterator();


        for (int i=0;i<allCountTrain;i++){
            if (iterator.hasNext())
            {
                Trainings train = iterator.next();
                CustomersTrainingsId customersTrainingsId = new CustomersTrainingsId(customers.getId(),train.getId());
                UserListTrainings userListTrainings = new UserListTrainings(customersTrainingsId,train,customers,false,false);
            }
            else {
                iterator = customerTrain.iterator();
            }

        }

    }

    @Override
    public UserListTrainingsGetModel readOneUserListTrainings(CustomersTrainingsId customersTrainingsId) {
        return null;
    }

    @Override
    public List<UserListTrainingsGetModel> readUserListTrainings(Long idCustomers) {
        return null;
    }

    @Override
    public List<UserListTrainingsGetModel> readAllUserListTrainings() {
        return null;
    }

    @Override
    public void editUserListTrainings(CustomersTrainingsId customersTrainingsId, UserListTrainingsPostModel userListTrainingsPostModel) {

    }

    @Override
    public void deleteOneUserListTrainings(CustomersTrainingsId customersTrainingsId) {

    }
}
