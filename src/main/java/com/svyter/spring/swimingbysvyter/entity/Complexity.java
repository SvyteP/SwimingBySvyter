package com.svyter.spring.swimingbysvyter.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Complexity")
public class Complexity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
}
