package com.example.extraordinarysubstances;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

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
        txtscore.setText("Ваш результат: " +  score);
    }
}
