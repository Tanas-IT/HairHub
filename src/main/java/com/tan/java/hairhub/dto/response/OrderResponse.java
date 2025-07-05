package com.tan.java.hairhub.dto.response;

import com.tan.java.hairhub.entities.OrderDetail;
import com.tan.java.hairhub.entities.Payment;
import com.tan.java.hairhub.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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
