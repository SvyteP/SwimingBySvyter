package com.svyter.spring.swimingbysvyter.dto;

import com.svyter.spring.swimingbysvyter.dto.base.DTO;
import com.svyter.spring.swimingbysvyter.entity.Inventory;
import lombok.Data;

@Data
public class InventoryRegDTO implements DTO {
    private String name;

    public InventoryRegDTO() {
    }

    public InventoryRegDTO(String name) {
        this.name = name;
    }

    public static InventoryRegDTO convertToModel(Inventory inventory)
    {
        return new InventoryRegDTO(inventory.getName());
    }

}
