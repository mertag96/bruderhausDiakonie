package com.example.firsttest.firsttest;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button butAnmelden;
    private TextView username, password;
    private ProgressBar loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //loading panel zuerst hier auf unsichtbar machen BUG: wenn man von der zweiten activity wieder mit zurück button zurück geht, dann lädt der panel permanent


        butAnmelden= findViewById(R.id.buttonAnmelden2);
        username= findViewById((R.id.inputUsername));
        password= findViewById((R.id.inputPassword));
        loading= findViewById(R.id.loadingPanel);

        loading.setVisibility(View.GONE);


            //Navigating to menunavigation page after listen on anmelden button
            butAnmelden.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v){

                    //checking that username and password fields are filled before the redirecting can work
                    if(username.getText().toString().trim().equals("") || password.getText().toString().trim().equals("")) {

                       Toast.makeText(getApplicationContext(), "Bitte Benutzername und Passwort eingeben", Toast.LENGTH_SHORT).show();
                    }else{
                        //Loading Panel wird hier erst angezeigt, bis es zur nächsten Activity übergegangen wird
                        loading.setVisibility(View.VISIBLE);

                        startActivity(new Intent(MainActivity.this, Menunavigation.class));

                }
            }});



    }
}