package com.svyter.spring.swimingbysvyter.model;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class QuestionerCreateModel {
    private int langthPool;

    private String gender;

    private int age;

    private String levelTrain;

    private int timeTrain;

    private int countWeek;

    private int countTrainOneWeek;
}
