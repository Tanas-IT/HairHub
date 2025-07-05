package com.tan.java.hairhub.mapper;

import com.tan.java.hairhub.dto.request.CreatePaymentDTO;
import com.tan.java.hairhub.dto.request.UpdatePaymentDTO;
import com.tan.java.hairhub.dto.response.PaymentResponse;
import com.tan.java.hairhub.entities.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PaymentMapper {
    PaymentResponse toPaymentResponse(Payment payment);

    Payment createPayment(CreatePaymentDTO createPaymentDTO);

    Payment updatePayment(UpdatePaymentDTO updatePaymentDTO, @MappingTarget Payment payment);

}
