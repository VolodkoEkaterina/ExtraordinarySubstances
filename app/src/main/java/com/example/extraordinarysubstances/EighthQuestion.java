package com.example.extraordinarysubstances;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EighthQuestion extends AppCompatActivity {
    private int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        score = intent.getIntExtra("score", score);
        setContentView(R.layout.eighthquestion);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        Button buttonNext=(Button)findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText answerText = findViewById(R.id.answ81);
                String answer = answerText.getText().toString();
                if (answer.equals("пироксилин")){
                    score+=100;
                }
                try {
                    Intent intent= new Intent(EighthQuestion.this, NinthQuestion.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Произошла непредвиденная ошибка", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
