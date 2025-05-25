package com.svyter.spring.swimingbysvyter.dto;

import com.svyter.spring.swimingbysvyter.dto.base.DTO;
import com.svyter.spring.swimingbysvyter.entity.Inventory;
import lombok.Data;

import java.util.ArrayList;

@Data
public class InventoriesDTO implements DTO {
    private ArrayList<Long> inventoriesId;

    public InventoriesDTO() {
    }

    public InventoriesDTO(ArrayList<Long> inventoriesId) {
        this.inventoriesId = inventoriesId;
    }

    public static InventoriesDTO convertToModel(ArrayList<Inventory> inventories)
    {
        ArrayList<Long> inventoriesId = new ArrayList<>();
        inventories.forEach(inventory -> inventoriesId.add(inventory.getId()));
        return new InventoriesDTO(inventoriesId);
    }

}
