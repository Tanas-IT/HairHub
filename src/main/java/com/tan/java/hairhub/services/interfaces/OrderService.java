package com.tan.java.hairhub.services.interfaces;

import com.tan.java.hairhub.dto.request.CreateOrderDTO;
import com.tan.java.hairhub.dto.request.UpdateOrderDTO;
import com.tan.java.hairhub.dto.response.OrderResponse;
import com.tan.java.hairhub.entities.Order;

import java.util.List;

public interface OrderService {
    List<OrderResponse> getAllOrder(int pageIndex, int pageSize);

    OrderResponse getOrderById(int orderId);
    OrderResponse createOrder(CreateOrderDTO createOrderDTO);

    Order updateOrder(UpdateOrderDTO updateOrderDTO) throws Exception;
    void deleteOrder(int orderId) throws Exception;
}
