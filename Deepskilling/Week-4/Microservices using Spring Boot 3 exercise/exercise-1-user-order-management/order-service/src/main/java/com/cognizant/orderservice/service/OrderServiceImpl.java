package com.cognizant.orderservice.service;

import com.cognizant.orderservice.client.UserServiceClient;
import com.cognizant.orderservice.dto.OrderRequest;
import com.cognizant.orderservice.dto.UserDTO;
import com.cognizant.orderservice.entity.Order;
import com.cognizant.orderservice.exception.OrderNotFoundException;
import com.cognizant.orderservice.exception.UserNotFoundException;
import com.cognizant.orderservice.repository.OrderRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserServiceClient userServiceClient;

    @Override
    public Order createOrder(OrderRequest request) {
        UserDTO user;
        try {
            user = userServiceClient.getUserById(request.getUserId());
        } catch (FeignException.NotFound ex) {
            throw new UserNotFoundException(request.getUserId());
        }

        if (user == null) {
            throw new UserNotFoundException(request.getUserId());
        }

        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setProduct(request.getProduct());
        order.setQuantity(request.getQuantity());
        order.setTotalAmount(request.getTotalAmount());
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = getOrderById(id);
        orderRepository.delete(order);
    }
}
