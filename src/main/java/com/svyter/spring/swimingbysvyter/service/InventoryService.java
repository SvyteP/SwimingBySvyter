package com.svyter.spring.swimingbysvyter.service;

import com.svyter.spring.swimingbysvyter.dto.InventoriesDTO;
import com.svyter.spring.swimingbysvyter.dto.InventoryRegDTO;
import com.svyter.spring.swimingbysvyter.dto.InventoryWithStockDTO;
import com.svyter.spring.swimingbysvyter.dto.base.ResponseDTO;

import java.util.List;

public interface InventoryService {
    void createInventory(InventoryRegDTO inventoryRegDTO);
    ResponseDTO<List<InventoryWithStockDTO>> getInventories(String token);
    ResponseDTO<InventoryWithStockDTO> getInventory(String token, Long idInv);
    void editInventory(InventoryRegDTO inventoryRegDTO, Long id);
    void delInventory(Long id);
    void setInventory(InventoriesDTO inventoriesDTO, String token);
}
