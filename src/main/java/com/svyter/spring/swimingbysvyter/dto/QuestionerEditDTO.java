package com.svyter.spring.swimingbysvyter.dto;

import com.svyter.spring.swimingbysvyter.entity.Questioner;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class QuestionerEditDTO {
    @Positive(message = "This is a required field!")
    private int lengthPool;
    @NotBlank(message = "This is a required field!")
    private String gender;
    @Positive(message = "This is a required field!")
    private int age;
    @Positive(message = "This is a required field!")
    private Long complexityId; // complexity, сложность тренировки
    @Positive(message = "This is a required field!")
    private int timeTrain; // время тренировки
    @Positive(message = "This is a required field!")
    private int countWeek; // кол-во недель
    @Positive(message = "This is a required field!")
    private int countTrainOneWeek; // кол-во тренировок в неделю


    public QuestionerEditDTO() {
    }


    public QuestionerEditDTO(int lengthPool, String gender, int age, Long complexityId, int timeTrain, int countWeek, int countTrainOneWeek) {
        this.lengthPool = lengthPool;
        this.gender = gender;
        this.age = age;
        this.complexityId = complexityId;
        this.timeTrain = timeTrain;
        this.countWeek = countWeek;
        this.countTrainOneWeek = countTrainOneWeek;
    }

    public  static QuestionerEditDTO questionerConvertor(Questioner questioner){
        if (questioner == null) return null;

        QuestionerEditDTO questionerDTO = new QuestionerEditDTO(questioner.getLengthPool(),questioner.getGender(),
                questioner.getAge(),questioner.getId(),
                questioner.getTimeTrain(),questioner.getCountWeek(),
                questioner.getCountTrainOneWeek() );
        return questionerDTO;
    }

    @Override
    public String toString() {
        return "QuestionerEditDTO{" +
                ", lengthPool=" + lengthPool +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", complexityTrain='" + complexityId + '\'' +
                ", timeTrain=" + timeTrain +
                ", countWeek=" + countWeek +
                ", countTrainOneWeek=" + countTrainOneWeek +
                '}';
    }
}
