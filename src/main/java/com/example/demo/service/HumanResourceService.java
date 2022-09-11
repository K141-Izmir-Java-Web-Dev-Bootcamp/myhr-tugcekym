package com.example.demo.service;

import com.example.demo.error.NotFountException;
import com.example.demo.model.HumanResource;
import com.example.demo.repository.HumanResourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HumanResourceService {
    private final HumanResourceRepository humanResourceRepository;

    public HumanResourceService(HumanResourceRepository humanResourceRepository) {
        this.humanResourceRepository = humanResourceRepository;
    }

    public HumanResource create(HumanResource humanResource) {
        return humanResourceRepository.save(humanResource);
    }
    public HumanResource update(long id, HumanResource reqHumanResource) {
        HumanResource humanResource = humanResourceRepository.findById(id)
                .orElseThrow(() -> new NotFountException("HumanResource " + id + " does not exist"));

        humanResource.setEmail(reqHumanResource.getEmail());
        humanResource.setUsername(reqHumanResource.getUsername());
        humanResource.setFullName(reqHumanResource.getFullName());
        humanResource.setPassword(reqHumanResource.getPassword());
        return humanResourceRepository.save(humanResource);
    }
    public void delete(long id) {
        HumanResource humanResource = humanResourceRepository.findById(id)
                .orElseThrow(() -> new NotFountException("HumanResource " + id + " does not exist"));
        humanResourceRepository.delete(humanResource);
    }

    public List<HumanResource> getAll() {
        return humanResourceRepository.findAll();
    }
    public HumanResource getById(Long id) {
        Optional<HumanResource> result = humanResourceRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        } else {
            throw new NotFountException("HumanResource " + id + " does not exist");
        }
    }
}
