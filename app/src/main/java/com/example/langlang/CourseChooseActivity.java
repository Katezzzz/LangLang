package com.example.langlang;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.langlang.models.CourseInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class CourseChooseActivity extends AppCompatActivity {
    String URL= "http://192.168.1.8/test-android/courses/showUserCourses.php?method=get&id=";

    JSONParser jsonParser=new JSONParser();
    ListView listView;
    Button button;
    ArrayList<CourseInfo> info = new ArrayList<CourseInfo>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_choose);
        Bundle extras = getIntent().getExtras();
        int id=extras.getInt("id");
        URL=URL+id;
        listView = (ListView) findViewById(R.id.listView);
        button=(Button) findViewById(R.id.button);
        getJSON(URL);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                CourseInfo message = info.get(position);

                intent.putExtra("CourseInfo", message);

                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), SingToCourse.class);
                intent.putExtra("id", id);
                startActivity(intent);

            }
        });
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
               // Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoListView(s);
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
                        sb.append(json +"\n");
                    }

                    String json2=sb.toString().trim();
                    String jsonFinal=json2.substring(json2.indexOf("["),json2.indexOf("]")+1);
                    Log.e("123 array", jsonFinal);
                    return jsonFinal;
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String json) throws JSONException {
        //creating a json array from the json string
        JSONArray jsonArray = new JSONArray(json);

        //creating a string array for listview
        String[] heroes = new String[jsonArray.length()];

        //looping through all the elements in json array
        for (int i = 0; i < jsonArray.length(); i++) {

            //getting json object from the json array
            JSONObject obj = jsonArray.getJSONObject(i);

            //getting the name from the json object and putting it inside string array
            heroes[i] = obj.getString("name");


            CourseInfo all= new CourseInfo();
            all.setName(obj.getString("name"));
            all.setId(Integer.parseInt(obj.getString("id")));
            all.setSeats(Integer.parseInt(obj.getString("seats")));
            all.setLanguage(obj.getString("language"));
            all.setTeacher(obj.getString("teacher"));
            all.setTime(obj.getString("time"));
            //getting the name from the json object and putting it inside string array

            info.add(all);
        }
        //the array adapter to load data into list
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, heroes);

        //attaching adapter to listview
        listView.setAdapter(arrayAdapter);
    }
}