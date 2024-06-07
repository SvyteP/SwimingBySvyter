package com.svyter.spring.swimingbysvyter.serviceImpl;

import com.svyter.spring.swimingbysvyter.dto.CustomersRepo;
import com.svyter.spring.swimingbysvyter.dto.InventoryRepo;
import com.svyter.spring.swimingbysvyter.entity.Customers;
import com.svyter.spring.swimingbysvyter.entity.Inventory;
import com.svyter.spring.swimingbysvyter.model.InventoryModel;
import com.svyter.spring.swimingbysvyter.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepo inventoryRepo;
    private final CustomersRepo customersRepo;
    @Autowired
    public InventoryServiceImpl(InventoryRepo inventoryRepo, CustomersRepo customersRepo, CustomersRepo customersRepo1) {
        this.inventoryRepo = inventoryRepo;
        this.customersRepo = customersRepo1;
    }

    @Override
    public void createInventory(InventoryModel inventoryModel) {
        try {
            Inventory inventory = new Inventory(inventoryModel.getName());

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

    @Override
    public void setInventory(List<InventoryModel> inventoryModels, Long id) {
        try {
            Customers customers =  customersRepo.findById(id).orElseThrow();
            List<Inventory> inventories = inventoryModels.stream()
                    .map(inventoryModel -> (inventoryRepo.findByName(inventoryModel.getName()))).toList();
            customers.setInventories(inventories);
            customersRepo.save(customers);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }

    }
}
