package com.example.demo.service;

import com.example.demo.controller.dto.ResultDto;
import com.example.demo.model.Advertisement;
import com.example.demo.model.AdvertisementUser;
import com.example.demo.repository.AdvertisementUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class UploadCvService {
    private final Path root = Paths.get("uploads");

    final String[] extensions = {"pdf", "xls", "doc", "docx"};
    final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
    final java.util.Random rand = new java.util.Random();
    final Set<String> identifiers = new HashSet<String>();

    private final AdvertisementService advertisementService;

    public UploadCvService(AdvertisementService advertisementService) {
        try {
            this.advertisementService = advertisementService;
            if(!Files.exists(root)) {
                Files.createDirectory(root);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    public ResultDto save(MultipartFile file, Long employee_id, Long advert_id) {
        try {
            Optional<AdvertisementUser> optionalAdvertisementUser = advertisementService.isUserAdvertisement(employee_id, advert_id);
            if(optionalAdvertisementUser.get() != null) {
                String extension = FilenameUtils.getExtension(file.getOriginalFilename());
                boolean found = Arrays.stream(extensions).anyMatch(extension::equals);
                if(found){
                    String fileName = this.randomIdentifier()+"."+extension;
                    Files.copy(file.getInputStream(), this.root.resolve(fileName));
                    AdvertisementUser safeAdvertisementUser = optionalAdvertisementUser.get();
                    safeAdvertisementUser.setCvPath(this.root.toAbsolutePath() + "\\" + fileName);
                    advertisementService.updateUser(safeAdvertisementUser);
                    return ResultDto.builder()
                            .status(true)
                            .detail(this.root.toAbsolutePath() + "\\" + fileName).build();
                } else {
                    return ResultDto.builder()
                            .status(false)
                            .detail("File format not supported!").build();
                }
            } else {
                return ResultDto.builder()
                        .status(false)
                        .detail("Adversitement is not found").build();
            }

        } catch (Exception e) {
            return ResultDto.builder()
                    .status(false)
                    .detail("Could not store the file. Error: " + e.getMessage()).build();
        }
    }

    public String randomIdentifier() {
        StringBuilder builder = new StringBuilder();
        while (builder.toString().length() == 0) {
            int length = rand.nextInt(10) + 10;
            for (int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            if (identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }
}
