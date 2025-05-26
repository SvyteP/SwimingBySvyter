package com.svyter.spring.swimingbysvyter.controller;

import com.svyter.spring.swimingbysvyter.dto.ComplexityDTO;
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

    @PostMapping("/admin")
    public ResponseEntity createComplexity(@RequestBody ComplexityDTO complexityDTO) {
        complexityService.createComplexity(complexityDTO);
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getComplexities() {
        return ResponseEntity.ok().body(complexityService.readComplexities());
    }

    @GetMapping("{id}")
    public ResponseEntity getComplexity(@PathVariable Long id) {
        return ResponseEntity.ok().body(complexityService.readComplexity(id));
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity updateComplexity(@RequestBody ComplexityDTO complexityDTO,
                                           @PathVariable Long id) {

        complexityService.editComplexity(complexityDTO, id);
        return ResponseEntity.ok().body(HttpStatus.OK);

    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity delComplexity(@PathVariable Long id) {
        complexityService.delComplexity(id);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

}

