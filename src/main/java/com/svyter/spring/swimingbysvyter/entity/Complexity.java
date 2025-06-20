package com.svyter.spring.swimingbysvyter.entity;

import javax.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Complexity")
public class Complexity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "complexity")
    private List<Trainings> trainings;

    public Complexity() {}
    public Complexity(String name) {
        this.name = name;
    }

    public Complexity(String name, List<Trainings> trainings) {
        this.name = name;
        this.trainings = trainings;
    }
}
