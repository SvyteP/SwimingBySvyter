package com.svyter.spring.swimingbysvyter.service;

import com.svyter.spring.swimingbysvyter.model.InventoryModel;

import java.util.List;

public interface InventoryService {
    void createInventory(InventoryModel inventoryModel);
    List<InventoryModel> readInventories();
    InventoryModel readInventory(Long id);
    void editInventory(InventoryModel inventoryModel,Long id);
    void delInventory(Long id);
    void setInventory(List<InventoryModel> inventoryModels,Long id);
}
