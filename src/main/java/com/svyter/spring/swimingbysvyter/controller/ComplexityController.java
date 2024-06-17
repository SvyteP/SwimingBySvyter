package com.svyter.spring.swimingbysvyter.controller;

import com.svyter.spring.swimingbysvyter.model.ComplexityModel;
import com.svyter.spring.swimingbysvyter.service.ComplexityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/compl")
public class ComplexityController {
    private final ComplexityService complexityService;
    @Autowired
    public ComplexityController(ComplexityService complexityService) {
        this.complexityService = complexityService;
    }
    @PostMapping
    public ResponseEntity createComplexity (@RequestBody ComplexityModel complexityModel)
    {
        try {
            complexityService.createComplexity(complexityModel);
            return ResponseEntity.ok().body(HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity getComplexities()
    {
        try {
            return ResponseEntity.ok().body(complexityService.readComplexities());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("{id}")
    public ResponseEntity getComplexity(@PathVariable Long id)
    {
        try {
            return ResponseEntity.ok().body(complexityService.readComplexity(id));
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("{id}")
    public ResponseEntity updateComplexity(@RequestBody ComplexityModel complexityModel,
                                           @PathVariable Long id){
        try {
            complexityService.editComplexity(complexityModel,id);
            return ResponseEntity.ok().body(HttpStatus.OK);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity delComplexity(@PathVariable Long id)
    {
        try {
            complexityService.delComplexity(id);
            return ResponseEntity.ok().body(HttpStatus.OK);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}

