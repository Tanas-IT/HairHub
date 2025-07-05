package com.tan.java.hairhub.dto.response;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

import com.tan.java.hairhub.entities.OrderDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private int orderId;

    private String orderName;

    private double totalPrice;

    private String note;

    private LocalDate orderDate;

    private int userId;

    private List<OrderDetail> orderDetails;

    private int paymentId;
}
