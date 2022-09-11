package com.example.demo.repository;

import com.example.demo.model.Interview;
import com.example.demo.model.InterviewStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InterviewStepRepository extends JpaRepository<InterviewStep, Long> {

}
