package com.example.extraordinarysubstances;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class Question extends AppCompatActivity implements Serializable {
    private long id;
    private String questionText;
    private String answer;
    private String answerRight;
    private int type;
    private String testName;
    private String testComment;
    private int points;

    public Question(long id, String questionText, String answer, String answerRight, int type, String testName, String testComment, int points) {
        this.id = id;
        this.questionText = questionText;
        this.answer = answer;
        this.answerRight = answerRight;
        this.type = type;
        this.testName = testName;
        this.testComment = testComment;
        this.points = points;
    }

    public Question( String questionText, String answer, String answerRight, int type, String testName, String testComment, int points) {
        this.questionText = questionText;
        this.answer = answer;
        this.answerRight = answerRight;
        this.type = type;
        this.testName = testName;
        this.testComment = testComment;
        this.points = points;
    }


    public long getId() {
        return id;
    }
    public String getQuestionText() {
        return questionText;
    }
    public String  getAnswer() {
        return answer;
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

    public String getTestComment() {
        return testComment;
    }

    public int getPoints() {
        return points;
    }

}
