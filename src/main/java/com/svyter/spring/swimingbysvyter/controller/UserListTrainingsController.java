package com.svyter.spring.swimingbysvyter.controller;

import com.svyter.spring.swimingbysvyter.joinClass.CustomersTrainingsId;
import com.svyter.spring.swimingbysvyter.service.UserListTrainingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("listTrainings")
public class UserListTrainingsController {
    private final UserListTrainingsService userListTrainingsService;
    @Autowired
    public UserListTrainingsController(UserListTrainingsService userListTrainingsService) {
        this.userListTrainingsService = userListTrainingsService;
    }
    // Подбор тренировок
    @PostMapping("/{idCustomers}")
    public ResponseEntity generateTrainings(@PathVariable Long idCustomers)
    {
        try {
            userListTrainingsService.createUserListTrainings(idCustomers);
            return ResponseEntity.ok().body(HttpStatus.OK);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }
    // Вывод списка всех тренировок, которые были подобраны пользователям
    @GetMapping
    public ResponseEntity getAllCustomersListTrainings()
    {
        try {
            return ResponseEntity.ok().body(userListTrainingsService.readAllUserListTrainings());
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }
    // Вывод всех тренировок 1-го пользователя
    @GetMapping("/{idCustomers}")
    public ResponseEntity getAllForCustomers(@PathVariable Long idCustomers)
    {
        try {
            return ResponseEntity.ok().body(userListTrainingsService.readUserListTrainings(idCustomers));
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }
    // Вывод конкретной тренировки по id-пользователя и id-тренировки
    @GetMapping("/one/{idCustomers}/{idTraining}")
    public ResponseEntity getOneUserListTrainings(@PathVariable Long idCustomers,
                                                  @PathVariable Long idTraining)
    {
        try {
            return ResponseEntity.ok().body(userListTrainingsService.readOneUserListTrainings(idTraining,idCustomers));
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }
    // Удаление подобранной тренировки(по id-пользователя и id-тренировки)
    @DeleteMapping("/{idCustomers}/{idTraining}")
    public ResponseEntity deleteOneUserListTrainings(@PathVariable Long idCustomers,
                                                     @PathVariable Long idTraining)
    {
        try {
            userListTrainingsService.deleteOneUserListTrainings(idTraining,idCustomers);
            return ResponseEntity.ok().body(HttpStatus.OK);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/like/{idCustomers}/{idTraining}")
    public ResponseEntity isLikeTraining(@PathVariable Long idCustomers,
                                             @PathVariable Long idTraining,
                                             @RequestParam boolean isLike)
    {
        try {
            userListTrainingsService.isLikeTraining(idTraining,idCustomers,isLike);
            return ResponseEntity.ok().body(HttpStatus.OK);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }
    @PutMapping("/complite/{idCustomers}/{idTraining}")
    public ResponseEntity isCompliteTraining(@PathVariable Long idCustomers,
                                             @PathVariable Long idTraining,
                                             @RequestParam boolean isCompl)
    {
        try {
            userListTrainingsService.isCompliteTraining(idTraining,idCustomers,isCompl);
            return ResponseEntity.ok().body(HttpStatus.OK);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }
    @GetMapping("/complite/{customersId}")
    public ResponseEntity complitedTrainingsList(@PathVariable Long customersId)
    {
        try {
            return ResponseEntity.ok().body(userListTrainingsService.isComplitedTrainingsList(customersId));
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }
    @GetMapping("/like/{customersId}")
    public ResponseEntity likeTrainingsList(@PathVariable Long customersId)
    {
        try {
            return ResponseEntity.ok().body(userListTrainingsService.isLikeTrainingsList(customersId));
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }


}
