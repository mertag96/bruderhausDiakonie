package com.example.firsttest.firsttest;


import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Intent goToMenuNavi, goToAllOrders;
     Button butAnmelden, test;
    private TextView username, password;
    private ProgressBar loading;
    private int permission, userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        butAnmelden= findViewById(R.id.buttonAnmelden2);
        username= findViewById((R.id.inputUsername));
        password= findViewById((R.id.inputPassword));
        test = findViewById(R.id.test);

        test.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, alluser.class));
            }
        });



            //Navigating to menunavigation page after listen on anmelden button
            butAnmelden.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v){

                    permission = 1;
                    //TODO: Get information from database about user and check if valid credentials entered & get permission and userID

                    //checking that username and password fields are filled before the redirecting can work
                    if(username.getText().toString().trim().equals("") || password.getText().toString().trim().equals("")) {

                        Toast.makeText(getApplicationContext(), "Bitte Benutzername und Passwort eingeben", Toast.LENGTH_SHORT).show();


                    }else if(username.getText().toString().trim().equals("k1")){
                        startActivity(new Intent(MainActivity.this, MenuplanKitchen.class));

                    }
                    else{
                        //Loading Panel wird hier erst angezeigt, bis es zur nächsten Activity übergegangen wird
                        if(permission == 1){
                            goToMenuNavi = new Intent(MainActivity.this, Menunavigation.class);
                            goToMenuNavi.putExtra("permission",permission);

                            //username mitgeben an AllOrders kp aber ob das klappt
                            String putusername = username.getText().toString();

                            goToAllOrders = new Intent(MainActivity.this, AllOrders.class);
                            goToAllOrders.putExtra("Username", putusername);

                           // goToMenuNavi.putExtra("userID",userID);
                          //  loading.setVisibility(View.VISIBLE);
                            startActivity(goToMenuNavi);
                          //  loading.setVisibility(View.INVISIBLE);
                        }
                        else{

                            //go to menuplan
                            //add permission as extras to intent
                        }
                    }
            }});
    }
}