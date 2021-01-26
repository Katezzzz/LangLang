package com.example.langlang;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CourseInfoActivity extends AppCompatActivity {
    String URL= "http://192.168.1.8/test-android/courses/SignForCourse.php";
    JSONParser jsonParser=new JSONParser();
    TextView name, teacher, data;
    Button sign;
   String idUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);
        sign=(Button)findViewById(R.id.signIN);
        name=(TextView) findViewById(R.id.name);
        teacher=(TextView) findViewById(R.id.teacher);
        data=(TextView) findViewById(R.id.data);
        Bundle extras = getIntent().getExtras();
        String idCourse=extras.getString("idCourse");
         idUser=extras.getString("idUser");
       name.setText(extras.getString("name"));
        teacher.setText(extras.getString("teacher"));
        data.setText(extras.getString("time"));

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CourseInfoActivity.AttemptAddCourse attemptLogin= new CourseInfoActivity.AttemptAddCourse();
                attemptLogin.execute(idCourse,idUser);


            }
        });


    }

    private class AttemptAddCourse extends AsyncTask<String, String, JSONObject> {

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override

        protected JSONObject doInBackground(String... args) {

            String idCourse = args[0];
            String idUser = args[1];


            ArrayList params = new ArrayList();
            params.add(new BasicNameValuePair("idCourse", idCourse));
            params.add(new BasicNameValuePair("idUser", idUser));

            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);


            return json;

        }

        protected void onPostExecute(JSONObject result) {

            // dismiss the dialog once product deleted
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            //Log.e("log_tag", result.toString());

            try {

                if (result != null) {



                    Toast.makeText(CourseInfoActivity.this,result.getString("message"),Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(CourseInfoActivity.this, CourseChooseActivity.class);
                        intent.putExtra("id", Integer.parseInt(idUser));
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                }
            } catch (JSONException e) {
                Log.e("log_tag", "Error parsing data "+e.toString());
                Log.e("log_tag", "Failed data was:\n" + result);
            }


        }

    }
}