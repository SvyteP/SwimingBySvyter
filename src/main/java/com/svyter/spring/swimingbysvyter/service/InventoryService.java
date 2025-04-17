package com.svyter.spring.swimingbysvyter.service;

import com.svyter.spring.swimingbysvyter.dto.InventoriesDTO;
import com.svyter.spring.swimingbysvyter.dto.InventoryDTO;

import java.util.List;

public interface InventoryService {
    void createInventory(InventoryDTO inventoryDTO);
    List<InventoryDTO> readInventories();
    InventoryDTO readInventory(Long id);
    void editInventory(InventoryDTO inventoryDTO, Long id);
    void delInventory(Long id);
    void setInventory(InventoriesDTO inventoriesDTO, Long id);
}
