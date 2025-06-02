package com.svyter.spring.swimingbysvyter.controller;

import com.svyter.spring.swimingbysvyter.service.UserListTrainingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer/trainings")
public class UserListTrainingsController {
    private final UserListTrainingsService userListTrainingsService;

    @Autowired
    public UserListTrainingsController(UserListTrainingsService userListTrainingsService) {
        this.userListTrainingsService = userListTrainingsService;
    }

    // Подбор тренировок
    @PostMapping
    public ResponseEntity generateTrainings(@RequestHeader(value = "Authorization") String token,
                                            @RequestParam boolean isRelevation) {
        return ResponseEntity.ok().body(userListTrainingsService.createUserTraining(token, isRelevation));
    }

    // Вывод списка всех тренировок, которые были подобраны пользователям
    @GetMapping("/admin/all")
    public ResponseEntity getAllCustomersListTrainings() {
        return ResponseEntity.ok().body(userListTrainingsService.getAllUserTrainings());
    }

    // Вывод всех тренировок 1-го пользователя
    @GetMapping("/all/user")
    public ResponseEntity getAllForCustomers(@RequestHeader(value = "Authorization") String token) {
        return ResponseEntity.ok().body(userListTrainingsService.getUserTrainings(token));
    }

    // Вывод всех выполненных/не выполненных тренировок
    @GetMapping("/all/isCompleted")
    public ResponseEntity getAllIsCompletedForCustomers(@RequestHeader(value = "Authorization") String token, @RequestParam Boolean isCompleted) {
        return ResponseEntity.ok().body(userListTrainingsService.getIsCompletedUserTrainings(token, isCompleted));
    }

    // Вывод всех выполненных/не выполненных тренировок
    @GetMapping("/all/isLiked")
    public ResponseEntity getAllIsLikeForCustomers(@RequestHeader(value = "Authorization") String token, @RequestParam Boolean isLike) {
        return ResponseEntity.ok().body(userListTrainingsService.getIsLikeUserTrainings(token, isLike));
    }

    // Вывод конкретной тренировки по id-тренировки
    @GetMapping("/one/{userTrainingId}")
    public ResponseEntity getOneUserListTrainings(@PathVariable Long userTrainingId) {
        return ResponseEntity.ok().body(userListTrainingsService.getOneUserTraining(userTrainingId));
    }

    // Удаление подобранной тренировки(по id-подобранной тренировки)
    @DeleteMapping("/{userTrainingId}")
    public ResponseEntity deleteOneUserListTrainings(@PathVariable Long userTrainingId) {
        userListTrainingsService.deleteUserTraining(userTrainingId);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PutMapping("/like/{userTrainingId}")
    public ResponseEntity isLikeTraining(@PathVariable Long userTrainingId,
                                         @RequestParam boolean isLike) {
        userListTrainingsService.isLikeTraining(userTrainingId, isLike);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PutMapping("/complete/{userTrainingId}")
    public ResponseEntity isCompliteTraining(@PathVariable Long userTrainingId,
                                             @RequestParam boolean isCompl) {
        userListTrainingsService.isCompliteTraining(userTrainingId, isCompl);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping("/complete")
    public ResponseEntity complitedTrainingsList(@RequestHeader(value = "Authorization") String token) {
        try {
            return ResponseEntity.ok().body(userListTrainingsService.isCompletedUserTraining(token));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/like")
    public ResponseEntity likeTrainingsList(@RequestHeader(value = "Authorization") String token) {
        return ResponseEntity.ok().body(userListTrainingsService.isLikeUserTraining(token));
    }
}
