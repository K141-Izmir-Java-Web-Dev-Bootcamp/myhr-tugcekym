package com.example.demo.controller;

import com.example.demo.controller.dto.HumanResourceDto;
import com.example.demo.model.HumanResource;
import com.example.demo.service.HumanResourceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/")
public class HumanResourceController {
    @Autowired
    private ModelMapper modelMapper;
    private final HumanResourceService humanResourceService;

    public HumanResourceController(HumanResourceService humanResourceService) {
        this.humanResourceService = humanResourceService;
    }
    @PostMapping("human-resource")
    public ResponseEntity<HumanResourceDto> create(@RequestBody HumanResourceDto dto) {
        HumanResource humanResourceReq = modelMapper.map(dto, HumanResource.class);
        HumanResource humanResource = humanResourceService.create(humanResourceReq);
        HumanResourceDto humanResourceResponse = modelMapper.map(humanResource, HumanResourceDto.class);
        return new ResponseEntity<HumanResourceDto>(humanResourceResponse, HttpStatus.CREATED);
    }
    @GetMapping("/human-resource/{id}")
    public ResponseEntity<HumanResourceDto> get(@PathVariable Long id) {
        HumanResource humanResource = humanResourceService.getById(id);
        HumanResourceDto humanResourceResponse = modelMapper.map(humanResource, HumanResourceDto.class);
        return ResponseEntity.ok().body(humanResourceResponse);
    }
    @GetMapping("human-resources")
    public List<HumanResourceDto> gets() {
        return humanResourceService.getAll().stream().map(post -> modelMapper.map(post, HumanResourceDto.class))
                .collect(Collectors.toList());
    }
    @PutMapping("/human-resource/{id}")
    public ResponseEntity<HumanResourceDto> update(@PathVariable long id, @RequestBody HumanResourceDto humanResourceDto) {
        HumanResource humanResourceRequest = modelMapper.map(humanResourceDto, HumanResource.class);
        HumanResource humanResource = humanResourceService.update(id, humanResourceRequest);
        HumanResourceDto humanResourceResponse = modelMapper.map(humanResource, HumanResourceDto.class);
        return ResponseEntity.ok().body(humanResourceResponse);
    }
    @DeleteMapping("/human-resource/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") Long id) {
        humanResourceService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
