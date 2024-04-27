package com.svyter.spring.swimingbysvyter.entity;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Questioner")
public class Questioner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "langthPool")
    private int langthPool;
    @Column(name = "gender")
    private String gender;
    @Column(name = "age")
    private int age;
    @Column(name = "levelTrain")
    private String levelTrain;
    @Column(name = "timeTrain")
    private int timeTrain;
    @Column(name = "countWeek")
    private int countWeek;
    @Column(name = "countTrainOneWeek")
    private int countTrainOneWeek;
    @OneToOne
    @JoinColumn(name = "customers")
    private Customers customers;

}
