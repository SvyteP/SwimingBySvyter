package com.svyter.spring.swimingbysvyter.dto;

import com.svyter.spring.swimingbysvyter.dto.base.DTO;
import com.svyter.spring.swimingbysvyter.entity.Customers;
import lombok.Data;

import java.util.List;
@Data
public class CustomersGetDTO implements DTO {
    private String login;
    private String email;
    private List<InventoryDto> inventory;
    private QuestionerDTO questioner;

    public CustomersGetDTO(String login, String email, List<InventoryDto> inventory, QuestionerDTO questioner) {
        this.login = login;
        this.email = email;
        this.inventory = inventory;
        this.questioner = questioner;
    }

    public static CustomersGetDTO convertCustomersToModel(Customers customers)
    {
        if (customers == null) return null;
        return new CustomersGetDTO(customers.getName(), customers.getEmail(),
                                        customers.getInventories().stream().map(InventoryDto::convertToModel).toList(),
                                        QuestionerDTO.questionerConvertor(customers.getQuestioner()));
    }
}
