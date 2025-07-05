package com.tan.java.hairhub.services.interfaces;

import java.util.List;

import com.tan.java.hairhub.dto.request.CreateOrderDTO;
import com.tan.java.hairhub.dto.request.UpdateOrderDTO;
import com.tan.java.hairhub.dto.response.OrderResponse;
import com.tan.java.hairhub.entities.Order;

public interface OrderService {
    List<OrderResponse> getAllOrder(int pageIndex, int pageSize);

    OrderResponse getOrderById(int orderId);

    OrderResponse createOrder(CreateOrderDTO createOrderDTO);

    Order updateOrder(UpdateOrderDTO updateOrderDTO) throws Exception;

    void deleteOrder(int orderId) throws Exception;
}
