package com.svyter.spring.swimingbysvyter.dto;

import com.svyter.spring.swimingbysvyter.entity.Inventory;

public record InventoryDto(long id, String name) {

    public static InventoryDto convertToModel(Inventory inventory)
    {
        return new InventoryDto(inventory.getId(), inventory.getName());
    }
}
