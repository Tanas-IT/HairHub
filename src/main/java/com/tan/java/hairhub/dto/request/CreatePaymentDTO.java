package com.tan.java.hairhub.dto.request;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentDTO {

    private String paymentMethod;

    private String status;

    private int orderId;
}
