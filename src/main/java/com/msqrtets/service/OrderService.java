package com.msqrtets.service;

import com.msqrtets.model.dto.OrderRequest;
import com.msqrtets.model.dto.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);

    OrderResponse getOrderById(Long id);

    List<OrderResponse> getAllOrders();

    OrderResponse updateOrder(Long id, OrderRequest orderRequest);

    void deleteOrder(Long id);

    List<OrderResponse> getOrdersByUserId(Long userId);
}
