package com.example.demo.service;

import com.example.demo.error.NotFountException;
import com.example.demo.model.Interview;
import com.example.demo.model.InterviewStep;
import com.example.demo.model.type.InterviewStepType;
import com.example.demo.repository.InterviewRepository;
import com.example.demo.repository.InterviewStepRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterviewService {
    private final InterviewRepository interviewRepository;
    private final InterviewStepRepository interviewStepRepository;

    public InterviewService(InterviewRepository interviewRepository, InterviewStepRepository interviewStepRepository) {
        this.interviewRepository = interviewRepository;
        this.interviewStepRepository = interviewStepRepository;
    }

    public Interview create(Interview interview) {
        return interviewRepository.save(interview);
    }
    public InterviewStep createStep(InterviewStep interviewStep) {
        InterviewStepType interviewStepType = null;
        for (InterviewStepType c : InterviewStepType.values()) {
            if (c.name().equals(interviewStep.getType())) {
                interviewStepType = c;
                break;
            }
        }
        if(interviewStepType != null) {
            interviewStepRepository.save(interviewStep);
        }
        return null;
    }
    public Interview update(long id, Interview reqInterview) {
        Interview interview = interviewRepository.findById(id)
                .orElseThrow(() -> new NotFountException("Interview " + id + " does not exist"));

        interview.setAdvertisement(reqInterview.getAdvertisement());
        interview.setSteps(reqInterview.getSteps());
        interview.setEmployee(reqInterview.getEmployee());
        return interviewRepository.save(interview);
    }
    public void delete(long id) {
        Interview interview = interviewRepository.findById(id)
                .orElseThrow(() -> new NotFountException("Interview " + id + " does not exist"));
        interviewRepository.delete(interview);
    }

    public List<Interview> getAll() {
        return interviewRepository.findAll();
    }
    public Interview getById(Long id) {
        Optional<Interview> result = interviewRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        } else {
            throw new NotFountException("Interview " + id + " does not exist");
        }
    }

}
