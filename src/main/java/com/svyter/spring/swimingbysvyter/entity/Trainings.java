package com.svyter.spring.swimingbysvyter.entity;

import jakarta.persistence.*;

import com.svyter.spring.swimingbysvyter.dto.ComplexityDTO;
import com.svyter.spring.swimingbysvyter.dto.InventoryRegDTO;
import com.svyter.spring.swimingbysvyter.dto.UserListTrainingsGetDTO;
import lombok.Data;

import java.util.Collections;
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
    @Column(name = "hitch")
    private String hitch;
    @Column(name = "main_training")
    private String mainTraining;
    @Column(name = "warm_up")
    private String warmUp;

    @ManyToOne
    @JoinColumn(name = "complexity_id")
    private Complexity complexity;

    @ManyToMany
    @JoinTable(
            name = "trainings_has_inventory",
            joinColumns = @JoinColumn(name = "trainings_id"),
            inverseJoinColumns = @JoinColumn(name = "inventory_id")
    )
    private List<Inventory> inventories;

    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "trainings")
    private List<UserTrainings> userTrainings;

    public Trainings() {

    }

    public Trainings(String name, String warmUp, String mainTraining, String hitch, List<Inventory> inventories, Complexity complexity) {
        this.name = name;
        this.warmUp = warmUp;
        this.mainTraining = mainTraining;
        this.hitch = hitch;
        this.inventories = Collections.unmodifiableList(inventories);
        this.complexity = complexity;
    }

    @Override
    public String toString() {
        return "Trainings{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", warmUp='" + warmUp + '\'' +
                ", mainTraining='" + mainTraining + '\'' +
                ", hitch='" + hitch + '\'' +
                ", inventoryList=" + inventories.stream().map(InventoryRegDTO::convertToModel).toList()+
                ", UserListTrainings=" + userTrainings.stream().map(UserListTrainingsGetDTO::convertToModel).toList() +
                ", complexity=" + ComplexityDTO.convertToModel(complexity) +
                '}';
    }
}
