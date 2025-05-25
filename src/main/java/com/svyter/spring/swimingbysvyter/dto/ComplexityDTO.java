package com.svyter.spring.swimingbysvyter.dto;

import com.svyter.spring.swimingbysvyter.dto.base.DTO;
import com.svyter.spring.swimingbysvyter.entity.Complexity;
import lombok.Data;

@Data
public class ComplexityDTO implements DTO {
    private long id;
    private String name;

    public ComplexityDTO() {
    }

    public ComplexityDTO(String name, long id) {
        this.name = name;
        this.id = id;
    }
    public static ComplexityDTO convertToModel(Complexity complexity){
        return new ComplexityDTO(complexity.getName(),complexity.getId());
    }
}
