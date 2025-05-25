package com.svyter.spring.swimingbysvyter.controller;

import com.svyter.spring.swimingbysvyter.dto.CustomersEditPassDTO;
import com.svyter.spring.swimingbysvyter.dto.CustomersRegDTO;
import com.svyter.spring.swimingbysvyter.service.CustomersService;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Validated
@RequestMapping("/customers")
public class CustomersController {
    private CustomersService customersService;
    @Autowired

    public CustomersController(CustomersService customersService) {
        this.customersService = customersService;
    }
    /*@PostMapping
    public ResponseEntity regCustomers(@Valid @RequestBody CustomersRegDTO customersRegDTO){
        try {
            customersService.regCustomers(customersRegDTO);
            return ResponseEntity.ok().body(HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }*/
    @DeleteMapping("/{id}/{idForDel}")
    public ResponseEntity delCustomers(@PathVariable Long idForDel,
                                       @PathVariable Long id){
        try {
            customersService.delCustomers(idForDel,id);
            return ResponseEntity.ok().body(HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping
    public ResponseEntity editLogin(@RequestParam Long id,
                                    @RequestBody Map<String,String> login){
        try {
            customersService.editLogin(id,login.get("login"));
            return ResponseEntity.ok().body(HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/pass")
    public ResponseEntity editPass(@Valid @RequestBody CustomersEditPassDTO customersEditPassDTO){
            customersService.editPass(customersEditPassDTO.getEmail(), customersEditPassDTO.getPass());
            return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getCustomer(@PathVariable Long id){
        try {

            return ResponseEntity.ok().body(customersService.getCustomer(id));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity getCustomers(){
        try {
            return ResponseEntity.ok().body(customersService.getCustomers());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
