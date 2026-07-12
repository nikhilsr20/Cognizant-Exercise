package com.cognizant.orderservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderRequest {

    @NotNull(message = "userId is required")
    private Long userId;

    @NotNull(message = "product is required")
    private String product;

    @NotNull(message = "quantity is required")
    private Integer quantity;

    @NotNull(message = "totalAmount is required")
    private BigDecimal totalAmount;
}
