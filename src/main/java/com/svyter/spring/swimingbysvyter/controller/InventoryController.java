package com.svyter.spring.swimingbysvyter.controller;

import com.svyter.spring.swimingbysvyter.dto.InventoriesDTO;
import com.svyter.spring.swimingbysvyter.dto.InventoryDTO;
import com.svyter.spring.swimingbysvyter.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;
    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
    @PostMapping()
    public ResponseEntity createInventory(@RequestBody InventoryDTO inventoryDTO)
    {
        try {
            inventoryService.createInventory(inventoryDTO);
            return ResponseEntity.ok().body(HttpStatus.CREATED);

        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity readInventories()
    {
        try {
            return ResponseEntity.ok().body(inventoryService.readInventories());

        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity readInventory(@PathVariable Long id)
    {
        try {
            return ResponseEntity.ok().body(inventoryService.readInventory(id));
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity editInventory(@RequestBody InventoryDTO inventoryDTO
                                        ,@PathVariable Long id)
    {
        try {
            inventoryService.editInventory(inventoryDTO,id);
            return ResponseEntity.ok().body(HttpStatus.OK);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delInventory(@PathVariable Long id)
    {
        try {
            inventoryService.delInventory(id);
            return ResponseEntity.ok().body(HttpStatus.OK);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/set/{id}")
    public ResponseEntity setInventory(@RequestBody InventoriesDTO inventoriesDTO, @PathVariable Long id)
    {
        try {
            inventoryService.setInventory(inventoriesDTO,id);
            return ResponseEntity.ok().body(HttpStatus.OK);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
