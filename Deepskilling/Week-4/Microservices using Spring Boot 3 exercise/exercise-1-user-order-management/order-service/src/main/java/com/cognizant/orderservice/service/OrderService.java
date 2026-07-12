package com.cognizant.orderservice.service;

import com.cognizant.orderservice.dto.OrderRequest;
import com.cognizant.orderservice.entity.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderRequest request);
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    List<Order> getOrdersByUserId(Long userId);
    void deleteOrder(Long id);
}
