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
    @Column(name = "board")
    private boolean board;
    @Column(name = "kolobashka")
    private boolean kolobashka;
    @Column(name = "paddles")
    private boolean paddles;
    @Column(name = "fingerPadd")
    private boolean fingerPadd;
    @Column(name = "flippers")
    private boolean flippers;
    @Column(name = "snorkel")
    private boolean snorkel;
    @OneToOne
    @JoinColumn(name = "customers")
    private Customers customers;


}
