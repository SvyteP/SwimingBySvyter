package com.svyter.spring.swimingbysvyter.dto;

import com.svyter.spring.swimingbysvyter.entity.Inventory;
import com.svyter.spring.swimingbysvyter.entity.Trainings;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;
@Data
public class TrainingsRegDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String warmUp;
    @NotBlank
    private String mainTraining;
    @NotBlank
    private String hitch;

    private InventoriesDTO inventories;

    @NotBlank
    private ComplexityDTO complexity;


    public TrainingsRegDTO() {
    }


    public TrainingsRegDTO(String name, String warmUp, String mainTraining, String hitch, InventoriesDTO inventories, ComplexityDTO complexity) {
        this.name = name;
        this.warmUp = warmUp;
        this.mainTraining = mainTraining;
        this.hitch = hitch;
        this.inventories = inventories;
        this.complexity = complexity;
    }

    public static TrainingsRegDTO convertToModel(Trainings trainings)
    {
        TrainingsRegDTO trainingsRegDTO = new TrainingsRegDTO(trainings.getName(),trainings.getWarmUp(),
                trainings.getMainTraining(), trainings.getHitch()
                ,InventoriesDTO.convertToModel(trainings.getInventories()),
                ComplexityDTO.convertToModel(trainings.getComplexity()));
        return trainingsRegDTO;
    }
}
