package com.example.demo.repository;

import com.example.demo.model.Employee;
import com.example.demo.model.HumanResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HumanResourceRepository extends JpaRepository<HumanResource, Long> {

}
