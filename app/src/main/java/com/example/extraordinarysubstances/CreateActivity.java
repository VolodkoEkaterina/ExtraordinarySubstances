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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class CreateActivity extends AppCompatActivity {
    private Button btAdd,btEnd;
    private EditText etTestName, etQuestionTitle, etQuestionText,etAnswer,etAnswerRight;
    private Context context;
    private long MyQuestionID;
    public int selected;
    private Spinner spinner;
    private String mychoose;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        etQuestionText=(EditText)findViewById(R.id.questionText);
        etAnswer=(EditText)findViewById(R.id.answer);
        etAnswerRight=(EditText)findViewById(R.id.answerRight);
        etTestName=(EditText)findViewById(R.id.testName);
        etQuestionTitle=(EditText)findViewById(R.id.questionTitle);
        btAdd=(Button)findViewById(R.id.butAdd);
        btEnd=(Button)findViewById(R.id.butEnd);
        spinner = findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                String[] choose = getResources().getStringArray(R.array.question_type);
                mychoose = choose[selectedItemPosition];
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        if(getIntent().hasExtra("Question")){
            Question question=(Question) getIntent().getSerializableExtra("Question");
            if (mychoose.equals('0')){
                type = 0;
            }
            else{
                type = 1;
            }
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
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question question=new Question(MyQuestionID,etQuestionText.getText().toString(),etAnswer.getText().toString(),etAnswerRight.getText().toString(), etQuestionTitle.getText().toString(), type, etTestName.getText().toString());
                Intent intent=getIntent();
                intent.putExtra("Question",question);
                setResult(RESULT_OK,intent);
                finish();
                // как сделать несколько вопросов в массиве?
            }
        });

        btEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //как создать весь тест из нескольких вопросов?
            }
        });

    }
    public void Change(View view){
        Fragment fragment = null;

        if (mychoose.equals('0')) {
            fragment = new RadioGroupFragment();
        } else if (mychoose.equals('1')) {
            fragment = new EditTextFragment();
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fr_rad,fragment);
        ft.commit();
    }
}
