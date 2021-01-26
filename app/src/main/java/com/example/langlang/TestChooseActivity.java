package com.example.langlang;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.langlang.models.Question;
import com.example.langlang.models.TestArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class TestChooseActivity extends AppCompatActivity {
    String URL= "http://192.168.1.8/test-android/showTests.php";
   ArrayList<TestArray> testy=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_choose);
        JSONParser jsonParser=new JSONParser();

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

        ArrayList<TestArray> testy=new ArrayList<>();

        int lastid=1;
        ArrayList<Question> q=new ArrayList<>();
        TestArray t=new TestArray(1,q);
        //looping through all the elements in json array
        for (int i = 0; i < jsonArray.length(); i++) {

            //getting json object from the json array
            JSONObject obj = jsonArray.getJSONObject(i);

            Question newQuestion = new Question();
            int testid=obj.getInt("idTest");
            if(testid!=lastid){
                testy.add(t);
                q=new ArrayList<>();
                 t=new TestArray(testid,q);
                lastid=testid;
            }
            newQuestion.setQuestion(obj.getString("question"));
            newQuestion.setA(obj.getString("A"));
            newQuestion.setB(obj.getString("B"));
            newQuestion.setC(obj.getString("C"));
            newQuestion.setD(obj.getString("D"));
            newQuestion.setCorrectAns(obj.getString("correctAns"));
            q.add(newQuestion);

            Log.d("obj", obj.toString());



        }
        Log.d("testy", testy.toString());
        String[] testnumber = new String[testy.size()];
        for (int i = 0; i < testy.size(); i++) {
            testnumber[i]="Test nr: "+testy.get(i).getIdTest();
        }
        ListView  listView=(ListView)findViewById(R.id.listTests);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(TestChooseActivity.this, LessonActivity.class);
                ArrayList<Question>  message = testy.get(position).getQuestions();
                intent.putParcelableArrayListExtra("questions",message);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, testnumber);

        //attaching adapter to listview
         listView.setAdapter(arrayAdapter);
    }

}