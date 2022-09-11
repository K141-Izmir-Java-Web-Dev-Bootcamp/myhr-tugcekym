package com.example.demo.controller;

import com.example.demo.service.UploadCvService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1/")
public class UploadCvController {

    private final UploadCvService uploadCvService;

    public UploadCvController (UploadCvService uploadCvService) {
        this.uploadCvService = uploadCvService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("advert_id") Long advert_id, @RequestParam("employee_id") Long employee_id) {
        try {
            return ResponseEntity.ok(uploadCvService.save(file, employee_id, advert_id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
