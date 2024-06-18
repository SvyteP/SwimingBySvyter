package com.svyter.spring.swimingbysvyter.joinClass;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Data
@Embeddable
public class CustomersTrainingsId implements Serializable {

    @Column(name = "customersId")
    private Long customersId;
    @Column(name = "trainingsId")
    private Long trainingsId;

    public CustomersTrainingsId() {
    }

    public CustomersTrainingsId(Long customersId, Long trainingsId) {
        this.customersId = customersId;
        this.trainingsId = trainingsId;
    }
}
