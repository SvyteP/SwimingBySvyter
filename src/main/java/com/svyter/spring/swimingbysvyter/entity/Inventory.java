package com.svyter.spring.swimingbysvyter.entity;

import javax.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "inventoryList")
    private List<Trainings> trainingsList;
    @ManyToMany(mappedBy = "inventories")
    private List<Customers> customers;

    public Inventory() {
    }

    public Inventory(String name) {
        this.name = name;
    }
}
