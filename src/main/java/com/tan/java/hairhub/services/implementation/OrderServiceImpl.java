package com.tan.java.hairhub.services.implementation;

import com.tan.java.hairhub.dto.request.CreateOrderDTO;
import com.tan.java.hairhub.dto.request.UpdateOrderDTO;
import com.tan.java.hairhub.dto.response.OrderResponse;
import com.tan.java.hairhub.entities.Order;
import com.tan.java.hairhub.mapper.OrderMapper;
import com.tan.java.hairhub.repositories.OrderRepository;
import com.tan.java.hairhub.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }


    @Override
    public List<OrderResponse> getAllOrder(int pageIndex, int pageSize) {
        List<Order> listOrder = this.orderRepository.getAllOrder((pageIndex - 1) * pageSize, pageSize);
        List<OrderResponse> listOrderResponse = new ArrayList<>();
        if(!listOrder.isEmpty()) {
            listOrder.forEach(order -> {
                OrderResponse orderResponse = this.orderMapper.toOrderResponse(order);
                listOrderResponse.add(orderResponse);
            });
        }
        return listOrderResponse;
    }

    @Override
    public OrderResponse getOrderById(int orderId) {
        Optional<Order> checkExistOrder = this.orderRepository.findById(orderId);
        if(!checkExistOrder.isPresent()) {
            OrderResponse orderResponse = this.orderMapper.toOrderResponse(checkExistOrder.get());
            return orderResponse;
        }
        return null;
    }

    @Override
    public OrderResponse createOrder(CreateOrderDTO createOrderDTO) {
        Order order = this.orderMapper.createOrder(createOrderDTO);
        if(order != null) {
            Order createOrder = this.orderRepository.save(order);
            OrderResponse orderResponse = this.orderMapper.toOrderResponse(createOrder);
            return orderResponse;
        }
        return null;
    }

    @Override
    public Order updateOrder(UpdateOrderDTO updateOrderDTO) throws Exception {
        Optional<Order> checkExistOrder = this.orderRepository.findById(updateOrderDTO.getOrderId());
        if(!checkExistOrder.isPresent()) {
            throw new Exception("Order does not exist");
        }
        Order updateOrder = this.orderMapper.updateOrder(updateOrderDTO, checkExistOrder.get());
        this.orderRepository.save(updateOrder);
        return updateOrder;
    }

    @Override
    public void deleteOrder(int orderId) throws Exception {
        Optional<Order> checkExistOrder = this.orderRepository.findById(orderId);
        if(!checkExistOrder.isPresent()) {
            throw new Exception("Order does not exist");
        }
        this.orderRepository.delete(checkExistOrder.get());
    }
}
