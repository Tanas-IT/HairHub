package com.tan.java.hairhub.repositories;

import com.tan.java.hairhub.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    @Query(value = "SELECT * From Payment LIMIT :pageSize OFFSET :pageIndex", nativeQuery = true)
    List<Payment> getAllPayment(int pageIndex, int pageSize);
}
