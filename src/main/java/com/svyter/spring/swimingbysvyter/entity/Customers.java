package com.svyter.spring.swimingbysvyter.entity;

import javax.persistence.*;

import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Customers")
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String pass;

    @Column(name = "email")
    private String email;
    @Column(name = "is_admin")
    private String admin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customers")
    private List <Categories> categories;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customers")
    private List <UserListTrainings> userListTrainings;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "customers")
    private Questioner questioner;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "customers")
    private Inventory inventory;
    public Customers() {

    }
    public Customers(String name, String pass, String email, String admin) {
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.admin = admin;
    }
}
