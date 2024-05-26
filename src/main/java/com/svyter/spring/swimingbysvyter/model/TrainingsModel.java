package com.svyter.spring.swimingbysvyter.model;

import com.svyter.spring.swimingbysvyter.dto.InventoryRepo;
import com.svyter.spring.swimingbysvyter.dto.TrainingsRepo;
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
    private List<String> inventoryList;
    @Autowired
    private static InventoryRepo inventoryRepo;

    public TrainingsModel() {
    }

    public TrainingsModel(String name, String warmUp, String mainTraining, String hitch, List<String> inventoryList) {
        this.name = name;
        this.warmUp = warmUp;
        this.mainTraining = mainTraining;
        this.hitch = hitch;
        this.inventoryList = inventoryList;
    }

    public static TrainingsModel convertToModel(Trainings trainings)
    {
<<<<<<< HEAD
        TrainingsModel trainingsModel = new TrainingsModel(trainings.getName(),trainings.getWarmUp(),
                                                            trainings.getMainTraining(), trainings.getHitch()
                                                            ,trainings.getInventoryList().stream().map(Inventory::getName).toList()
                );
        return trainingsModel;
=======
        return new TrainingsModel(trainings.getName(),trainings.getWarmUp(),
                                                            trainings.getMainTraining(), trainings.getHitch());
>>>>>>> complexity
    }
}
