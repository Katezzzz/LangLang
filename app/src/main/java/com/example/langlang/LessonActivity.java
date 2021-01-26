package com.example.langlang;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LessonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
TextView label=(TextView)findViewById(R.id.lnumber);
        TextView title= (TextView)findViewById(R.id.titleL);
        TextView words= (TextView)findViewById(R.id.wordsL);
        TextView homework= (TextView)findViewById(R.id.homeworkL);
        Intent intent = getIntent();

        label.setText("Lekcja nr:"+intent.getIntExtra("number",1));
title.setText(intent.getStringExtra("text"));
        words.setText(intent.getStringExtra("words"));
        homework.setText(intent.getStringExtra("homework"));
    }
}