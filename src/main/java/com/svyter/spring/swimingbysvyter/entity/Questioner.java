package com.svyter.spring.swimingbysvyter.entity;

import javax.persistence.*;

import com.svyter.spring.swimingbysvyter.model.CustomersGetModel;
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

    public Questioner() {
    }

    public Questioner(int langthPool, String gender, int age, String levelTrain, int timeTrain, int countWeek, int countTrainOneWeek, Customers customers) {
        this.langthPool = langthPool;
        this.gender = gender;
        this.age = age;
        this.levelTrain = levelTrain;
        this.timeTrain = timeTrain;
        this.countWeek = countWeek;
        this.countTrainOneWeek = countTrainOneWeek;
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "Questioner{" +
                "id=" + id +
                ", langthPool=" + langthPool +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", levelTrain='" + levelTrain + '\'' +
                ", timeTrain=" + timeTrain +
                ", countWeek=" + countWeek +
                ", countTrainOneWeek=" + countTrainOneWeek +
                ", customers=" + CustomersGetModel.convertCustomersToModel(customers) +
                '}';
    }
}
