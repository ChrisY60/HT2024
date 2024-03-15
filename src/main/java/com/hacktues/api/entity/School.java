package com.hacktues.api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "schools")
@Data
@Cacheable
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "access_code", unique = true, nullable = false)
    private UUID accessCode = UUID.randomUUID();
}
