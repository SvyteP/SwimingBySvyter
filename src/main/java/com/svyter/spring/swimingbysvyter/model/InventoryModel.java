package com.svyter.spring.swimingbysvyter.model;

import com.svyter.spring.swimingbysvyter.entity.Inventory;
import lombok.Data;

@Data
public class InventoryModel {
    private String name;

    public InventoryModel() {
    }

    public InventoryModel(String name) {
        this.name = name;
    }

    public static InventoryModel convertToModel(Inventory inventory)
    {
        InventoryModel inventoryModel = new InventoryModel(inventory.getName());
        return inventoryModel;
    }

}
