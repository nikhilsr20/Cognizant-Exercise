package com.cognizant.inventoryservice.service;

import com.cognizant.inventoryservice.client.ProductServiceClient;
import com.cognizant.inventoryservice.dto.InventoryResponse;
import com.cognizant.inventoryservice.dto.ProductDTO;
import com.cognizant.inventoryservice.entity.Inventory;
import com.cognizant.inventoryservice.exception.InventoryNotFoundException;
import com.cognizant.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductServiceClient productServiceClient;

    @Override
    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    @Override
    public InventoryResponse getInventoryByProductId(Long productId) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new InventoryNotFoundException(productId));

        ProductDTO product = productServiceClient.getProductById(productId);

        return new InventoryResponse(
                inventory.getId(),
                inventory.getProductId(),
                product != null ? product.getName() : "UNKNOWN",
                inventory.getQuantityAvailable(),
                inventory.getWarehouseLocation()
        );
    }

    @Override
    public Inventory adjustQuantity(Long productId, Integer delta) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new InventoryNotFoundException(productId));
        inventory.setQuantityAvailable(inventory.getQuantityAvailable() + delta);
        return inventoryRepository.save(inventory);
    }
}
