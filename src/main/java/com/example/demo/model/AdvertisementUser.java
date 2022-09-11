package com.example.demo.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode

public class AdvertisementUser {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name = "advert_id", referencedColumnName = "id")
    private Advertisement advert;

    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    private String cvPath;
}
