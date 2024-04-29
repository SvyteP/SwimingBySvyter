package com.svyter.spring.swimingbysvyter.model;

import com.svyter.spring.swimingbysvyter.entity.Trainings;
import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class TrainingsModel {
    @NotBlank
    private String name;
    @NotBlank
    private String warmUp;
    @NotBlank
    private String mainTraining;
    @NotBlank
    private String hitch;

    public TrainingsModel() {
    }

    public TrainingsModel(String name, String warmUp, String mainTraining, String hitch) {
        this.name = name;
        this.warmUp = warmUp;
        this.mainTraining = mainTraining;
        this.hitch = hitch;
    }

    public static TrainingsModel convertToModel(Trainings trainings)
    {
        TrainingsModel trainingsModel = new TrainingsModel(trainings.getName(),trainings.getWarmUp(),
                                                            trainings.getMainTraining(), trainings.getHitch());
        return trainingsModel;
    }
}
