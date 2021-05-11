package com.example.extraordinarysubstances;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    private Button btSave,btCancel;
    private EditText etQuestionText,etAnswer,etAnsw1,etAnsw2,etAnsw3,etAnsw4;
    private Context context;
    private long MyQuestionID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etQuestionText=(EditText)findViewById(R.id.questionText);
        etAnswer=(EditText)findViewById(R.id.answer);
        etAnsw1=(EditText)findViewById(R.id.answ1);
        etAnsw2=(EditText)findViewById(R.id.answ2);
        etAnsw3=(EditText)findViewById(R.id.answ3);
        etAnsw4=(EditText)findViewById(R.id.answ4);
        btSave=(Button)findViewById(R.id.butSave);
        btCancel=(Button)findViewById(R.id.butCancel);

        if(getIntent().hasExtra("Question")){
            Question question=(Question) getIntent().getSerializableExtra("Question");
            etQuestionText.setText(question.getQuestionText());
            etAnswer.setText(question.getAnswer());
            etAnsw1.setText(question.getAnsw1());
            etAnsw2.setText(question.getAnsw2());
            etAnsw3.setText(question.getAnsw3());
            etAnsw4.setText(question.getAnsw4());
            MyQuestionID=question.getId();
        }
        else
        {
            MyQuestionID=-1;
        }
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question question=new Question(MyQuestionID,etQuestionText.getText().toString(),etAnswer.getText().toString(),etAnsw1.getText().toString(), etAnsw2.getText().toString(), etAnsw3.getText().toString(), etAnsw4.getText().toString());
                Intent intent=getIntent();
                intent.putExtra("Question",question);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
