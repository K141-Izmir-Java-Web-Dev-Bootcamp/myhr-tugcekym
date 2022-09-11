package com.example.demo.repository;

import com.example.demo.model.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InterviewRepository extends JpaRepository<Interview, Long> {

}
