package com.svyter.spring.swimingbysvyter.dto;

import com.svyter.spring.swimingbysvyter.entity.Questioner;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Data
public class QuestionerDTO {
    private Long idCustomer;
    @Positive(message = "This is a required field!")
    private int langthPool;
    @NotBlank(message = "This is a required field!")
    private String gender;
    @Positive(message = "This is a required field!")
    private int age;
    @NotBlank(message = "This is a required field!")
    private String complexityTrain; // complexity, сложность тренировки
    @Positive(message = "This is a required field!")
    private int timeTrain; // время тренировки
    @Positive(message = "This is a required field!")
    private int countWeek; // кол-во недель
    @Positive(message = "This is a required field!")
    private int countTrainOneWeek; // кол-во тренировок в неделю


    public QuestionerDTO() {
    }


    public QuestionerDTO(Long idCustomer, int langthPool, String gender, int age, String complexityTrain, int timeTrain, int countWeek, int countTrainOneWeek) {
        this.idCustomer = idCustomer;
        this.langthPool = langthPool;
        this.gender = gender;
        this.age = age;
        this.complexityTrain = complexityTrain;
        this.timeTrain = timeTrain;
        this.countWeek = countWeek;
        this.countTrainOneWeek = countTrainOneWeek;
    }

    public  static QuestionerDTO questionerConvertor(Questioner questioner){
        if (questioner == null)
            return null;

        QuestionerDTO questionerDTO = new QuestionerDTO(questioner.getCustomers().getId(),questioner.getLengthPool(),questioner.getGender(),
                                                questioner.getAge(),questioner.getLevelTrain(),
                                                questioner.getTimeTrain(),questioner.getCountWeek(),
                                                questioner.getCountTrainOneWeek());
        return questionerDTO;
    }
}
