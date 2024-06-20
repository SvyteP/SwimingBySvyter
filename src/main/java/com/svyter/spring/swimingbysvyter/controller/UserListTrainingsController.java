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
    @GetMapping("/one")
    public ResponseEntity getOneUserListTrainings(@RequestBody CustomersTrainingsId customersTrainingsId)
    {
        try {
            return ResponseEntity.ok().body(userListTrainingsService.readOneUserListTrainings(customersTrainingsId));
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }


}
