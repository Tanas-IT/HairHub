package com.tan.java.hairhub.services.interfaces;

import com.tan.java.hairhub.dto.request.CreatePaymentDTO;
import com.tan.java.hairhub.dto.request.UpdatePaymentDTO;
import com.tan.java.hairhub.dto.response.PaymentResponse;
import com.tan.java.hairhub.entities.Payment;

import java.util.List;

public interface PaymentService {
    List<PaymentResponse> getAllPayment(int pageIndex, int pageSize);

    PaymentResponse getPaymentById(int paymentId);
    PaymentResponse createPayment(CreatePaymentDTO createPaymentDTO);

    Payment updatePayment(UpdatePaymentDTO updatePaymentDTO) throws Exception;
    void deletePayment(int paymentId) throws Exception;
}
