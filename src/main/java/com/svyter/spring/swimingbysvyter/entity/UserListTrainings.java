package com.svyter.spring.swimingbysvyter.entity;

import javax.persistence.*;

import com.svyter.spring.swimingbysvyter.joinClass.CustomersTrainingsId;
import lombok.Data;

@Data
@Entity
@Table(name = "UserListTrainings")
public class UserListTrainings {
    @EmbeddedId
    private CustomersTrainingsId id = new CustomersTrainingsId();

    @ManyToOne
    @MapsId("trainingsId")
    @JoinColumn(name = "trainings")
    private Trainings trainings;
    @ManyToOne
    @MapsId("customersId")
    @JoinColumn(name = "Customers")
    private Customers customers;
    @OneToOne(mappedBy = "userListTrainings",cascade = CascadeType.ALL)
    private Result result;

    @Column(name = "likeTrain")
    private boolean likeTrain;
    @Column(name = "complited")
    private boolean complited;

    public UserListTrainings() {
    }

    public UserListTrainings(CustomersTrainingsId id, Trainings trainings, Customers customers, /*Result result,*/ boolean likeTrain, boolean complited) {
        this.id = id;
        this.trainings = trainings;
        this.customers = customers;
        /*this.result = result;*/
        this.likeTrain = likeTrain;
        this.complited = complited;
    }
}
