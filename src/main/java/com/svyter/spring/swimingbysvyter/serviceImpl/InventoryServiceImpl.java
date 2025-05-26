package com.svyter.spring.swimingbysvyter.serviceImpl;

import com.svyter.spring.swimingbysvyter.dto.InventoryWithStockDTO;
import com.svyter.spring.swimingbysvyter.dto.base.ResponseDTO;
import com.svyter.spring.swimingbysvyter.exception.NotFoundDataException;
import com.svyter.spring.swimingbysvyter.repo.CustomersRepo;
import com.svyter.spring.swimingbysvyter.repo.InventoryRepo;
import com.svyter.spring.swimingbysvyter.entity.Customers;
import com.svyter.spring.swimingbysvyter.entity.Inventory;
import com.svyter.spring.swimingbysvyter.dto.InventoriesDTO;
import com.svyter.spring.swimingbysvyter.dto.InventoryRegDTO;
import com.svyter.spring.swimingbysvyter.security.JwtUtils;
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
    private final JwtUtils jwtUtils;

    @Autowired
    public InventoryServiceImpl(InventoryRepo inventoryRepo, CustomersRepo customersRepo, CustomersRepo customersRepo1, MessageSource messageSource, JwtUtils jwtUtils) {
        this.inventoryRepo = inventoryRepo;
        this.customersRepo = customersRepo1;
        this.messageSource = messageSource;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public void createInventory(InventoryRegDTO inventoryRegDTO) {

        if (!inventoryRepo.existsByName(inventoryRegDTO.getName())) {
            Inventory inventory = new Inventory(inventoryRegDTO.getName());
            inventoryRepo.save(inventory);
        } else {
            throw new NotFoundDataException(
                    String.format(messageSource.getMessage("error.inventory.already.exist", null, Locale.getDefault()), "name " + inventoryRegDTO.getName())
            );
        }

    }

    @Override
    public ResponseDTO<List<InventoryWithStockDTO>> getInventories(String token) {
        Long id = jwtUtils.extractUserId(token);
        Customers customer = customersRepo.findById(id).orElseThrow( () -> new NotFoundDataException(
                messageSource.getMessage("error.customer.not.found", new Object[]{"id " + id}, Locale.getDefault())
        ));
        return new  ResponseDTO<>(inventoryRepo.findAll().stream().map(i -> InventoryWithStockDTO.convertToModel(i,customer.getInventories().contains(i))).toList());
    }

    @Override
    public ResponseDTO<InventoryWithStockDTO> getInventory(String token, Long idInv) {
        Long id = jwtUtils.extractUserId(token);
        Customers customer = customersRepo.findById(id).orElseThrow( () -> new NotFoundDataException(
                messageSource.getMessage("error.customer.not.found", new Object[]{"id " + id}, Locale.getDefault())
        ));
        Inventory inventory = inventoryRepo.findById(idInv).orElseThrow(() -> new NotFoundDataException(
                String.format(messageSource.getMessage("error.inventory.notfound", null, Locale.getDefault()), "id " + id)
        ));
        return new  ResponseDTO<>(InventoryWithStockDTO.convertToModel(inventory,customer.getInventories().contains(inventory)));
    }

    @Override
    public void editInventory(InventoryRegDTO inventoryRegDTO, Long id) {
        Inventory inventory = inventoryRepo.findById(id).orElseThrow(() -> new NotFoundDataException(
                String.format(messageSource.getMessage("error.inventory.notfound", null, Locale.getDefault()), "id " + id)
        ));
        inventory.setName(inventoryRegDTO.getName());
        inventoryRepo.save(inventory);
    }

    @Override
    public void delInventory(Long id) {
        inventoryRepo.deleteById(id);
    }

    @Override
    public void setInventory(InventoriesDTO inventoriesDTO, String token) {
        long id = jwtUtils.extractUserId(token);
        Customers customers = customersRepo.findById(id).orElseThrow(() -> new NotFoundDataException(
                String.format(messageSource.getMessage("error.customer.notfound", null, Locale.getDefault()), "id " + id)
        ));
        List<Inventory> inventories = new ArrayList<>();
        inventoryRepo.findAllById(inventoriesDTO.getInventoriesId()).forEach(inventories::add);
        customers.setInventories(inventories);
        customersRepo.save(customers);
    }
}
