package com.svyter.spring.swimingbysvyter.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "UserListTrainings")
public class UserListTrainings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "trainings")
    private Trainings trainings;

    @ManyToOne
    @JoinColumn(name = "Customers")
    private Customers customers;
    @ManyToOne
    @JoinColumn(name = "Resualt")
    private Result result;

    @Column(name = "likeTrain")
    private boolean likeTrain;
    @Column(name = "complited")
    private boolean compl;

}
