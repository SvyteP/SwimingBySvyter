package com.svyter.spring.swimingbysvyter.entity;

import javax.persistence.*;
import lombok.Data;

import java.util.List;

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
    @ManyToMany
    @JoinTable(
            name = "trainings_inventory_list",
            joinColumns = @JoinColumn(name = "training_id"),
            inverseJoinColumns = @JoinColumn(name = "inventory_id")
    )
    private List<Inventory> inventoryList;
    @OneToMany
    @JoinColumn(name = "UserListTrainings")
    private List<UserListTrainings> UserListTrainings;
    @ManyToOne
    private Complexity complexity;

    public Trainings() {

    }


    public Trainings(String name, String warmUp, String mainTraining, String hitch, List<Inventory> inventoryList, Complexity complexity) {
        this.name = name;
        this.warmUp = warmUp;
        this.mainTraining = mainTraining;
        this.hitch = hitch;
        this.inventoryList = inventoryList;
        this.complexity = complexity;
    }
}
