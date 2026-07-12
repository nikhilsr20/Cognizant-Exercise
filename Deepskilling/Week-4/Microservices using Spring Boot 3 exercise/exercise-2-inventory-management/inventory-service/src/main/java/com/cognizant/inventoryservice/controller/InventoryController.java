package com.cognizant.inventoryservice.controller;

import com.cognizant.inventoryservice.dto.InventoryResponse;
import com.cognizant.inventoryservice.entity.Inventory;
import com.cognizant.inventoryservice.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<Inventory> createInventory(@Valid @RequestBody Inventory inventory) {
        return new ResponseEntity<>(inventoryService.createInventory(inventory), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventory() {
        return ResponseEntity.ok(inventoryService.getAllInventory());
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<InventoryResponse> getInventoryByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(inventoryService.getInventoryByProductId(productId));
    }

    @PatchMapping("/product/{productId}/adjust")
    public ResponseEntity<Inventory> adjustQuantity(@PathVariable Long productId, @RequestParam Integer delta) {
        return ResponseEntity.ok(inventoryService.adjustQuantity(productId, delta));
    }
}
