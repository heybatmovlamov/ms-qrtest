package com.msqrtets.service;

import com.msqrtets.model.Order;
import com.msqrtets.model.dto.OrderRequestDto;
import com.msqrtets.model.dto.OrderResponseDto;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);

    OrderResponseDto getOrderById(Long id);

    List<OrderResponseDto> getAllOrders();

    OrderResponseDto updateOrder(Long id, OrderRequestDto orderRequestDto);

    void deleteOrder(Long id);

    List<OrderResponseDto> getOrdersByUserId(Long userId);
}
