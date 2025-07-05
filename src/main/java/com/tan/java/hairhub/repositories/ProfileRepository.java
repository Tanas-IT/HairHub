package com.tan.java.hairhub.repositories;


import com.tan.java.hairhub.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    @Query(value = "SELECT * FROM Profile LIMIT :pageSize OFFSET :pageIndex", nativeQuery = true)
    List<Profile> getAllProfile(int pageIndex, int pageSize);
}
