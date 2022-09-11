package com.example.demo.repository;

import com.example.demo.model.Advertisement;
import com.example.demo.model.AdvertisementUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertisementUserRepository extends JpaRepository<AdvertisementUser, Long> {
    List<AdvertisementUser> findByAdvertId(Long id);
    AdvertisementUser getById(Long id);
}
