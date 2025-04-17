package com.svyter.spring.swimingbysvyter.service;

import com.svyter.spring.swimingbysvyter.dto.ComplexityDTO;

import java.util.List;

public interface ComplexityService {
    void createComplexity(ComplexityDTO complexityDTO);
    List<ComplexityDTO> readComplexities();
    ComplexityDTO readComplexity(Long id);
    void editComplexity(ComplexityDTO complexityDTO, Long id);
    void delComplexity(Long id);
}
