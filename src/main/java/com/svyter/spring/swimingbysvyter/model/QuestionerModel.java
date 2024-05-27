package com.svyter.spring.swimingbysvyter.model;

import com.svyter.spring.swimingbysvyter.entity.Questioner;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class QuestionerModel {
    private Long idCustomer;
    @Positive(message = "This is a required field!")
    private int langthPool;
    @NotBlank(message = "This is a required field!")
    private String gender;
    @Positive(message = "This is a required field!")
    private int age;
    @NotBlank(message = "This is a required field!")
    private String levelTrain; // complexity, сложность тренировки
    @Positive(message = "This is a required field!")
    private int timeTrain; // время тренировки
    @Positive(message = "This is a required field!")
    private int countWeek; // кол-во недель
    @Positive(message = "This is a required field!")
    private int countTrainOneWeek; // кол-во тренировок в неделю

    public QuestionerModel() {
    }


    public QuestionerModel(Long idCustomer, int langthPool, String gender, int age, String levelTrain, int timeTrain, int countWeek, int countTrainOneWeek) {
        this.idCustomer = idCustomer;
        this.langthPool = langthPool;
        this.gender = gender;
        this.age = age;
        this.levelTrain = levelTrain;
        this.timeTrain = timeTrain;
        this.countWeek = countWeek;
        this.countTrainOneWeek = countTrainOneWeek;
    }

    public  static QuestionerModel questionerConvertor(Questioner questioner){
        QuestionerModel questionerModel = new QuestionerModel(questioner.getCustomers().getId(),questioner.getLangthPool(),questioner.getGender(),
                                                questioner.getAge(),questioner.getLevelTrain(),
                                                questioner.getTimeTrain(),questioner.getCountWeek(),
                                                questioner.getCountTrainOneWeek());
        return questionerModel;
    }
}
