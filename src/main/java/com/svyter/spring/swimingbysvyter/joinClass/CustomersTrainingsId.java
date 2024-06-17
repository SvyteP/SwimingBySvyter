package com.svyter.spring.swimingbysvyter.joinClass;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
@Data
@Embeddable
public class CustomersTrainingsId implements Serializable {
    private Long customersId;
    private Long trainingsId;

}
