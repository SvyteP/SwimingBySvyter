package com.svyter.spring.swimingbysvyter.controller;

import com.svyter.spring.swimingbysvyter.dto.TrainingsDTO;
import com.svyter.spring.swimingbysvyter.dto.TrainingsRegDTO;
import com.svyter.spring.swimingbysvyter.service.TrainingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/training/admin")
@Validated
public class TrainingsController {
    private final TrainingsService trainingsService;

    @Autowired
    public TrainingsController(TrainingsService trainingsService) {
        this.trainingsService = trainingsService;
    }

    @PostMapping
    public ResponseEntity createTrainings(@RequestBody TrainingsRegDTO trainingsRegDTO) {
        trainingsService.createTrain(trainingsRegDTO);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity editTrainings(@RequestBody TrainingsDTO trainingsDTO, @PathVariable Long id) {
        trainingsService.editTrainings(trainingsDTO, id);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity getTrainings() {
        return ResponseEntity.ok().body(trainingsService.readTrainings());
    }

    @GetMapping("/{id}")
    public ResponseEntity getTrain(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(trainingsService.readTrain(id));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delTrain(@PathVariable Long id) {
        try {
            trainingsService.delTrainings(id);
            return ResponseEntity.ok().body(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
