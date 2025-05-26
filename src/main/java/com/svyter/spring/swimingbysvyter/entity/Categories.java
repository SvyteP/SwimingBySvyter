package com.svyter.spring.swimingbysvyter.entity;

import com.svyter.spring.swimingbysvyter.dto.CustomersGetDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Arrays;
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

    @Override
    public String toString() {
        return "Categories{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", customers=" + Arrays.toString(customers.stream().map(CustomersGetDTO::convertCustomersToModel).toArray()) +
                '}';
    }
}
