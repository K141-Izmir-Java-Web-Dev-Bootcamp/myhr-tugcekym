package com.example.demo.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode

public class HumanResource {
    @Id
    @GeneratedValue
    private long id;

    private String username;

    private String password;

    private String email;

    private String fullName;
}
