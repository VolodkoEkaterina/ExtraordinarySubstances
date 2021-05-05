package com.example.extraordinarysubstances;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LastActivity extends AppCompatActivity {
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
        if (score<400){
            txtscore.setText("Ваш результат: " +  score + " из 1000 " + "\n Вам стоит подучить химию");

        }
        if (score>390 && score<800){
            txtscore.setText("Ваш результат: " +  score + " из 1000 " + "\n Неплохо, вы почти знаток химии.");

        }
        if (score>790){
            txtscore.setText("Ваш результат: " +  score + " из 1000 " + "\n Молодцы, Вы химический гений.");
        }
    }
}
