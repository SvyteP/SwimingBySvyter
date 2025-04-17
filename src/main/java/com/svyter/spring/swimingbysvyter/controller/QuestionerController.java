package com.svyter.spring.swimingbysvyter.controller;

import com.svyter.spring.swimingbysvyter.dto.QuestionerDTO;
import com.svyter.spring.swimingbysvyter.service.QuestionerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/questioner")
public class QuestionerController {
    private QuestionerService questionerService;
    @Autowired
    public QuestionerController(QuestionerService questionerService) {
        this.questionerService = questionerService;
    }

    @PostMapping
    public ResponseEntity createQuestioner(@Validated @RequestBody QuestionerDTO questionerDTO){
        try {
            questionerService.createQuestioner(questionerDTO);
            return ResponseEntity.ok().body(HttpStatus.OK);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity editQuestioner(@Validated @RequestBody QuestionerDTO questionerDTO,
                                         @PathVariable Long id){
        try {
            questionerService.editQuestioner(questionerDTO,id);
            return ResponseEntity.ok().body(HttpStatus.OK);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity readQuestioner(@PathVariable Long id)
    {
        try {

            return ResponseEntity.ok().body( questionerService.getQuestioner(id));
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delQuestioner(@PathVariable Long id)
    {
        try {
            questionerService.delQuestioner(id);
            return ResponseEntity.ok().body(HttpStatus.OK);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
