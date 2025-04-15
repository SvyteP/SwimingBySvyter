package com.svyter.spring.swimingbysvyter.entity;

import javax.persistence.*;

import com.svyter.spring.swimingbysvyter.model.InventoryModel;
import com.svyter.spring.swimingbysvyter.model.QuestionerModel;
import com.svyter.spring.swimingbysvyter.model.UserListTrainingsGetModel;
import lombok.Data;

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
                ", categories=" + category +
                ", userListTrainings=" + userTrainings.stream().map(UserListTrainingsGetModel::convertToModel).toList() +
                ", questioner=" + QuestionerModel.questionerConvertor(questioner)+
                ", inventories=" + inventories.stream().map(InventoryModel::convertToModel).toList() +
                '}';
    }
}
