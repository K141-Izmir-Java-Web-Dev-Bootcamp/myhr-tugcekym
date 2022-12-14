package com.example.demo.service;

import com.example.demo.error.NotFountException;
import com.example.demo.model.Advertisement;
import com.example.demo.model.AdvertisementUser;
import com.example.demo.repository.AdvertisementRepository;
import com.example.demo.repository.AdvertisementUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementUserRepository advertisementUserRepository;

    public AdvertisementService(AdvertisementRepository advertisementRepository, AdvertisementUserRepository advertisementUserRepository) {
        this.advertisementRepository = advertisementRepository;
        this.advertisementUserRepository = advertisementUserRepository;
    }

    public Advertisement create(Advertisement advertisement) {
        return advertisementRepository.save(advertisement);
    }

    public AdvertisementUser addUsers(AdvertisementUser advertisement) {
        Optional<AdvertisementUser> optionalAdvertisement = this.isUserAdvertisement(advertisement.getEmployee().getId(), advertisement.getAdvert().getId());
        if(optionalAdvertisement.get() != null) {
            return advertisementUserRepository.save(advertisement);
        }
        return advertisement;
    }

    public AdvertisementUser updateUser(AdvertisementUser advertisementUser) {
        AdvertisementUser advertisement = advertisementUserRepository.findById(advertisementUser.getId())
                .orElseThrow(() -> new NotFountException("Advertisement " + advertisementUser.getId() + " does not exist"));
        return advertisementUserRepository.save(advertisement);
    }

    public Advertisement update(long id, Advertisement postAdvertisement) {
        Advertisement advertisement = advertisementRepository.findById(id)
                .orElseThrow(() -> new NotFountException("Advertisement " + id + " does not exist"));

        advertisement.setSkills(postAdvertisement.getSkills());
        advertisement.setTitle(postAdvertisement.getTitle());
        advertisement.setHumanResource(postAdvertisement.getHumanResource());
        advertisement.setDetail(postAdvertisement.getDetail());
        advertisement.setCompanyName(postAdvertisement.getCompanyName());
        advertisement.setStartDate(postAdvertisement.getStartDate());
        advertisement.setEndDate(postAdvertisement.getEndDate());
        return advertisementRepository.save(advertisement);
    }
    public void delete(long id) {
        Advertisement advertisement = advertisementRepository.findById(id)
                .orElseThrow(() -> new NotFountException("Advertisement " + id + " does not exist"));
        advertisementRepository.delete(advertisement);
    }

    public List<Advertisement> getAll() {
        return advertisementRepository.findAll();
    }

    public Advertisement getById(Long id) {
        Optional<Advertisement> result = advertisementRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        } else {
            throw new NotFountException("Advertisement " + id + " does not exist");
        }
    }
    public List<AdvertisementUser> getUserById(Long id) {
        return advertisementUserRepository.findByAdvertId(id);
    }

    public Optional<AdvertisementUser> isUserAdvertisement(Long employee_id, Long advert_id){
       return advertisementUserRepository.findByAdvertId(advert_id)
                .stream().filter(p -> p.getEmployee().getId() == employee_id).collect(Collectors.toList()).stream().findFirst();
    }
}
