package com.example.langlang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.langlang.models.LessonInfo;

import java.util.ArrayList;

public class AllActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
        ArrayList<LessonInfo> List =  getIntent().getParcelableArrayListExtra("List");
        ListView  listView = (ListView) findViewById(R.id.lessonsList);
        String[] heroes = new String[List.size()];
        for (int i = 0; i < List.size(); i++) {

            //getting json object from the json array

            heroes[i] = "Lekcja nr "+List.get(i).getLessonNumber();



        }

        //the array adapter to load data into list
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, heroes);

        //attaching adapter to listview
        listView.setAdapter(arrayAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(AllActivity.this, LessonActivity.class);
               LessonInfo message = List.get(position);
                intent.putExtra("text", message.getText());
                intent.putExtra("homework", message.getHomework());
                intent.putExtra("words", message.getWords());
                intent.putExtra("number", message.getLessonNumber());
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }


}