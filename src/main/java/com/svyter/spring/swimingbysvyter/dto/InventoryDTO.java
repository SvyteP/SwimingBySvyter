package com.svyter.spring.swimingbysvyter.dto;

import com.svyter.spring.swimingbysvyter.entity.Inventory;
import lombok.Data;

@Data
public class InventoryDTO {
    private String name;

    public InventoryDTO() {
    }

    public InventoryDTO(String name) {
        this.name = name;
    }

    public static InventoryDTO convertToModel(Inventory inventory)
    {
        InventoryDTO inventoryDTO = new InventoryDTO(inventory.getName());
        return inventoryDTO;
    }

}
