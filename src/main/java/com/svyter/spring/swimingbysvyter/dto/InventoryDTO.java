package com.svyter.spring.swimingbysvyter.dto;

import com.svyter.spring.swimingbysvyter.dto.base.DTO;
import com.svyter.spring.swimingbysvyter.entity.Inventory;
import lombok.Data;

@Data
public class InventoryDTO implements DTO {
    private String name;

    public InventoryDTO() {
    }

    public InventoryDTO(String name) {
        this.name = name;
    }

    public static InventoryDTO convertToModel(Inventory inventory)
    {
        return new InventoryDTO(inventory.getName());
    }

}
