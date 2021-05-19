package com.example.extraordinarysubstances;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    private EditText etTestName, etQuestionText,etAnswer,etAnswerRight, etTestComment, etPoints;
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
                etPoints=(EditText)findViewById(R.id.points);
                etTestComment = (EditText)findViewById(R.id.test_comment);
                etQuestionText=(EditText)findViewById(R.id.questionText);
                etAnswer=(EditText)findViewById(R.id.answer);
                etAnswerRight=(EditText)findViewById(R.id.answerRight);
                etTestName=(EditText)findViewById(R.id.testName);


                btAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Question question=new Question(MyQuestionID,etQuestionText.getText().toString(),etAnswer.getText().toString(),etAnswerRight.getText().toString(),  type, etTestName.getText().toString(), etTestComment.getText().toString(), etPoints.getText().toInt());
                        arrayList.add(question);


                    }
                });
                btEnd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i=0; i<arrayList.size(); i++){
                            Question questions = arrayList.get(i);
                            DBQuestion db = new DBQuestion(CreateActivity.this);
                            db.insert(questions.getQuestionText(), questions.getAnswer(), questions.getAnswerRight(), questions.getType(), questions.getTestName(), questions.getTestComment(), questions.getPoints());
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
            etTestName.setText(question.getTestName());
            etTestComment.setText(question.getTestComment());
            MyQuestionID=question.getId();
        }
        else
        {
            MyQuestionID=-1;
        }
    }
}