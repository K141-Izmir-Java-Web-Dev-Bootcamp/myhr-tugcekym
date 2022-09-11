package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode

public class Employee {
    @Id
    @GeneratedValue
    private long id;

    private String username;

    private String password;

    private String email;

    private String fullName;

    private String address;

    private int birthYear;

    @OneToMany
    @JoinTable(
            name="AdvertisemenetUsers",
            joinColumns = @JoinColumn( name="advert_id"),
            inverseJoinColumns = @JoinColumn( name="employee_id")
    )
    private List<Advertisement> advertisements;
}
