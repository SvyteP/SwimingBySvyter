package com.svyter.spring.swimingbysvyter.serviceImpl;

import com.svyter.spring.swimingbysvyter.dto.base.ResponseDTO;
import com.svyter.spring.swimingbysvyter.exception.DataAlreadyExistException;
import com.svyter.spring.swimingbysvyter.exception.NotFoundDataException;
import com.svyter.spring.swimingbysvyter.repo.ComplexityRepo;
import com.svyter.spring.swimingbysvyter.entity.Complexity;
import com.svyter.spring.swimingbysvyter.dto.ComplexityDTO;
import com.svyter.spring.swimingbysvyter.service.ComplexityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class ComplexityServiceImpl implements ComplexityService {

    private final ComplexityRepo complexityRepo;
    private final MessageSource messageSource;

    @Autowired
    public ComplexityServiceImpl(ComplexityRepo complexityRepo, MessageSource messageSource) {
        this.complexityRepo = complexityRepo;
        this.messageSource = messageSource;
    }

    @Override
    public void createComplexity(ComplexityDTO complexityDTO) {
        if (complexityRepo.existsByName(complexityDTO.getName())) {
            throw new DataAlreadyExistException(
                    String.format(messageSource.getMessage("error.complexity.already.exist", null, Locale.getDefault()), "name: " + complexityDTO.getName())
            );
        }
        complexityRepo.save(new Complexity(complexityDTO.getName()));
    }

    @Override
    public  ResponseDTO<List<ComplexityDTO>> readComplexities() {
        return new ResponseDTO<>(complexityRepo.findAll().stream().map(ComplexityDTO::convertToModel).toList()); //Изменить!
    }

    @Override
    public ResponseDTO<ComplexityDTO> readComplexity(Long id) {
        return new ResponseDTO<>(ComplexityDTO.convertToModel(complexityRepo.findById(id)
                .orElseThrow(() -> new NotFoundDataException(
                        String.format(messageSource.getMessage("error.complexity.notfound", null, Locale.getDefault()), "id " + id))
                )));
    }

    @Override
    public void editComplexity(ComplexityDTO complexityDTO, Long id) {

        Complexity complexity = complexityRepo.findById(id).orElseThrow(() -> new NotFoundDataException(
                String.format(messageSource.getMessage("error.complexity.notfound", null, Locale.getDefault()), "id " + id)
        ));
        complexity.setName(complexityDTO.getName());
        complexityRepo.save(complexity);
    }

    @Override
    public void delComplexity(Long id) {
        complexityRepo.deleteById(id);
    }
}
