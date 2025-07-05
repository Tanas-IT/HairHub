package com.tan.java.hairhub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tan.java.hairhub.entities.Combo;

public interface ComboRespository extends JpaRepository<Combo, Integer> {

    @Query(value = "SELECT * From combo LIMIT :pageSize OFFSET :pageIndex", nativeQuery = true)
    List<Combo> getAllCombo(int pageIndex, int pageSize);

    @Query(value = "SELECT * From combo where combo.service_id = :serviceId", nativeQuery = true)
    List<Combo> getAllComboByServiceId(int serviceId);
}
