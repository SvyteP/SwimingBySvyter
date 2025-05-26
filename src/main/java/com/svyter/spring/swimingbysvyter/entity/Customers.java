package com.svyter.spring.swimingbysvyter.entity;

import jakarta.persistence.*;

import com.svyter.spring.swimingbysvyter.dto.InventoryRegDTO;
import com.svyter.spring.swimingbysvyter.dto.QuestionerDTO;
import com.svyter.spring.swimingbysvyter.dto.UserListTrainingsGetDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Customers")
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String pass;
    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "categories_id")
    private Categories category;

    @OneToMany(mappedBy = "customers")
    private List <UserTrainings> userTrainings;

    @OneToOne
    @JoinColumn(name = "questioner_id")
    private Questioner questioner;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "customers_has_inventory",
            joinColumns = @JoinColumn(name = "customers_id"),
            inverseJoinColumns = @JoinColumn(name = "inventory_id")
    )
    private List <Inventory> inventories;

    public Customers(String name, String pass, String email) {
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.userTrainings = new ArrayList<>();
        this.inventories = new ArrayList<>();
    }

    public Customers() {

    }

    @Override
    public String toString() {
        return "Customers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                ", email='" + email + '\'' +
                ", categories=" + (category != null ? category.getName() : "null") +
                ", userListTrainings=" + (userTrainings != null ? userTrainings.stream().map(UserListTrainingsGetDTO::convertToModel).toList().toString() : "null") +
                ", questioner=" + (questioner != null ? QuestionerDTO.questionerConvertor(questioner).toString() : "null") +
                ", inventories=" + (inventories != null ? inventories.stream().map(InventoryRegDTO::convertToModel).toList().toString() : "null") +
                '}';
    }
}
