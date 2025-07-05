package com.tan.java.hairhub.services.implementation;

import com.tan.java.hairhub.dto.request.CreatePaymentDTO;
import com.tan.java.hairhub.dto.request.UpdatePaymentDTO;
import com.tan.java.hairhub.dto.response.PaymentResponse;
import com.tan.java.hairhub.entities.Order;
import com.tan.java.hairhub.entities.Payment;
import com.tan.java.hairhub.mapper.PaymentMapper;
import com.tan.java.hairhub.repositories.OrderRepository;
import com.tan.java.hairhub.repositories.PaymentRepository;
import com.tan.java.hairhub.services.interfaces.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;
    private OrderRepository orderRepository;
    private PaymentMapper paymentMapper;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, OrderRepository orderRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<PaymentResponse> getAllPayment(int pageIndex, int pageSize) {
        List<Payment> listPayment = this.paymentRepository.getAllPayment((pageIndex - 1) * pageSize, pageSize);
        List<PaymentResponse> listPaymentResponse = new ArrayList<>();
        if(!listPayment.isEmpty()) {
            listPayment.forEach(payment -> {
                PaymentResponse paymentResponse = this.paymentMapper.toPaymentResponse(payment);
                listPaymentResponse.add(paymentResponse);
            });
        }
        return listPaymentResponse;
    }

    @Override
    public PaymentResponse getPaymentById(int paymentId) {
        Optional<Payment> payment = this.paymentRepository.findById(paymentId);
        if(payment.isPresent()) {
            PaymentResponse paymentResponse = this.paymentMapper.toPaymentResponse(payment.get());
            return paymentResponse;
        }
        return null;
    }

    @Override
    public PaymentResponse createPayment(CreatePaymentDTO createPaymentDTO) {
        Payment payment = this.paymentMapper.createPayment(createPaymentDTO);
        Optional<Order> checkExistOrder = this.orderRepository.findById(createPaymentDTO.getOrderId());
        if(payment != null && checkExistOrder.isPresent()) {
            payment.setOrder(checkExistOrder.get());
            Payment result = this.paymentRepository.save(payment);
            PaymentResponse paymentResponse = this.paymentMapper.toPaymentResponse(result);
            return paymentResponse;
        }
        return null;
    }

    @Override
    public Payment updatePayment(UpdatePaymentDTO updatePaymentDTO) throws Exception {
        Optional<Payment> payment = this.paymentRepository.findById(updatePaymentDTO.getPaymentId());
        Optional<Order> checkExistOrder = this.orderRepository.findById(updatePaymentDTO.getOrderId());
        if(!payment.isPresent() && checkExistOrder.isPresent()) {
            throw new Exception("Payment does not exist");
        }
        payment.get().setOrder(checkExistOrder.get());
        Payment result = this.paymentMapper.updatePayment(updatePaymentDTO, payment.get());
        this.paymentRepository.save(result);
        return result;
    }

    @Override
    public void deletePayment(int paymentId) throws Exception {
        Optional<Payment> payment = this.paymentRepository.findById(paymentId);
        if(!payment.isPresent()) {
            throw new Exception("Payment does not exist");
        }
        this.paymentRepository.delete(payment.get());
    }
}
