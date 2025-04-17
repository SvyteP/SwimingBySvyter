package com.svyter.spring.swimingbysvyter.serviceImpl;

import com.svyter.spring.swimingbysvyter.exception.NotFoundDataException;
import com.svyter.spring.swimingbysvyter.repo.CustomersRepo;
import com.svyter.spring.swimingbysvyter.repo.InventoryRepo;
import com.svyter.spring.swimingbysvyter.entity.Customers;
import com.svyter.spring.swimingbysvyter.entity.Inventory;
import com.svyter.spring.swimingbysvyter.dto.InventoriesDTO;
import com.svyter.spring.swimingbysvyter.dto.InventoryDTO;
import com.svyter.spring.swimingbysvyter.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepo inventoryRepo;
    private final CustomersRepo customersRepo;
    private final MessageSource messageSource;

    @Autowired
    public InventoryServiceImpl(InventoryRepo inventoryRepo, CustomersRepo customersRepo, CustomersRepo customersRepo1, MessageSource messageSource) {
        this.inventoryRepo = inventoryRepo;
        this.customersRepo = customersRepo1;
        this.messageSource = messageSource;
    }

    @Override
    public void createInventory(InventoryDTO inventoryDTO) {
        try {
            if (inventoryRepo.existsByName(inventoryDTO.getName())) {
                Inventory inventory = new Inventory(inventoryDTO.getName());
                inventoryRepo.save(inventory);
            }
            else{
                throw new NotFoundDataException(
                        String.format(messageSource.getMessage("error.inventory.already.exist",null, Locale.getDefault()),"name " +inventoryDTO.getName())
                );
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<InventoryDTO> readInventories() {
        try {
            return inventoryRepo.findAll().stream().map(InventoryDTO::convertToModel).toList();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public InventoryDTO readInventory(Long id) {
        try {
            return InventoryDTO.convertToModel(inventoryRepo.findById(id).orElseThrow(() -> new NotFoundDataException(
                    String.format(messageSource.getMessage("error.inventory.notfound",null, Locale.getDefault()),"id " +id)
            )));
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void editInventory(InventoryDTO inventoryDTO, Long id) {
        try {
            Inventory inventory  = inventoryRepo.findById(id).orElseThrow(() -> new NotFoundDataException(
                    String.format(messageSource.getMessage("error.inventory.notfound",null, Locale.getDefault()),"id " +id)
            ));
            inventory.setName(inventoryDTO.getName());
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
    public void setInventory(InventoriesDTO inventoriesDTO, Long id) {
        try {
            Customers customers =  customersRepo.findById(id).orElseThrow(() -> new NotFoundDataException(
                    String.format(messageSource.getMessage("error.customer.notfound",null, Locale.getDefault()),"id " +id)
            ));
            List<Inventory> inventories = new ArrayList<>() ;
            inventoryRepo.findAllById(inventoriesDTO.getInventoriesId()).forEach(inventories::add);
            customers.setInventories(inventories);
            customersRepo.save(customers);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }

    }
}
