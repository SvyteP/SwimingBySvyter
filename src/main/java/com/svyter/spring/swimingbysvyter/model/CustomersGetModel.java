package com.svyter.spring.swimingbysvyter.model;

import com.svyter.spring.swimingbysvyter.entity.Categories;
import com.svyter.spring.swimingbysvyter.entity.Customers;
import com.svyter.spring.swimingbysvyter.entity.Inventory;
import com.svyter.spring.swimingbysvyter.entity.Questioner;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;
@Data
public class CustomersGetModel {
    private String login;
    private String email;
    private String admin;
    private List<Inventory> inventory;
    private Questioner questioner;

    public CustomersGetModel(String login, String email, String admin, List<Inventory> inventory, Questioner questioner) {
        this.login = login;
        this.email = email;
        this.admin = admin;
        this.inventory = inventory;
        this.questioner = questioner;
    }

    public static CustomersGetModel convertCustomersToModel(Customers customers)
    {
        CustomersGetModel customersGetModel = new CustomersGetModel(customers.getName(), customers.getEmail(), customers.getAdmin(),
                                                                    customers.getInventory(),customers.getQuestioner());
        return customersGetModel;
    }
}
