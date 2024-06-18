package com.svyter.spring.swimingbysvyter.model;

import com.svyter.spring.swimingbysvyter.entity.UserListTrainings;
import lombok.Data;

@Data
public class UserListTrainingsPostModel {
    private boolean likeTrain;
    private boolean complited;

    public UserListTrainingsPostModel() {
    }

    public UserListTrainingsPostModel(boolean likeTrain, boolean complited) {
        this.likeTrain = likeTrain;
        this.complited = complited;
    }

}
