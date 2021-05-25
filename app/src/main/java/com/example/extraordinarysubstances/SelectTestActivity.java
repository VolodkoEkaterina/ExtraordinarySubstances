package com.example.extraordinarysubstances;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
public class SelectTestActivity extends AppCompatActivity {
    ListView listView;
    private Object AdapterView;
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
        testName.remove(null);
        Log.d("My", questions.toString());
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, testName);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                TextView textView = (TextView) itemClicked;
                String textName = textView.getText().toString();
                Intent intent= new Intent(SelectTestActivity.this, TestActivity.class);
                intent.putExtra("testName", textName); startActivity(intent);
            }
        });
    }
}