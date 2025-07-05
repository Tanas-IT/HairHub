package com.tan.java.hairhub.mapper;

import com.tan.java.hairhub.dto.request.CreateOrderDTO;
import com.tan.java.hairhub.dto.request.UpdateOrderDTO;
import com.tan.java.hairhub.dto.response.OrderResponse;
import com.tan.java.hairhub.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {
    OrderResponse toOrderResponse(Order order);

    Order createOrder(CreateOrderDTO createOrderDTO);

    Order updateOrder(UpdateOrderDTO updateOrderDTO, @MappingTarget Order order);
}
