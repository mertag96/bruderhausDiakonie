package com.example.firsttest.firsttest;


import android.support.v4.view.ViewPager;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    private Intent goToMenuNavi, goToAllOrders,goToMenuClient, goToMenuKitchen;
     Button butAnmelden, test;
    private TextView username, password;
    //private ProgressBar loading;
    private int permission, userID;
    private JSONArray userdata;
    private JSONObject drow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        butAnmelden= findViewById(R.id.buttonAnmelden2);
        username= findViewById((R.id.inputUsername));
        password= findViewById((R.id.inputPassword));

            //Navigating to menunavigation page after listen on anmelden button
            butAnmelden.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v){

                    //TODO: Get information from database about user and check if valid credentials entered & get permission and userID

                    //checking that username and password fields are filled before the redirecting can work
                    if(username.getText().toString().trim().equals("") || password.getText().toString().trim().equals("")) {
                        Toast.makeText(getApplicationContext(), "Bitte Benutzername und Passwort eingeben", Toast.LENGTH_SHORT).show();
                    }else{
                        login();
                    }
                }
            });
    }

    public void login() {
        NetworkHandler networkHelper = new NetworkHandler();
        JSONObject json = new JSONObject();
        try {
            json.put("username", username.getText());
            json.put("password", password.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        networkHelper.post("http://134.103.176.137:8081/login", json.toString(), new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }
            @Override
            public void onResponse(Response response) throws IOException {
                String responseStr = response.body().string();
                System.out.println("HALLOOO"+responseStr);
                if(responseStr.length()<=2){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Login fehlerhaft!", Toast.LENGTH_LONG).show();
                        }
                    });
                }else{
                    try {
                        userdata = new JSONArray(responseStr);
                        drow = userdata.getJSONObject(0);
                        //System.out.println("ROLE: "+drow.optString("ROLE"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(drow.optString("ROLE").equals("1")){
                                System.out.println("ROLE 1 eingeloggt");
                                goToMenuNavi = new Intent(MainActivity.this, Menunavigation.class);
                                goToMenuNavi.putExtra("userid",drow.optString("ID"));
                                goToMenuNavi.putExtra("username",drow.optString("USERNAME"));
                                startActivity(goToMenuNavi);
                            }else if(drow.optString("ROLE").equals("2")){
                                System.out.println("ROLE 2 eingeloggt");
                                goToMenuClient = new Intent(MainActivity.this, MenuplanClient.class);
                                goToMenuClient.putExtra("userid",drow.optString("ID"));
                                goToMenuClient.putExtra("username",drow.optString("USERNAME"));
                                startActivity(goToMenuClient);
                            }else{
                                System.out.println("ROLE 3 eingeloggt");
                                goToMenuKitchen = new Intent(MainActivity.this, MenuplanKitchen.class);
                                goToMenuKitchen.putExtra("userid",drow.optString("ID"));
                                goToMenuKitchen.putExtra("username",drow.optString("USERNAME"));
                                startActivity(goToMenuKitchen);
                            }

                        }
                    });
                }

            }
        });
    }
}