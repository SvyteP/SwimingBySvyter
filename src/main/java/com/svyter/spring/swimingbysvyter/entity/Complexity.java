package com.svyter.spring.swimingbysvyter.entity;

import javax.persistence.*;
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
    @ManyToOne
    private Trainings trainings;

    public Complexity() {}
    public Complexity(String name) {
        this.name = name;
    }

    public Complexity(String name, Trainings trainings) {
        this.name = name;
        this.trainings = trainings;
    }
}
