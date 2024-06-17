package com.svyter.spring.swimingbysvyter.serviceImpl;

import com.svyter.spring.swimingbysvyter.dto.ComplexityRepo;
import com.svyter.spring.swimingbysvyter.dto.TrainingsRepo;
import com.svyter.spring.swimingbysvyter.entity.Complexity;
import com.svyter.spring.swimingbysvyter.entity.Trainings;
import com.svyter.spring.swimingbysvyter.model.ComplexityModel;
import com.svyter.spring.swimingbysvyter.model.TrainingsModel;
import com.svyter.spring.swimingbysvyter.service.ComplexityService;
import com.svyter.spring.swimingbysvyter.service.TrainingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplexityServiceImpl implements ComplexityService {

    private final ComplexityRepo complexityRepo;
    @Autowired
    public ComplexityServiceImpl(ComplexityRepo complexityRepo) {
        this.complexityRepo = complexityRepo;
    }

    @Override
    public void createComplexity(ComplexityModel complexityModel) {
        try{
           if (complexityRepo.existsByName(complexityModel.getName())){
               throw new RuntimeException("Указанный уровень сложности уже существует!");
           }
            complexityRepo.save(new Complexity(complexityModel.getName()));

        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<ComplexityModel> readComplexities() {
        try {
            return complexityRepo.findAll().stream().map(ComplexityModel :: convertToModel).toList(); //Изменить!

        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ComplexityModel readComplexity(Long id) {
        try{
        return ComplexityModel.convertToModel(complexityRepo.findById(id).orElseThrow());

        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void editComplexity(ComplexityModel complexityModel, Long id) {
        try{
        Complexity complexity = complexityRepo.findById(id).orElseThrow();
        complexity.setName(complexityModel.getName());

        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delComplexity(Long id) {
        try {
            complexityRepo.deleteById(id);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }

    }
}
