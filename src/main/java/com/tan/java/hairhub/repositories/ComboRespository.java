package com.tan.java.hairhub.repositories;

import com.tan.java.hairhub.entities.Combo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComboRespository extends JpaRepository<Combo, Integer> {

    @Query(value = "SELECT * From combo LIMIT :pageSize OFFSET :pageIndex", nativeQuery = true)
    List<Combo> getAllCombo(int pageIndex, int pageSize);
}
