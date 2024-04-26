package com.svyter.spring.swimingbysvyter.entity;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Trainings")
public class Trainings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "warmUp")
    private String warmUp;
    @Column(name = "mainTraining")
    private String mainTraining;
    @Column(name = "hitch")
    private String hitch;
    @ManyToOne
    @JoinColumn(name = "Complexity")
    private Complexity complexity;

}
