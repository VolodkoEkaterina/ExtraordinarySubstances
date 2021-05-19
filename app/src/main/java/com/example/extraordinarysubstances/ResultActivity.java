package com.example.extraordinarysubstances;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    TextView txtscore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        txtscore = (TextView)findViewById(R.id.txtscore);
        Intent intent = getIntent();
        int score = intent.getIntExtra("score",60000);
        if (score<500){
            txtscore.setText("Ваш результат: " +  score + " из 1000 Вам стоит подучить химию!");
        }
        if (score<800){
            txtscore.setText("Ваш результат: " +  score + " из 1000 Вы почти у цели, не останавливайтесь, потренируйтесь ещё");
        }
        if (score>790){
            txtscore.setText("Ваш результат: " +  score + " из 1000 Вы просто химический гений какой-то!");
        }
    }
}
