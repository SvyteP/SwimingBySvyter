package com.svyter.spring.swimingbysvyter.model;

import com.svyter.spring.swimingbysvyter.entity.Categories;
import com.svyter.spring.swimingbysvyter.entity.Customers;
import com.svyter.spring.swimingbysvyter.entity.Inventory;
import com.svyter.spring.swimingbysvyter.entity.Questioner;
import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
@Data
public class CustomersGetModel {
    private String login;
    private String email;
    private List<InventoryModel> inventory;
    private QuestionerModel questioner;

    public CustomersGetModel(String login, String email,List<InventoryModel> inventory, QuestionerModel questioner) {
        this.login = login;
        this.email = email;
        this.inventory = inventory;
        this.questioner = questioner;
    }

    public static CustomersGetModel convertCustomersToModel(Customers customers)
    {
        return new CustomersGetModel(customers.getName(), customers.getEmail(),
                                        customers.getInventories().stream().map(InventoryModel::convertToModel).toList(),
                                        QuestionerModel.questionerConvertor(customers.getQuestioner()));
    }
}
