package com.msqrtets.mapper;

import com.msqrtets.entity.Order;
import com.msqrtets.model.dto.OrderRequest;
import com.msqrtets.model.dto.OrderResponse;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "user.id", target = "userId")
    OrderResponse toDto(Order order);

    @Mapping(source = "userId", target = "user.id")
    Order toEntity(OrderRequest dto);

    void updateOrderFromDto(OrderRequest dto, @MappingTarget Order order);

    List<OrderResponse> toDtoList(List<Order> orders);

}
