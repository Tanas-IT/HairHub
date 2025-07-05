package com.tan.java.hairhub.dto.request;

import java.time.LocalDate;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDTO {

    private String orderName;

    private double totalPrice;

    private String note;

    private LocalDate orderDate;

    private int userId;

    private int paymentId;
}
