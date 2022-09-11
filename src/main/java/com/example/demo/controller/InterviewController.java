package com.example.demo.controller;

import com.example.demo.controller.dto.InterviewDto;
import com.example.demo.controller.dto.InterviewStepDto;
import com.example.demo.model.Interview;
import com.example.demo.model.InterviewStep;
import com.example.demo.service.InterviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/")
public class InterviewController {
    @Autowired
    private ModelMapper modelMapper;
    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }
    @PostMapping("interview")
    public ResponseEntity<InterviewDto> create(@RequestBody InterviewDto dto) {
        Interview interviewReq = modelMapper.map(dto, Interview.class);
        Interview interview = interviewService.create(interviewReq);
        InterviewDto interviewResponse = modelMapper.map(interview, InterviewDto.class);
        return new ResponseEntity<InterviewDto>(interviewResponse, HttpStatus.CREATED);
    }
    @GetMapping("/interview/{id}")
    public ResponseEntity<InterviewDto> get(@PathVariable Long id) {
        Interview interview = interviewService.getById(id);
        InterviewDto interviewResponse = modelMapper.map(interview, InterviewDto.class);
        return ResponseEntity.ok().body(interviewResponse);
    }
    @GetMapping("interviews")
    public List<InterviewDto> gets() {
        return interviewService.getAll().stream().map(post -> modelMapper.map(post, InterviewDto.class))
                .collect(Collectors.toList());
    }
    @PutMapping("interview/{id}")
    public ResponseEntity<InterviewDto> update(@PathVariable long id, @RequestBody InterviewDto interviewDto) {
        Interview interviewRequest = modelMapper.map(interviewDto, Interview.class);
        Interview interview = interviewService.update(id, interviewRequest);
        InterviewDto interviewResponse = modelMapper.map(interview, InterviewDto.class);
        return ResponseEntity.ok().body(interviewResponse);
    }
    @DeleteMapping("interview/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") Long id) {
        interviewService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
    @PostMapping("interview-step")
    public ResponseEntity<InterviewStepDto> createInterviewStep(@RequestBody InterviewStepDto interviewStepDto) {
        InterviewStep interviewStepReq = modelMapper.map(interviewStepDto, InterviewStep.class);
        InterviewStep interviewStep = interviewService.createStep(interviewStepReq);
        InterviewStepDto interviewStepResponse = modelMapper.map(interviewStep, InterviewStepDto.class);
        return new ResponseEntity<InterviewStepDto>(interviewStepResponse, HttpStatus.CREATED);
    }
}
