package com.svyter.spring.swimingbysvyter.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "m50")
    private String m50;
    @Column(name = "m100")
    private String m100;
    @Column(name = "m200")
    private String m200;
    @Column(name = "mainExer")
    private String mainExer;
}
