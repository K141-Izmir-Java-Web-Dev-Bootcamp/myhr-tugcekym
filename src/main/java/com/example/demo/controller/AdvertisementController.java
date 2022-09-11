package com.example.demo.controller;

import com.example.demo.controller.dto.AdvertisementDto;
import com.example.demo.controller.dto.AdvertisementUserDto;
import com.example.demo.model.Advertisement;
import com.example.demo.model.AdvertisementUser;
import com.example.demo.service.AdvertisementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/")
public class AdvertisementController {
    @Autowired
    private ModelMapper modelMapper;
    private final AdvertisementService advertisementService;

    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }
    @PostMapping("advertisement")
    public ResponseEntity<AdvertisementDto> create(@RequestBody AdvertisementDto dto) {
        Advertisement advertisementReq = modelMapper.map(dto, Advertisement.class);
        Advertisement advertisement = advertisementService.create(advertisementReq);
        AdvertisementDto advertisementResponse = modelMapper.map(advertisement, AdvertisementDto.class);
        return new ResponseEntity<AdvertisementDto>(advertisementResponse, HttpStatus.CREATED);
    }
    @GetMapping("/advertisement/{id}")
    public ResponseEntity<AdvertisementDto> get(@PathVariable Long id) {
        Advertisement advertisement = advertisementService.getById(id);
        AdvertisementDto advertisementResponse = modelMapper.map(advertisement, AdvertisementDto.class);
        return ResponseEntity.ok().body(advertisementResponse);
    }
    @GetMapping("advertisements")
    public List<AdvertisementDto> gets() {
        return advertisementService.getAll().stream().map(post -> modelMapper.map(post, AdvertisementDto.class))
                .collect(Collectors.toList());
    }
    @PutMapping("advertisement/{id}")
    public ResponseEntity<AdvertisementDto> update(@PathVariable long id, @RequestBody AdvertisementDto advertisementDto) {
        Advertisement advertisementReq = modelMapper.map(advertisementDto, Advertisement.class);
        Advertisement advertisement = advertisementService.update(id, advertisementReq);
        AdvertisementDto advertisementResponse = modelMapper.map(advertisement, AdvertisementDto.class);
        return ResponseEntity.ok().body(advertisementResponse);
    }
    @DeleteMapping("advertisement/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") Long id) {
        advertisementService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
    @PostMapping("advertisement-user")
    public ResponseEntity<AdvertisementUserDto> addUser(@RequestBody AdvertisementUserDto dto) {
        AdvertisementUser advertisementUserReq = modelMapper.map(dto, AdvertisementUser.class);
        AdvertisementUser advertisementUser = advertisementService.addUsers(advertisementUserReq);
        AdvertisementUserDto advertisementUserResponse = modelMapper.map(advertisementUser, AdvertisementUserDto.class);
        return new ResponseEntity<AdvertisementUserDto>(advertisementUserResponse, HttpStatus.CREATED);
    }
    @GetMapping("advertisement-users/{id}")
    public List<AdvertisementUserDto> getUsers(@PathVariable Long id) {
        return advertisementService.getUserById(id).stream().map(post -> modelMapper.map(post, AdvertisementUserDto.class))
                .collect(Collectors.toList());
    }
}
