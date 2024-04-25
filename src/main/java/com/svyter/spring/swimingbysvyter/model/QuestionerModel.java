package com.svyter.spring.swimingbysvyter.model;

import com.svyter.spring.swimingbysvyter.entity.Questioner;
import lombok.Data;

@Data
public class QuestionerModel {
    private int langthPool;

    private String gender;

    private int age;

    private String levelTrain;

    private int timeTrain;

    private int countWeek;

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
