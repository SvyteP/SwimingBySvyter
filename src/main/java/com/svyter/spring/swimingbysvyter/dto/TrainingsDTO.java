package com.svyter.spring.swimingbysvyter.dto;

import com.svyter.spring.swimingbysvyter.dto.base.DTO;
import com.svyter.spring.swimingbysvyter.entity.Inventory;
import com.svyter.spring.swimingbysvyter.entity.Trainings;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class TrainingsDTO implements DTO {
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


    public TrainingsDTO() {
    }


    public TrainingsDTO(String name, String warmUp, String mainTraining, String hitch, List<Long> inventories, String complexity) {
        this.name = name;
        this.warmUp = warmUp;
        this.mainTraining = mainTraining;
        this.hitch = hitch;
        this.inventories = inventories;
        this.complexity = complexity;
    }

    public static TrainingsDTO convertToModel(Trainings trainings)
    {
        TrainingsDTO trainingsDTO = new TrainingsDTO(trainings.getName(),trainings.getWarmUp(),
                                                            trainings.getMainTraining(), trainings.getHitch()
                                                            ,trainings.getInventories().stream().
                                                                    map(Inventory::getId).toList(),
                                                            trainings.getComplexity().getName()

                );
        return trainingsDTO;
    }
}
