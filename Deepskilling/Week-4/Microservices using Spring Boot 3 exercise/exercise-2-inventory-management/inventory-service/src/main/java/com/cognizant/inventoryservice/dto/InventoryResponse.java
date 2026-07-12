package com.cognizant.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponse {
    private Long inventoryId;
    private Long productId;
    private String productName;
    private Integer quantityAvailable;
    private String warehouseLocation;
}
