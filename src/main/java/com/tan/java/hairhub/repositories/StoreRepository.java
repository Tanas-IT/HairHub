package com.tan.java.hairhub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tan.java.hairhub.entities.Store;

public interface StoreRepository extends JpaRepository<Store, Integer> {

    @Query(value = "Select * From Store LIMIT :pageSize OFFSET :pageIndex", nativeQuery = true)
    List<Store> getAllStore(int pageIndex, int pageSize);
}
