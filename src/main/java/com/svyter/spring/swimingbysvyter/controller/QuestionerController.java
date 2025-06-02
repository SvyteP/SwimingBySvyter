package com.svyter.spring.swimingbysvyter.controller;

import com.svyter.spring.swimingbysvyter.dto.QuestionerDTO;
import com.svyter.spring.swimingbysvyter.dto.QuestionerEditDTO;
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
    public ResponseEntity createQuestioner(@Validated @RequestBody QuestionerEditDTO questionerEditDTO,
                                           @RequestHeader(value = "Authorization") String token) {
        questionerService.createQuestioner(questionerEditDTO, token);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity editQuestioner(@Validated @RequestBody QuestionerEditDTO questionerEditDTO,
                                         @RequestHeader(value = "Authorization") String token) {
        return ResponseEntity.ok().body(questionerService.editQuestioner(questionerEditDTO, token));
    }

    @GetMapping
    public ResponseEntity readQuestioner(@RequestHeader(value = "Authorization") String token) {
        return ResponseEntity.ok().body(questionerService.getQuestioner(token));
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity delQuestioner(@PathVariable Long id) {
        questionerService.delQuestioner(id);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
