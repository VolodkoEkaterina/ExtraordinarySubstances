package com.example.extraordinarysubstances;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SelectTestActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_test);
        listView = (ListView) findViewById(R.id.list);
        DBQuestion db = new DBQuestion(this);
        ArrayList<Question> questions = db.selectAll();
        ArrayAdapter<String> adapter;
        ArrayList<String> testName = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++){
            if (!testName.contains(questions.get(i).getTestName())){
                testName.add(questions.get(i).getTestName());
            }
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, testName);
        listView.setAdapter(adapter);
    }
}
