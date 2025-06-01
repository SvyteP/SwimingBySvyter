package com.svyter.spring.swimingbysvyter.service;

import com.svyter.spring.swimingbysvyter.dto.InventoryRegDTO;
import com.svyter.spring.swimingbysvyter.dto.InventoryWithStockDTO;
import com.svyter.spring.swimingbysvyter.dto.base.ResponseDTO;

import java.util.List;

public interface InventoryService {
    void createInventory(InventoryRegDTO inventoryRegDTO);
    ResponseDTO<List<InventoryWithStockDTO>> getInventoriesWithToken(String token);
    ResponseDTO<List<InventoryWithStockDTO>> getInventories();
    ResponseDTO<InventoryWithStockDTO> getInventory(String token, Long idInv);
    void editInventory(InventoryRegDTO inventoryRegDTO, Long id);
    void delInventory(Long id);
    void setInventory(List<Long> inventoriesId, String token);
}
