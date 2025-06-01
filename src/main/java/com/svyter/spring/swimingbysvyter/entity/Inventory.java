package com.svyter.spring.swimingbysvyter.entity;

import jakarta.persistence.*;

import com.svyter.spring.swimingbysvyter.dto.CustomersGetDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

    @ManyToMany(mappedBy = "inventories")
    private List<Trainings> trainingsList;

    @ManyToMany(mappedBy = "inventories")
    private List<Customers> customers;

    public Inventory() {
    }

    public Inventory(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", trainingsList=" + trainingsList +
                ", customers=" + customers.stream().map(CustomersGetDTO::convertCustomersToModel).toList() +
                '}';
    }
}
