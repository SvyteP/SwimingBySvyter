package com.svyter.spring.swimingbysvyter.entity;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "user_trainings")
public class UserTrainings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trainings_id")
    private Trainings trainings;
    @ManyToOne
    @JoinColumn(name = "customers_id")
    private Customers customers;

 /*   @OneToOne(mappedBy = "userTrainings",cascade = CascadeType.ALL)
    private Result result;*/

    @Column(name = "like_train")
    private boolean likeTrain;
    @Column(name = "completed")
    private boolean completed;

    public UserTrainings() {
    }

    public UserTrainings(Trainings trainings, Customers customers, /*Result result,*/ boolean likeTrain, boolean completed) {
        this.trainings = trainings;
        this.customers = customers;
/*        this.result = result;*/
        this.likeTrain = likeTrain;
        this.completed = completed;
    }
}
