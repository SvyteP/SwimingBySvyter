package com.svyter.spring.swimingbysvyter.dto;

import com.svyter.spring.swimingbysvyter.entity.UserTrainings;
import lombok.Data;

@Data
public class UserListTrainingsGetDTO {
    private CustomersGetDTO customersGetDTO;
    private TrainingsDTO trainingsDTO;
    private boolean likeTrain;
    private boolean completed;

    public UserListTrainingsGetDTO() {}

    public UserListTrainingsGetDTO(CustomersGetDTO customersGetDTO, TrainingsDTO trainingsDTO, boolean likeTrain, boolean complited) {
        this.customersGetDTO = customersGetDTO;
        this.trainingsDTO = trainingsDTO;
        this.likeTrain = likeTrain;
        this.completed = complited;
    }
    public static UserListTrainingsGetDTO convertToModel (UserTrainings userListTrainings)
    {
        UserListTrainingsGetDTO userListTrainingsGetDTO = new UserListTrainingsGetDTO(CustomersGetDTO.convertCustomersToModel(userListTrainings.getCustomers()),
                                                                                            TrainingsDTO.convertToModel(userListTrainings.getTrainings()),
                                                                                            userListTrainings.isLikeTrain(),
                                                                                            userListTrainings.isCompleted());
        return userListTrainingsGetDTO;
    }
}
