package com.example.extraordinarysubstances;
import android.content.Context; import android.content.Intent;
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
    private EditText etTestName, etQuestionText,etAnswer,etAnswerRight, etTestComment, etPointset, etQuestionTextR,etAnswerRightR, etPointsR, etPoints;
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
        etTestName=(EditText)findViewById(R.id.testName);
        etTestComment = (EditText)findViewById(R.id.test_comment);
        btAdd=(Button)findViewById(R.id.butAdd);
        btEnd=(Button)findViewById(R.id.butEnd);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId) {
                type = selectedItemPosition;
                Fragment fragment = null;
                if(type==0){
                    fragment = new RadioGroupFragment();
                } else if (type==1){
                    fragment = new EditTextFragment();
                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frame_layout_create, fragment);
                ft.commit();
                btAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(type==0){
                            etPointsR=(EditText)findViewById(R.id.points_radiogroup);
                            etQuestionTextR=(EditText)findViewById(R.id.questionText_radiogroup);
                            etAnswer=(EditText)findViewById(R.id.answer_radiogroup);
                            etAnswerRightR=(EditText)findViewById(R.id.answerRight_radiogroup);
                            Log.d("My", etQuestionTextR + " " + etAnswerRightR + " " + etAnswer + " " + type + " " + etTestName + " " + etTestComment + " " + etPointsR); Question question=new Question(etQuestionTextR.getText().toString(),etAnswer.getText().toString(), etAnswerRightR.getText().toString(), type, etTestName.getText().toString(), etTestComment.getText().toString(), Integer.parseInt(etPointsR.getText().toString()));
                            arrayList.add(question);
                            etPointsR.setText("");
                            etQuestionTextR.setText("");
                            etAnswer.setText("");
                            etAnswerRightR.setText("");
                        } else if (type==1){
                            etPoints=(EditText)findViewById(R.id.points_editText);
                            etQuestionText=(EditText)findViewById(R.id.questionText_editText);
                            etAnswerRight=(EditText)findViewById(R.id.answerRight_editText);
                            Question question=new Question(etQuestionText.getText().toString(), "",etAnswerRight.getText().toString(), type, etTestName.getText().toString(), etTestComment.getText().toString(), Integer.parseInt(etPoints.getText().toString())); arrayList.add(question); etPoints.setText(""); etQuestionText.setText(""); etAnswerRight.setText("");
                        }
                    }
                });
                btEnd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Question question = null;
                        if(type==0){
                            etPointsR=(EditText)findViewById(R.id.points_radiogroup);
                            etQuestionTextR=(EditText)findViewById(R.id.questionText_radiogroup);
                            etAnswer=(EditText)findViewById(R.id.answer_radiogroup);
                            etAnswerRightR=(EditText)findViewById(R.id.answerRight_radiogroup);
                            Log.d("My", etQuestionTextR + " " + etAnswerRightR + " " + etAnswer + " " + type + " " + etTestName + " " + etTestComment + " " + etPointsR); question=new Question(etQuestionTextR.getText().toString(),etAnswer.getText().toString(), etAnswerRightR.getText().toString(), type, etTestName.getText().toString(), etTestComment.getText().toString(), Integer.parseInt(etPointsR.getText().toString()));
                        } else if (type==1){
                            etPoints=(EditText)findViewById(R.id.points_editText);
                            etQuestionText=(EditText)findViewById(R.id.questionText_editText);
                            etAnswerRight=(EditText)findViewById(R.id.answerRight_editText);
                            question=new Question(etQuestionText.getText().toString(), "",etAnswerRight.getText().toString(), type, etTestName.getText().toString(), etTestComment.getText().toString(), Integer.parseInt(etPoints.getText().toString())); } if(!arrayList.contains(question)){
                            arrayList.add(question);
                        } for (int i=0; i<arrayList.size(); i++){
                            Question questions = arrayList.get(i);
                            DBQuestion db = new DBQuestion(CreateActivity.this);
                            Log.d("My", arrayList.size() + " size");
                            Long l = db.insert(questions.getQuestionText(), questions.getAnswer(), questions.getAnswerRight(), questions.getType(), questions.getTestName(), questions.getTestComment(), questions.getPoints()); Log.d("My", l + " успех");
                        }
                        try{
                            Intent intent = new Intent(CreateActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }catch(Exception e){

                        }
                    }
                });
            } public void onNothingSelected(AdapterView<?> parent) { }
        });
        if(getIntent().hasExtra("Question")){
            Question question=(Question) getIntent().getSerializableExtra("Question");
            etQuestionText.setText(question.getQuestionText());
            etAnswer.setText(question.getAnswer());
            etAnswerRight.setText(question.getAnswerRight());
            etTestName.setText(question.getTestName());
            etTestComment.setText(question.getTestComment());
            MyQuestionID=question.getId();
        } else {
            MyQuestionID=-1;
        }
    }
}