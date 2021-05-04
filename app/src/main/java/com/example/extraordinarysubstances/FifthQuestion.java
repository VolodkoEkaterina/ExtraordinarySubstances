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

public class FifthQuestion extends AppCompatActivity {
    private int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        score = intent.getIntExtra("score", score);
        setContentView(R.layout.fifthquestion);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Button buttonNext=(Button)findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    EditText answerText = findViewById(R.id.answ51);
                    String answer = answerText.getText().toString();
                    if (answer.equals("бензойная кислота")){
                        score+=100;
                    }
                    Intent intent= new Intent(FifthQuestion.this, SixthQuestion.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Произошла непредвиденная ошибка", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}