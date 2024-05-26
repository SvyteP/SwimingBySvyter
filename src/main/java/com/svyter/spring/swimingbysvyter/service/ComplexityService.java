package com.svyter.spring.swimingbysvyter.service;

import com.svyter.spring.swimingbysvyter.model.ComplexityModel;
import com.svyter.spring.swimingbysvyter.model.TrainingsModel;

import java.util.List;

public interface ComplexityService {
    void createComplexity(ComplexityModel complexityModel);
    List<ComplexityModel> readComplexities();
    ComplexityModel readComplexity(Long id);
    void editComplexity(ComplexityModel complexityModel,Long id);
    void delComplexity(Long id);
}
