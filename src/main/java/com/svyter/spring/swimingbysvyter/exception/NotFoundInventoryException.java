package com.svyter.spring.swimingbysvyter.exception;

public class NotFoundInventoryException extends Exception {
    public NotFoundInventoryException(String inventoryName) {
        super("Инвентарь: " + inventoryName+
                " не найден!");
    }
}
