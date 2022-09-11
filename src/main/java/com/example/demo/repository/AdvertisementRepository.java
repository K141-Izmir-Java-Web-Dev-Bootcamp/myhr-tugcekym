package com.example.demo.repository;

import com.example.demo.model.Advertisement;
import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
}
