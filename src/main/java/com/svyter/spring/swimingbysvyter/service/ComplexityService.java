package com.svyter.spring.swimingbysvyter.service;

import com.svyter.spring.swimingbysvyter.dto.ComplexityDTO;
import com.svyter.spring.swimingbysvyter.dto.base.ResponseDTO;

import java.util.List;

public interface ComplexityService {
    void createComplexity(ComplexityDTO complexityDTO);
    ResponseDTO<List<ComplexityDTO>> readComplexities();
    ResponseDTO<ComplexityDTO> readComplexity(Long id);
    void editComplexity(ComplexityDTO complexityDTO, Long id);
    void delComplexity(Long id);
}
