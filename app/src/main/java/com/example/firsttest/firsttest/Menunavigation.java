package com.example.firsttest.firsttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
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

       //  allOrders.setOnClickListener(new View.OnClickListener(){
    //    @Override
      //  public void onClick(View v){
       //     startActivity(new Intent(Menunavigation.this, alluser.class));
       //       }
  //  });
}
}