package com.svyter.spring.swimingbysvyter.dto;

import com.svyter.spring.swimingbysvyter.entity.Complexity;
import lombok.Data;

@Data
public class ComplexityDTO {
    private String name;

    public ComplexityDTO() {
    }
    public ComplexityDTO(String name) {
        this.name = name;
    }
    public static ComplexityDTO convertToModel(Complexity complexity){
        return new ComplexityDTO(complexity.getName());
    }
}
