package com.msqrtets.service.Impl;

import com.msqrtets.entity.Order;
import com.msqrtets.model.dto.OrderRequest;
import com.msqrtets.model.dto.OrderResponse;
import com.msqrtets.mapper.OrderMapper;
import com.msqrtets.repository.OrderRepository;
import com.msqrtets.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;

    @Override
    public OrderResponse createOrder(OrderRequest dto) {
        Order entity = mapper.toEntity(dto);
        log.info(entity.toString());
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("salam"));
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public OrderResponse updateOrder(Long id, OrderRequest dto) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        mapper.updateOrderFromDto(dto, order);

        return mapper.toDto(repository.save(order));
    }


    @Override
    public void deleteOrder(Long id) {
        repository.deleteById(id);

    }

    @Override
    public List<OrderResponse> getOrdersByUserId(Long userId) {
        return mapper.toDtoList(repository.findByUserId(userId));
    }
}
