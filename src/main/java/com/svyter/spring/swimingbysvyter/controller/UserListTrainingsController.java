package com.svyter.spring.swimingbysvyter.controller;

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

    @PostMapping("/{idCustomers}")
    public ResponseEntity delTrain(@PathVariable Long idCustomers)
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

}
