package com.svyter.spring.swimingbysvyter.dto;

import com.svyter.spring.swimingbysvyter.dto.base.DTO;
import com.svyter.spring.swimingbysvyter.entity.Customers;
import lombok.Data;

import java.util.List;
@Data
public class CustomersGetDTO implements DTO {
    private String login;
    private String email;
    private List<InventoryDTO> inventory;
    private QuestionerDTO questioner;

    public CustomersGetDTO(String login, String email, List<InventoryDTO> inventory, QuestionerDTO questioner) {
        this.login = login;
        this.email = email;
        this.inventory = inventory;
        this.questioner = questioner;
    }

    public static CustomersGetDTO convertCustomersToModel(Customers customers)
    {
        return new CustomersGetDTO(customers.getName(), customers.getEmail(),
                                        customers.getInventories().stream().map(InventoryDTO::convertToModel).toList(),
                                        QuestionerDTO.questionerConvertor(customers.getQuestioner()));
    }
}
