package com.example.extraordinarysubstances;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SearchActivity extends AppCompatActivity {
    private EditText search_field;
    private Button search_btn;
    private TextView result_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_search);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //search_btn = findViewById(R.id.search_btn);
       // search_field = findViewById(R.id.search_field);
        //result_info = findViewById(R.id.result_info);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(search_field.getText().toString().trim().equals(""))
                    Toast.makeText(SearchActivity.this, R.string.hint_search,Toast.LENGTH_SHORT).show();
                else{
                    String substance = search_field.getText().toString();
                    String key = "";//вставить api ключ
                    String url="";

                    new GetData().execute(url);
                }
            }
        });
    }

    private class GetData extends AsyncTask<String,String,String>{

        protected void onPreExecute(){
            super.onPreExecute();
            result_info.setText("Ожидайте...");
        }

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection con = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(strings[0]);
                con = (HttpURLConnection) url.openConnection();
                con.connect();

                InputStream stream = con.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String str = "";

                while((str = reader.readLine()) != null)
                    buffer.append(str).append("\n");
                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(con != null)
                   con.disconnect();

                try {
                    if (reader != null)
                        reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            result_info.setText(result);

            try {
                JSONObject obj = new JSONObject(result);
                //result_info.setText("Химическая формула: " + obj.getJSONObject("")."");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
