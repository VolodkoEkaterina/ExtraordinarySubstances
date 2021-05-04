package com.example.extraordinarysubstances;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SixthQuestion extends AppCompatActivity {
    private int score=0;
    private int c=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        score = intent.getIntExtra("score", score);

        setContentView(R.layout.sixthquestion);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        RadioGroup radioGroup = findViewById(R.id.radioGroup6);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case -1:
                        Toast.makeText(getApplicationContext(), "Ничего не выбрано",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.answ61:
                        c=0;
                        break;
                    case R.id.answ62:
                        c=0;
                        break;
                    case R.id.answ63:
                        c=1;
                        break;
                    case R.id.answ64:
                        c=0;
                        break;
                    default:
                        break;
                }
            }
        });
        Button buttonNext=(Button)findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(c==1){
                        score+=100;
                    }
                    Intent intent= new Intent(SixthQuestion.this, SeventhQuestion.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Произошла непредвиденная ошибка", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}