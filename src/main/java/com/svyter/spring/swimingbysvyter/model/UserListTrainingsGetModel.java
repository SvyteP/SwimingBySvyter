package com.svyter.spring.swimingbysvyter.model;

import com.svyter.spring.swimingbysvyter.entity.UserTrainings;
import lombok.Data;

@Data
public class UserListTrainingsGetModel {
    private CustomersGetModel customersGetModel;
    private TrainingsModel trainingsModel;
    private boolean likeTrain;
    private boolean completed;

    public UserListTrainingsGetModel() {}

    public UserListTrainingsGetModel(CustomersGetModel customersGetModel, TrainingsModel trainingsModel, boolean likeTrain, boolean complited) {
        this.customersGetModel = customersGetModel;
        this.trainingsModel = trainingsModel;
        this.likeTrain = likeTrain;
        this.completed = complited;
    }
    public static UserListTrainingsGetModel convertToModel (UserTrainings userListTrainings)
    {
        UserListTrainingsGetModel userListTrainingsGetModel = new UserListTrainingsGetModel(CustomersGetModel.convertCustomersToModel(userListTrainings.getCustomers()),
                                                                                            TrainingsModel.convertToModel(userListTrainings.getTrainings()),
                                                                                            userListTrainings.isLikeTrain(),
                                                                                            userListTrainings.isCompleted());
        return userListTrainingsGetModel;
    }
}
