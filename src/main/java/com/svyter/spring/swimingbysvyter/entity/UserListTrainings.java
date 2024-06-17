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
    @MapsId("trainingsId")
    @JoinColumn(name = "Customers")
    private Customers customers;
    @OneToOne(mappedBy = "userListTrainings",cascade = CascadeType.ALL)
    private Result result;

    @Column(name = "likeTrain")
    private boolean likeTrain;
    @Column(name = "complited")
    private boolean complited;

}
