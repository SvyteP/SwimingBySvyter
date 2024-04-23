package com.svyter.spring.swimingbysvyter.controller;

import com.svyter.spring.swimingbysvyter.model.CustomersEditPass;
import com.svyter.spring.swimingbysvyter.model.CustomersRegModel;
import com.svyter.spring.swimingbysvyter.service.CustomersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/customers")
public class CustomersController {
    private CustomersService customersService;
    @Autowired

    public CustomersController(CustomersService customersService) {
        this.customersService = customersService;
    }
    @PostMapping
    public ResponseEntity<String> regCustomers( @Valid CustomersRegModel customersRegModel){
        try {
            customersService.regCustomers(customersRegModel);
            return ResponseEntity.ok().body("Customers was registered");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping
    public ResponseEntity<String> delCustomers(@RequestParam Long idForDel,
                                               @RequestParam Long id){
        try {
            customersService.delCustomers(idForDel,id);
            return ResponseEntity.ok().body("Customers was deleted");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping
    public ResponseEntity<String> editLogin(@RequestParam Long id,
                                               @RequestBody String login){
        try {
            customersService.editLogin(id,login);
            return ResponseEntity.ok().body("Login was edited");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/pass")
    public ResponseEntity<String> editPass(@Valid @RequestBody CustomersEditPass customersEditPass){
        try {
            customersService.editPass(customersEditPass.getEmail(), customersEditPass.getPass());
            return ResponseEntity.ok().body("Pass was edited");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
