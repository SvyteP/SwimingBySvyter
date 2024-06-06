package com.svyter.spring.swimingbysvyter.controller;

import com.svyter.spring.swimingbysvyter.model.QuestionerModel;
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
    public ResponseEntity createQuestioner(@Validated @RequestBody QuestionerModel questionerModel){
        try {
            questionerService.createQuestioner(questionerModel);
            return ResponseEntity.ok().body(HttpStatus.OK);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity editQuestioner(@Validated @RequestBody QuestionerModel questionerModel,
                                         @RequestParam Long id){
        try {
            questionerService.editQuestioner(questionerModel,id);
            return ResponseEntity.ok().body(HttpStatus.OK);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity readQuestioner(@RequestParam Long id)
    {
        try {

            return ResponseEntity.ok().body( questionerService.getQuestioner(id));
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping
    public ResponseEntity delQuestioner(@RequestParam Long id)
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
