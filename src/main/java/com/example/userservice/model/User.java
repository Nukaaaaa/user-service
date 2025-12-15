package com.example.userservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    private UUID id;

    private String username;
    private Integer age;
    private String gender;

    @Column(length = 500)
    private String bio;

    private Integer score = 1000;

    private Instant createdAt = Instant.now();
    private String location;
}
