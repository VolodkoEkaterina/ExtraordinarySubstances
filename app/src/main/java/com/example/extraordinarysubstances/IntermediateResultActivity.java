package com.example.extraordinarysubstances;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.extraordinarysubstances.EighthQuestion;

public class IntermediateResultActivity extends AppCompatActivity {
    TextView txtinscore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate_result);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        txtinscore = (TextView)findViewById(R.id.txtinscore);
        Intent intent = getIntent();
        int score = intent.getIntExtra("score",60000);
        txtinscore.setText("Ваш промежуточный результат: " + score + " из 700");
        Button buttonNext = (Button) findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntermediateResultActivity.this, EighthQuestion.class);
                intent.putExtra("score", score);
                startActivity(intent);
            }
        });
    }
}
