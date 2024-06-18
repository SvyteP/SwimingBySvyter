package com.svyter.spring.swimingbysvyter.model;

import com.svyter.spring.swimingbysvyter.entity.UserListTrainings;

import javax.persistence.Column;

public class UserListTrainingsGetModel {
    private CustomersGetModel customersGetModel;
    private TrainingsModel trainingsModel;
    private boolean likeTrain;
    private boolean complited;

    public UserListTrainingsGetModel() {
    }

    public UserListTrainingsGetModel(CustomersGetModel customersGetModel, TrainingsModel trainingsModel, boolean likeTrain, boolean complited) {
        this.customersGetModel = customersGetModel;
        this.trainingsModel = trainingsModel;
        this.likeTrain = likeTrain;
        this.complited = complited;
    }
    public UserListTrainingsGetModel convertToModel (UserListTrainings userListTrainings)
    {
        UserListTrainingsGetModel userListTrainingsGetModel = new UserListTrainingsGetModel(CustomersGetModel.convertCustomersToModel(userListTrainings.getCustomers()),
                                                                                            TrainingsModel.convertToModel(userListTrainings.getTrainings()),
                                                                                            userListTrainings.isLikeTrain(),
                                                                                            userListTrainings.isComplited());
        return userListTrainingsGetModel;
    }
}
