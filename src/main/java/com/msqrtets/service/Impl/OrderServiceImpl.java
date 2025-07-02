package com.msqrtets.service.Impl;

import com.msqrtets.model.Order;
import com.msqrtets.model.dto.OrderRequestDto;
import com.msqrtets.model.dto.OrderResponseDto;
import com.msqrtets.model.mapper.OrderMapper;
import com.msqrtets.repository.OrderRepository;
import com.msqrtets.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        return orderMapper.toDto(orderRepository.save(orderMapper.toEntity(orderRequestDto)));
    }

    @Override
    public OrderResponseDto getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::toDto)
                .orElseThrow(()-> new RuntimeException("salam"));
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        return orderMapper.toDtoList(orderRepository.findAll());
    }

    @Override
    public OrderResponseDto updateOrder(Long id, OrderRequestDto dto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        Order entity = orderMapper.toEntity(dto);
        entity.setId(order.getId());

        return orderMapper.toDto(orderRepository.save(entity));
    }


    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);

    }

    @Override
    public List<OrderResponseDto> getOrdersByUserId(Long userId) {
        return orderMapper.toDtoList(orderRepository.findByUserId(userId));
    }
}
