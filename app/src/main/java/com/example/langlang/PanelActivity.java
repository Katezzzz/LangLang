package com.example.langlang;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PanelActivity extends AppCompatActivity {
    String URL = "http://192.168.1.8/test-android/lessons/getLessonList.php?method=get&id=";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_panel2);
        Bundle extras = getIntent().getExtras();
//pobranie danych o kursie

        TextView title= (TextView)findViewById(R.id.title);
        TextView words= (TextView)findViewById(R.id.words);
        TextView homework= (TextView)findViewById(R.id.homework);


            title.setText(extras.getString("text"));
            words.setText(extras.getString("words"));
            homework.setText(extras.getString("homework"));

        }
    }





