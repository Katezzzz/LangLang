package com.example.langlang;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.langlang.models.CourseInfo;
import com.example.langlang.models.LessonInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    String URL = "http://192.168.1.8/test-android/lessons/getLessonList.php?method=get&id=";
    public ArrayList<LessonInfo> List = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        CourseInfo CourseInfo = (CourseInfo) getIntent().getSerializableExtra("CourseInfo");
//pobranie danych o kursie
        URL = URL + CourseInfo.getId();
        getJSON(URL);







    }
    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoData(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    java.net.URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }

                    String json2 = sb.toString().trim();
                    String jsonFinal = json2.substring(json2.indexOf("["), json2.indexOf("]") + 1);
                    Log.e("jsonparser", jsonFinal);
                    this.getStatus();
                    Log.d("status", String.valueOf(this.getStatus()));
                    return jsonFinal;
                } catch (Exception e) {
                    return null;
                }
            }
        }

        GetJSON getJSON = new GetJSON();
        getJSON.execute();


    }

    private void loadIntoData(String json) throws JSONException {
        //creating a json array from the json string
        JSONArray jsonArray = new JSONArray(json);

        //creating a string array for listview
        String[] lessonsNumbers = new String[jsonArray.length()];


        //looping through all the elements in json array
        for (int i = 0; i < jsonArray.length(); i++) {

            //getting json object from the json array
            JSONObject obj = jsonArray.getJSONObject(i);
            lessonsNumbers[i] = Integer.toString(i + 1) + obj.getString("title");
            LessonInfo newLesson = new LessonInfo();
            newLesson.setIdSubject(Integer.parseInt(obj.getString("idSubject")));
            newLesson.setIdCourse(Integer.parseInt(obj.getString("idCourse")));
            newLesson.setText(obj.getString("text"));
            newLesson.setHomework(obj.getString("homework"));
            newLesson.setTitle(obj.getString("title"));
            newLesson.setWords(obj.getString("words"));
            newLesson.setLessonNumber(i + 1);


            List.add(newLesson);
        }
        Button all = (Button) findViewById(R.id.all);
        Button last = (Button) findViewById(R.id.last);
        Button homew = (Button) findViewById(R.id.homew);
        Button testy = (Button) findViewById(R.id.Testy);
        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuActivity.this, PanelActivity.class);
                LessonInfo l=List.get(List.size()-1);
                intent.putExtra("text",l.getText());
                intent.putExtra("homework",l.getHomework());
                intent.putExtra("words",l.getWords());
                startActivity(intent);

            }
        });
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuActivity.this, AllActivity.class);

                intent.putParcelableArrayListExtra("List",List);
                startActivity(intent);

            }
        });
        homew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuActivity.this, HomeworkList.class);

                intent.putParcelableArrayListExtra("List",List);
                startActivity(intent);

            }
        });
        testy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuActivity.this, TestChooseActivity.class);


                startActivity(intent);

            }
        });
    }
}