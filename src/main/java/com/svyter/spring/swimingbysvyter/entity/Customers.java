package com.svyter.spring.swimingbysvyter.entity;

import jakarta.persistence.*;
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
    @Column(name = "is_admin")
    private boolean isAdmin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customers")
    private List <Categories> categories;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customers")
    private List <UserListTrainings> userListTrainings;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customers")
    private List <Questioner> questioner;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customers")
    private List <Inventory> inventory;

    public Customers(String name, String pass, String email, boolean isAdmin) {
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public Customers() {

    }
}
