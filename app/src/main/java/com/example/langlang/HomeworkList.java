package com.example.langlang;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.langlang.models.LessonInfo;

import java.util.ArrayList;

public class HomeworkList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ArrayList<LessonInfo> List =  getIntent().getParcelableArrayListExtra("List");
        ListView listView = (ListView) findViewById(R.id.homeworkList);
        String[] heroes = new String[List.size()];
        for (int i = 0; i < List.size(); i++) {

            //getting json object from the json array

            heroes[i] = "Praca domowa nr "+List.get(i).getLessonNumber()+": "+List.get(i).getHomework();



        }

        //the array adapter to load data into list
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, heroes);

        //attaching adapter to listview
        listView.setAdapter(arrayAdapter);
    }
}