package com.svyter.spring.swimingbysvyter.entity;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "you_have")
    private boolean  youHave;
    @ManyToOne
    @JoinColumn(name = "customers")
    private Customers customers;

    public Inventory() {
    }

    public Inventory(String name, boolean youHave) {
        this.name = name;
        this.youHave = youHave;
    }
}
