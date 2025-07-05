package com.tan.java.hairhub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tan.java.hairhub.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT * From Order LIMIT :pageSize OFFSET :pageSize", nativeQuery = true)
    List<Order> getAllOrder(int pageIndex, int pageSize);
}
