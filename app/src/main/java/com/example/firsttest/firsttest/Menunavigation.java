package com.example.firsttest.firsttest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class Menunavigation extends AppCompatActivity{
       ImageView newUser, allUser, menuplan, allOrders;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_menunavigation);


        newUser= findViewById((R.id.neuerBenutzer));
        allUser = findViewById(R.id.alleUser);
        menuplan = findViewById(R.id.menuplan);
        allOrders = findViewById(R.id.allorders);


        //Navigating to 4 different activities
        newUser.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v){
        startActivity(new Intent(Menunavigation.this, RegisterActivity.class));
        }
        });


       allUser.setOnClickListener(new View.OnClickListener(){
               @Override
               public void onClick(View v){
                      startActivity(new Intent(Menunavigation.this, alluser.class));
                }
        });

        menuplan.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v){
            startActivity(new Intent(Menunavigation.this, Menuplan.class));
        }
          });

         allOrders.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v){
            startActivity(new Intent(Menunavigation.this, AllOrders.class));
             }
    });
}
}