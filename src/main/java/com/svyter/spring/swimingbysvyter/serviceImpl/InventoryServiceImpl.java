package com.svyter.spring.swimingbysvyter.serviceImpl;

import com.svyter.spring.swimingbysvyter.dto.InventoryRepo;
import com.svyter.spring.swimingbysvyter.entity.Inventory;
import com.svyter.spring.swimingbysvyter.model.InventoryModel;
import com.svyter.spring.swimingbysvyter.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepo inventoryRepo;
    @Autowired
    public InventoryServiceImpl(InventoryRepo inventoryRepo) {
        this.inventoryRepo = inventoryRepo;
    }

    @Override
    public void createInventory(InventoryModel inventoryModel) {
        try {
            Inventory inventory = new Inventory(inventoryModel.getName(),inventoryModel.isYouHave());
            inventoryRepo.save(inventory);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<InventoryModel> readInventories() {
        try {
            return inventoryRepo.findAll().stream().map(InventoryModel::convertToModel).toList();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public InventoryModel readInventory(Long id) {
        try {
            return InventoryModel.convertToModel(inventoryRepo.findById(id).orElseThrow());
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void editInventory(InventoryModel inventoryModel, Long id) {
        try {
            Inventory inventory  = inventoryRepo.findById(id).orElseThrow();
            inventory.setName(inventoryModel.getName());
            inventory.setYouHave(inventory.isYouHave());
            inventoryRepo.save(inventory);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delInventory(Long id) {
        try {
            inventoryRepo.deleteById(id);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }

    }
}
