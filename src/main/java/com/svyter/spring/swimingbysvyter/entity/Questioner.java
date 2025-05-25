package com.svyter.spring.swimingbysvyter.entity;

import com.svyter.spring.swimingbysvyter.dto.ComplexityDTO;
import jakarta.persistence.*;

import com.svyter.spring.swimingbysvyter.dto.CustomersGetDTO;
import lombok.Data;

@Data
@Entity
@Table(name = "Questioner")
public class Questioner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "length_pool")
    private int lengthPool;
    @Column(name = "gender")
    private String gender;
    @Column(name = "age")
    private int age;

    @Column(name = "time_train")
    private int timeTrain;
    @Column(name = "count_week")
    private int countWeek;
    @Column(name = "count_train_one_week")
    private int countTrainOneWeek;

    @ManyToOne
    @JoinColumn(name = "complexity_id")
    private Complexity complexity;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "questioner")
    private Customers customers;

    public Questioner() {
    }

    public Questioner(int langthPool, String gender, int age, int timeTrain, int countWeek, int countTrainOneWeek, Customers customers) {
        this.lengthPool = langthPool;
        this.gender = gender;
        this.age = age;
        this.timeTrain = timeTrain;
        this.countWeek = countWeek;
        this.countTrainOneWeek = countTrainOneWeek;
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "Questioner{" +
                "id=" + id +
                ", langthPool=" + lengthPool +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", complexity='" + ComplexityDTO.convertToModel(complexity) + '\'' +
                ", timeTrain=" + timeTrain +
                ", countWeek=" + countWeek +
                ", countTrainOneWeek=" + countTrainOneWeek +
                ", customers=" + CustomersGetDTO.convertCustomersToModel(customers) +
                '}';
    }
}
