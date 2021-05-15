package com.example.extraordinarysubstances;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class Question extends AppCompatActivity implements Serializable {
    private long id;
    private String questionTitle;
    private String questionText;
    private String answer;
    private String answerRight;
    private int type;
    private String testName;

    public Question(long id, String questionTitle, String questionText, String answer, String answerRight, int type, String testName) {
        this.id = id;
        this.questionTitle = questionTitle;
        this.questionText = questionText;
        this.answer = answer;
        this.answerRight = answerRight;
        this.type = type;
        this.testName = testName;
    }

    public Question(String questionTitle, String questionText, String answer, String answerRight, int type, String testName) {
        this.questionTitle = questionTitle;
        this.questionText = questionText;
        this.answer = answer;
        this.answerRight = answerRight;
        this.type = type;
        this.testName = testName;
    }


    public long getId() {
        return id;
    }
    public String getQuestionTitle() {
        return questionTitle;
    }
    public String getQuestionText() {
        return questionText;
    }
    public String [] getAnswer() {
        String[] answers = answer.split(" ");
        return answers;
    }

    public String getAnswerRight() {
        return answerRight;
    }

    public int getType() {
        return type;
    }

    public String getTestName() {
        return testName;
    }

}
