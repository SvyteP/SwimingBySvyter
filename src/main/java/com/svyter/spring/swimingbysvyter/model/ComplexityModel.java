package com.svyter.spring.swimingbysvyter.model;

import com.svyter.spring.swimingbysvyter.entity.Complexity;
import lombok.Data;

@Data
public class ComplexityModel {
    private String name;

    public ComplexityModel() {
    }
    public ComplexityModel(String name) {
        this.name = name;
    }
    public static ComplexityModel convertToModel(Complexity complexity){
        return new ComplexityModel(complexity.getName());
    }
}
