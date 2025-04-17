package com.svyter.spring.swimingbysvyter.serviceImpl;

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
        try {
            if (complexityRepo.existsByName(complexityDTO.getName())) {
                throw new DataAlreadyExistException(
                        String.format(messageSource.getMessage("error.complexity.already.exist", null, Locale.getDefault()), "name: " + complexityDTO.getName())
                );
            }
            complexityRepo.save(new Complexity(complexityDTO.getName()));

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<ComplexityDTO> readComplexities() {
        try {
            return complexityRepo.findAll().stream().map(ComplexityDTO::convertToModel).toList(); //Изменить!

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ComplexityDTO readComplexity(Long id) {
        try {
            return ComplexityDTO.convertToModel(complexityRepo.findById(id)
                    .orElseThrow(() -> new NotFoundDataException(
                            String.format(messageSource.getMessage("error.complexity.notfound", null, Locale.getDefault()),"id "+ id))
                    ));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void editComplexity(ComplexityDTO complexityDTO, Long id) {
        try {
            Complexity complexity = complexityRepo.findById(id).orElseThrow(() -> new NotFoundDataException(
                    String.format(messageSource.getMessage("error.complexity.notfound",null,Locale.getDefault()),"id "+ id)
            ));
            complexity.setName(complexityDTO.getName());
            complexityRepo.save(complexity);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delComplexity(Long id) {
        try {
            complexityRepo.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
