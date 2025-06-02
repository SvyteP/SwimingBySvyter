package com.svyter.spring.swimingbysvyter.dto;

import com.svyter.spring.swimingbysvyter.entity.Inventory;
import lombok.Data;

@Data
public class InventoryWithStockDTO {
    private Long id;
    private String name;
    private Boolean isStock;

    public InventoryWithStockDTO() {
    }

    public InventoryWithStockDTO(Long id, String name, Boolean isStock) {
        this.id = id;
        this.name = name;
        this.isStock = isStock;
    }

    public static InventoryWithStockDTO convertToModel(Inventory inventory, Boolean isStock)
    {
        return new InventoryWithStockDTO(inventory.getId(), inventory.getName(),isStock);
    }

}
