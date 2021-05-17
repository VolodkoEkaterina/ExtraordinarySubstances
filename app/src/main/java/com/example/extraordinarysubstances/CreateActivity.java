package com.example.extraordinarysubstances;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArraySet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class CreateActivity extends AppCompatActivity {
    private Button btAdd,btEnd;
    private EditText etTestName, etQuestionTitle, etQuestionText,etAnswer,etAnswerRight;
    private Context context;
    private long MyQuestionID;
    public int selected;
    private Spinner spinner;
    private int type;
    ArrayList<Question>arrayList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        spinner = findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                type = selectedItemPosition;
                Fragment fragment = null;
                if(type==0){
                    fragment = new RadioGroupFragment();
                } else if (type==1){
                    fragment = new EditTextFragment();
                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frame_layout, fragment);
                ft.commit();

                btAdd=(Button)findViewById(R.id.butAdd);
                btEnd=(Button)findViewById(R.id.butEnd);
                etQuestionText=(EditText)findViewById(R.id.questionText);
                etAnswer=(EditText)findViewById(R.id.answer);
                etAnswerRight=(EditText)findViewById(R.id.answerRight);
                etTestName=(EditText)findViewById(R.id.testName);
                etQuestionTitle=(EditText)findViewById(R.id.questionTitle);

                btAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Question question=new Question(MyQuestionID,etQuestionText.getText().toString(),etAnswer.getText().toString(),etAnswerRight.getText().toString(), etQuestionTitle.getText().toString(), type, etTestName.getText().toString());
                        arrayList.add(question);
                    }
                });
                btEnd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i=0; i<arrayList.size(); i++){
                            Question questions = arrayList.get(i);
                            DBQuestion db = new DBQuestion(CreateActivity.this);
                            db.insert(questions.getQuestionTitle(),questions.getQuestionText(), questions.getAnswer(), questions.getAnswerRight(), questions.getType(), questions.getTestName());
                        }
                    }
                });
            }
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        if(getIntent().hasExtra("Question")){
            Question question=(Question) getIntent().getSerializableExtra("Question");

            etQuestionText.setText(question.getQuestionText());
            etAnswer.setText(question.getAnswer());
            etAnswerRight.setText(question.getAnswerRight());
            etQuestionTitle.setText(question.getQuestionTitle());
            etTestName.setText(question.getTestName());
            MyQuestionID=question.getId();
        }
        else
        {
            MyQuestionID=-1;
        }
    }
}
