package com.example.langlang;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {


    EditText editEmail, editPassword, editName,editUserName,editLastName;
    Button btnSignIn, btnRegister;

    String URL= "http://192.168.1.20/test-android/index.php";

    JSONParser jsonParser=new JSONParser();

    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editPassword=(EditText)findViewById(R.id.editPassword);
        editEmail=(EditText)findViewById(R.id.editEmail);
        editUserName=(EditText)findViewById(R.id.editUserName);
        editName=(EditText)findViewById(R.id.editName);
        editLastName=(EditText)findViewById(R.id.editLastName);
        btnSignIn=(Button)findViewById(R.id.btnSignIn);
        btnRegister=(Button)findViewById(R.id.btnRegister);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AttemptLogin attemptLogin= new AttemptLogin();
                attemptLogin.execute(editUserName.getText().toString(),editPassword.getText().toString(),"","","");


            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(i==0)
                {
                    i=1;
                    editEmail.setVisibility(View.VISIBLE);
                    editName.setVisibility(View.VISIBLE);
                    editLastName.setVisibility(View.VISIBLE);
                    btnSignIn.setVisibility(View.GONE);
                    btnRegister.setText("Stwórz Konto");
                }
                else{

                    btnRegister.setText("Zarejestruj");
                    editEmail.setVisibility(View.GONE);
                    editName.setVisibility(View.GONE);
                    editLastName.setVisibility(View.GONE);
                    btnSignIn.setVisibility(View.VISIBLE);
                    i=0;

                    AttemptLogin attemptLogin= new AttemptLogin();
                    attemptLogin.execute(editUserName.getText().toString(),editPassword.getText().toString(),editEmail.getText().toString(),
                            editName.getText().toString(),editLastName.getText().toString());

                }

            }
        });


    }

    private class AttemptLogin extends AsyncTask<String, String, JSONObject> {

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override

        protected JSONObject doInBackground(String... args) {

            String lastName = args[4];
            String name = args[3];
            String email = args[2];
            String password = args[1];
            String userName= args[0];

            ArrayList params = new ArrayList();
            params.add(new BasicNameValuePair("userName", userName));
            params.add(new BasicNameValuePair("password", password));
            if(email.length()>0)
                params.add(new BasicNameValuePair("email",email));

            if(name.length()>0)
                params.add(new BasicNameValuePair("name",name));
            if(lastName.length()>0)
                params.add(new BasicNameValuePair("lastName",lastName));

            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);


            return json;

        }

        protected void onPostExecute(JSONObject result) {

            // dismiss the dialog once product deleted
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            //Log.e("log_tag", result.toString());

            try {

                if (result != null) {
                    Toast.makeText(getApplicationContext(),result.getString("message"),Toast.LENGTH_LONG).show();
                    Log.d("API123",result.getString("success"));
int value=Integer.parseInt(result.getString("success"));
                    if(value==1){
                        Intent intent = new Intent(getApplicationContext(), Panel.class);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                Log.e("log_tag", "Error parsing data "+e.toString());
                Log.e("log_tag", "Failed data was:\n" + result);
            }


        }

    }
}