package com.svyter.spring.swimingbysvyter.dto;

import com.svyter.spring.swimingbysvyter.dto.base.DTO;
import com.svyter.spring.swimingbysvyter.entity.Questioner;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Data
public class QuestionerDTO implements DTO {
    @Positive(message = "This is a required field!")
    private int lengthPool;
    @NotBlank(message = "This is a required field!")
    private String gender;
    @Positive(message = "This is a required field!")
    private int age;
    @NotNull(message = "This is a required field!")
    private ComplexityDTO complexity; // complexity, сложность тренировки
    @Positive(message = "This is a required field!")
    private int timeTrain; // время тренировки
    @Positive(message = "This is a required field!")
    private int countWeek; // кол-во недель
    @Positive(message = "This is a required field!")
    private int countTrainOneWeek; // кол-во тренировок в неделю


    public QuestionerDTO() {
    }


    public QuestionerDTO(int lengthPool, String gender, int age, ComplexityDTO complexityDTO, int timeTrain, int countWeek, int countTrainOneWeek) {
        this.lengthPool = lengthPool;
        this.gender = gender;
        this.age = age;
        this.complexity = complexityDTO;
        this.timeTrain = timeTrain;
        this.countWeek = countWeek;
        this.countTrainOneWeek = countTrainOneWeek;
    }

    public  static QuestionerDTO questionerConvertor(Questioner questioner){
        if (questioner == null) return null;

        QuestionerDTO questionerDTO = new QuestionerDTO(questioner.getLengthPool(),questioner.getGender(),
                                                questioner.getAge(),ComplexityDTO.convertToModel(questioner.getComplexity()),
                                                questioner.getTimeTrain(),questioner.getCountWeek(),
                                                questioner.getCountTrainOneWeek() );
        return questionerDTO;
    }

    @Override
    public String toString() {
        return "QuestionerDTO{" +
                ", lengthPool=" + lengthPool +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", complexityTrain='" + complexity + '\'' +
                ", timeTrain=" + timeTrain +
                ", countWeek=" + countWeek +
                ", countTrainOneWeek=" + countTrainOneWeek +
                '}';
    }
}
