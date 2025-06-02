package com.svyter.spring.swimingbysvyter.controller;

import com.svyter.spring.swimingbysvyter.dto.InventoryRegDTO;
import com.svyter.spring.swimingbysvyter.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/create")
    public ResponseEntity createInventory(@RequestBody InventoryRegDTO inventoryRegDTO) {
        inventoryService.createInventory(inventoryRegDTO);
        return ResponseEntity.ok().body(new ResponseEntity<HttpStatus>(HttpStatus.CREATED));
    }

    @GetMapping
    public ResponseEntity getInventoriesWithToken(@RequestHeader(value = "Authorization") String token) {
        return ResponseEntity.ok().body(inventoryService.getInventoriesWithToken(token));
    }

    @GetMapping("get")
    public ResponseEntity getInventories(){
        return ResponseEntity.ok().body(inventoryService.getInventories());
    }


    @GetMapping("/{idInv}")
    public ResponseEntity getInventory(@RequestHeader(value = "Authorization") String token,
                                       @PathVariable Long idInv) {
        return ResponseEntity.ok().body(inventoryService.getInventory(token, idInv));
    }

    @PutMapping("/{id}")
    public ResponseEntity editInventory(@RequestBody InventoryRegDTO inventoryRegDTO
            , @PathVariable Long id) {
        inventoryService.editInventory(inventoryRegDTO, id);
        return ResponseEntity.ok().body(new ResponseEntity<HttpStatus>(HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delInventory(@PathVariable Long id) {
        inventoryService.delInventory(id);
        return ResponseEntity.ok().body(new ResponseEntity<HttpStatus>(HttpStatus.OK));
    }

    @PutMapping("/set")
    public ResponseEntity setInventory(@RequestBody List<Long> inventoriesDTO,
                                       @RequestHeader(value = "Authorization") String token) {
        inventoryService.setInventory(inventoriesDTO, token);
        return ResponseEntity.ok().body(new ResponseEntity<HttpStatus>(HttpStatus.OK));
    }

}
