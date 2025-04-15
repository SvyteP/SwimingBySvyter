package com.svyter.spring.swimingbysvyter.model;

import com.svyter.spring.swimingbysvyter.entity.Inventory;
import lombok.Data;

import java.util.ArrayList;

@Data
public class InventoriesModel {
    private ArrayList<Long> inventoriesId;

    public InventoriesModel() {
    }

    public InventoriesModel(ArrayList<Long> inventoriesId) {
        this.inventoriesId = inventoriesId;
    }

    public static InventoriesModel convertToModel(ArrayList<Inventory> inventories)
    {
        ArrayList<Long> inventoriesId = new ArrayList<>();
        inventories.forEach(inventory -> inventoriesId.add(inventory.getId()));
        return new InventoriesModel(inventoriesId);
    }

}
