package com.svyter.spring.swimingbysvyter.dto;

import com.svyter.spring.swimingbysvyter.dto.base.DTO;
import com.svyter.spring.swimingbysvyter.entity.Customers;
import com.svyter.spring.swimingbysvyter.entity.Inventory;
import lombok.Data;

import java.util.List;

@Data
public class CustomersGetWithTokenDTO implements DTO {
    private String login;
    private String email;
    private List<InventoryDto> inventory;
    private QuestionerDTO questioner;
    private String token;

    public CustomersGetWithTokenDTO(String login, String email, List<InventoryDto> inventory, QuestionerDTO questioner, String token) {
        this.login = login;
        this.email = email;
        this.inventory = inventory;
        this.questioner = questioner;
        this.token = token;
    }

    public static CustomersGetWithTokenDTO convertCustomersToModel(Customers customers, String token) {
        if (customers == null || token == null) return null;
        return new CustomersGetWithTokenDTO (customers.getName(), customers.getEmail(),
                customers.getInventories().stream().map(InventoryDto::convertToModel).toList(),
                QuestionerDTO.questionerConvertor(customers.getQuestioner()),token);
    }
}
