package com.svyter.spring.swimingbysvyter.model;

import com.svyter.spring.swimingbysvyter.dto.InventoryRepo;
import com.svyter.spring.swimingbysvyter.entity.Inventory;
import com.svyter.spring.swimingbysvyter.entity.Trainings;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Component
public class TrainingsModel {
    @NotBlank
    private String name;
    @NotBlank
    private String warmUp;
    @NotBlank
    private String mainTraining;
    @NotBlank
    private String hitch;

    private List<Long> inventories;

    @NotBlank
    private String complexity;


    public TrainingsModel() {
    }


    public TrainingsModel(String name, String warmUp, String mainTraining, String hitch, List<Long> inventories, String complexity) {
        this.name = name;
        this.warmUp = warmUp;
        this.mainTraining = mainTraining;
        this.hitch = hitch;
        this.inventories = inventories;
        this.complexity = complexity;
    }

    public static TrainingsModel convertToModel(Trainings trainings)
    {
        TrainingsModel trainingsModel = new TrainingsModel(trainings.getName(),trainings.getWarmUp(),
                                                            trainings.getMainTraining(), trainings.getHitch()
                                                            ,trainings.getInventories().stream().
                                                                    map(Inventory::getId).toList(),
                                                            trainings.getComplexity().getName()

                );
        return trainingsModel;
    }
}
