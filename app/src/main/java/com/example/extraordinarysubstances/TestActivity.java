package com.example.extraordinarysubstances;

import android.content.Intent;

import android.os.Bundle;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
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
    int c = 0;
    int p =0;
    ArrayList<Question> questions;
    ArrayList<Integer> wrongList;
    TextView questionText;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test);
        handler = new Handler(Looper.getMainLooper());
        Intent intent = getIntent();
        textName = intent.getStringExtra("testName");

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
                        score = score+p;

                    }else{
                        wrongList = new ArrayList<>();
                        wrongList.add(number);
                    }

                } else if (questions.get(number).getType() == 1) {
                    EditText answerText = findViewById(R.id.questionAnswer);
                    String answer = answerText.getText().toString();
                    if (answer.equals(questions.get(number).getAnswerRight())) {
                        score = score + p;
                    }else{
                    }
                }
                number += 1;
                if (number < questions.size()) {
                    fillFragment(questions.get(number));
                } else {
                    Intent intent = new Intent(TestActivity.this, LastActivity.class);
                    intent.putExtra("score", score);
                    intent.putExtra("point", c);
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
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    handler.post(new Runnable(){
                        @Override
                        public void run(){
                            questionText =  findViewById(R.id.questionText);
                            questionText.setText(question.getQuestionText());
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

                    });
                }
            }).start();
            p = question.getPoints();
            c= c+p;

        }
        else if (question.getType()==1){
            Fragment fragment =  new TestEditFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frame_layout, fragment);
            ft.commit();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    handler.post(new Runnable(){
                        @Override
                        public void run(){
                            questionText =  findViewById(R.id.question_Text);
                            questionText.setText(question.getQuestionText());
                            EditText answerText = findViewById(R.id.questionAnswer);
                            String answer = answerText.getText().toString();
                        }

                    });
                }
            }).start();
            p = question.getPoints();
            c= c+p;
        }
    }
}
