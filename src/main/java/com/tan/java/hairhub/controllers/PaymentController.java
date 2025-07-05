package com.tan.java.hairhub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tan.java.hairhub.dto.request.CreatePaymentDTO;
import com.tan.java.hairhub.dto.request.UpdatePaymentDTO;
import com.tan.java.hairhub.dto.response.ApiResponse;
import com.tan.java.hairhub.dto.response.PaymentResponse;
import com.tan.java.hairhub.entities.Payment;
import com.tan.java.hairhub.services.interfaces.PaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<PaymentResponse>>> getAllPayment(
            @RequestParam int pageIndex, @RequestParam int pageSize) {
        List<PaymentResponse> listPaymentResponse = this.paymentService.getAllPayment(pageIndex, pageSize);
        ApiResponse<List<PaymentResponse>> apiResponse = new ApiResponse<>();
        if (!listPaymentResponse.isEmpty()) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Get all payment success");
            apiResponse.setData(listPaymentResponse);

            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiResponse.setMessage("Do not have any Payment");
        apiResponse.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentResponse>> getPaymentById(@PathVariable int id) {
        PaymentResponse paymentResponse = this.paymentService.getPaymentById(id);
        ApiResponse<PaymentResponse> apiResponse = new ApiResponse<>();
        if (paymentResponse != null) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Get payment by id success");
            apiResponse.setData(paymentResponse);

            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }

        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiResponse.setMessage("Do not have any payment with that id");
        apiResponse.setData(null);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<PaymentResponse>> createPayment(@RequestBody CreatePaymentDTO createPaymentDTO) {
        PaymentResponse payment = this.paymentService.createPayment(createPaymentDTO);
        ApiResponse<PaymentResponse> apiResponse = new ApiResponse<>();
        if (payment != null) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Create payment success");
            apiResponse.setData(payment);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage("Create payment failed");
        apiResponse.setData(null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<com.tan.java.hairhub.entities.Payment>> updatePayment(
            @RequestBody UpdatePaymentDTO updatePaymentDTO) throws Exception {
        var paymentUpdate = this.paymentService.updatePayment(updatePaymentDTO);
        ApiResponse<Payment> apiResponse = new ApiResponse<>();
        if (paymentUpdate != null) {
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Update payment success");
            apiResponse.setData(paymentUpdate);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }

        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage("Update payment failed");
        apiResponse.setData(null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePayment(@PathVariable int id) throws Exception {
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        try {
            this.paymentService.deletePayment(id);
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("Delete payment success");
            apiResponse.setData(null);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (Exception ex) {
            apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Delete payment failed");
            apiResponse.setData(null);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
    }
}
