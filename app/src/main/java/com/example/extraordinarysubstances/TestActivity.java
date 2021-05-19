package com.example.extraordinarysubstances;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    String textName = null;
    int number=0;
    int score=0;
    ArrayList<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        textName = intent.getStringExtra("testName");
        setContentView(R.layout.activity_select_test);
        DBQuestion db = new DBQuestion(this);
        questions = db.selectTest(textName);
        fillFragment(questions.get(0));
        Button buttonNext=(Button)findViewById(R.id.buttonNext);
        TextView Title = findViewById(R.id.title);
        Title.setText(textName);
        buttonNext.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (questions.get(number).getType() == 0) {
                    RadioGroup radioGroup = findViewById(R.id.radioGroup);
                    int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                    RadioButton Answer = findViewById(checkedRadioButtonId);
                    if (Answer.getText().equals(questions.get(number).getAnswerRight())) {
                        score += 1;
                    }

                } else if (questions.get(number).getType() == 1) {
                    EditText answerText = findViewById(R.id.questionAnswer);
                    String answer = answerText.getText().toString();
                    if (answer.equals(questions.get(number).getAnswerRight())) {
                        score += 1;
                    }
                }
                number += 1;
                if (number < questions.size()) {
                    fillFragment(questions.get(number));
                } else {
                    Intent intent = new Intent(TestActivity.this, LastActivity.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                }
            }
        });

    }
    public void fillFragment(Question question){
        if (question.getType()==0){
            Fragment fragment =  new TestRadFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frame_layout, fragment);
            ft.commit();
            TextView QuestionText =  findViewById(R.id.questionText);
            QuestionText.setText(question.getQuestionText());
            RadioButton Answer1 = findViewById(R.id.answ1);
            RadioButton Answer2 = findViewById(R.id.answ2);
            RadioButton Answer3 = findViewById(R.id.answ3);
            RadioButton Answer4 = findViewById(R.id.answ4);
            String answer = question.getAnswer();
            String[] answers = answer.split(" ");
            Answer1.setText(answers[0]);
            Answer2.setText(answers[1]);
            Answer3.setText(answers[2]);
            Answer4.setText(answers[3]);

        }
        else if (question.getType()==1){
            Fragment fragment =  new TestEditFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frame_layout, fragment);
            ft.commit();
            TextView QuestionText =  findViewById(R.id.questionText);
            QuestionText.setText(question.getQuestionText());
            EditText answerText = findViewById(R.id.questionAnswer);
            String answer = answerText.getText().toString();
        }
    }
}
