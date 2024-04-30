package com.svyter.spring.swimingbysvyter.model;

import com.svyter.spring.swimingbysvyter.entity.Inventory;
import lombok.Data;

@Data
public class InventoryModel {
    private String name;
    private boolean  youHave;
    public InventoryModel() {
    }

    public InventoryModel(String name, boolean youHave) {
        this.name = name;
        this.youHave = youHave;
    }
    public static InventoryModel convertToModel(Inventory inventory)
    {
        InventoryModel inventoryModel = new InventoryModel(inventory.getName(),inventory.isYouHave());
        return inventoryModel;
    }

}
