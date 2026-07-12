package com.cognizant.inventoryservice.service;

import com.cognizant.inventoryservice.dto.InventoryResponse;
import com.cognizant.inventoryservice.entity.Inventory;

import java.util.List;

public interface InventoryService {
    Inventory createInventory(Inventory inventory);
    List<Inventory> getAllInventory();
    InventoryResponse getInventoryByProductId(Long productId);
    Inventory adjustQuantity(Long productId, Integer delta);
}
