package com.example.extraordinarysubstances;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    TextView txtscore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        txtscore = (TextView)findViewById(R.id.txtscore1);
        Intent intent = getIntent();
        int score = intent.getIntExtra("score",60000);
        if (score<500){
            txtscore.setText("Ваш результат: " +  score + " из 1000\n\nВам стоит подучить химию!");
        }
        if (score<800){
            txtscore.setText("Ваш результат: " +  score + " из 1000\n\nВы почти у цели, не\nостанавливайтесь, потренируйтесь ещё");
        }
        if (score>790){
            txtscore.setText("Ваш результат: " +  score + " из 1000\n\nВы просто химический гений какой-то!");
        }
        Button buttonEnd1 = (Button) findViewById(R.id.button);
        buttonEnd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
