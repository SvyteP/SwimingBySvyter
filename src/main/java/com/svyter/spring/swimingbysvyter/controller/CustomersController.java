package com.svyter.spring.swimingbysvyter.controller;

import com.svyter.spring.swimingbysvyter.dto.CustomersEditPassDTO;
import com.svyter.spring.swimingbysvyter.service.CustomersService;
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

    @DeleteMapping("/admin/{idForDel}")
    public ResponseEntity delCustomers(@PathVariable Long idForDel) {
        customersService.delCustomers(idForDel);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PutMapping("/login")
    public ResponseEntity editLogin(@RequestHeader(value = "Authorization") String token,
                                    @RequestBody Map<String, String> login) {
        customersService.editLogin(token, login.get("login"));
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PutMapping("/pass")
    public ResponseEntity editPass(@RequestHeader(value = "Authorization") String token, @RequestBody CustomersEditPassDTO customersEditPassDTO) {
        customersService.editPass(token, customersEditPassDTO.getPass());
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getCustomer(@RequestHeader(value = "Authorization") String token) {
        return ResponseEntity.ok().body(customersService.getCustomer(token));
    }

    @GetMapping("/admin")
    public ResponseEntity getCustomers() {
        return ResponseEntity.ok().body(customersService.getCustomers());
    }
}
