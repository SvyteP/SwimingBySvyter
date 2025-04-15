package com.svyter.spring.swimingbysvyter.entity;

import javax.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "category")
    private List<Customers> customers;
}
