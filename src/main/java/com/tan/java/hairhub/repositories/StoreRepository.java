package com.tan.java.hairhub.repositories;

import com.tan.java.hairhub.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Integer> {

    @Query(value = "Select * From Store LIMIT :pageSize OFFSET :pageIndex", nativeQuery = true)
    List<Store> getAllStore(int pageIndex, int pageSize);
}
