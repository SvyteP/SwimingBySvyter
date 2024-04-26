package com.svyter.spring.swimingbysvyter.model;

import com.svyter.spring.swimingbysvyter.entity.Questioner;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class QuestionerModel {

    @Positive(message = "This is a required field!")
    private int langthPool;
    @NotBlank(message = "This is a required field!")
    private String gender;
    @Positive(message = "This is a required field!")
    private int age;
    @NotBlank(message = "This is a required field!")
    private String levelTrain;
    @Positive(message = "This is a required field!")
    private int timeTrain;
    @Positive(message = "This is a required field!")
    private int countWeek;
    @Positive(message = "This is a required field!")
    private int countTrainOneWeek;

    public QuestionerModel() {
    }

    public QuestionerModel(int langthPool, String gender, int age, String levelTrain, int timeTrain, int countWeek, int countTrainOneWeek) {
        this.langthPool = langthPool;
        this.gender = gender;
        this.age = age;
        this.levelTrain = levelTrain;
        this.timeTrain = timeTrain;
        this.countWeek = countWeek;
        this.countTrainOneWeek = countTrainOneWeek;
    }

    public  static QuestionerModel questionerConvertor(Questioner questioner){
        QuestionerModel questionerModel = new QuestionerModel(questioner.getLangthPool(),questioner.getGender(),
                                                questioner.getAge(),questioner.getLevelTrain(),
                                                questioner.getTimeTrain(),questioner.getCountWeek(),
                                                questioner.getCountTrainOneWeek());
        return questionerModel;
    }
}
