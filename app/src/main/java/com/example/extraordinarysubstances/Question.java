package com.example.extraordinarysubstances;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class Question extends AppCompatActivity implements Serializable {
    private long id;
    private String questionText;
    private String answer;
    private String answ1;
    private String answ2;
    private String answ3;
    private String answ4;

    public Question(long id, String questionText, String answer, String answ1, String answ2, String answ3, String answ4) {
        this.id = id;
        this.questionText = questionText;
        this.answer = answer;
        this.answ1 = answ1;
        this.answ2 = answ2;
        this.answ3 = answ3;
        this.answ4 = answ4;
    }

    public long getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }
    public String getAnswer() {
        return answ1;
    }

    public String getAnsw1() {
        return answ1;
    }

    public String getAnsw2() {
        return answ2;
    }

    public String getAnsw3() {
        return answ3;
    }

    public String getAnsw4() {
        return answ4;
    }
}
