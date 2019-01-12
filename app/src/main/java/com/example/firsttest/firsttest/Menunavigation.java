package com.example.firsttest.firsttest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Menunavigation extends AppCompatActivity {
    ImageView newUser, allUser, menuplan, allOrders;
    ImageButton imgB;
    Button logVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_menunavigation);


        newUser = findViewById((R.id.neuerBenutzer));
        allUser = findViewById(R.id.alleUser);
        menuplan = findViewById(R.id.menuplan);
        allOrders = findViewById(R.id.allorders);
        imgB = findViewById(R.id.logoutButton);
        logVisible = findViewById(R.id.logVisible);
        logVisible.setVisibility(View.GONE);


        //Navigating to 4 different activities
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menunavigation.this, RegisterActivity.class));
            }
        });


        allUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menunavigation.this, alluser.class));
            }
        });

        menuplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menunavigation.this, Menuplan.class));
            }
        });

        allOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menunavigation.this, AllOrders.class));
            }
        });

        logVisible = findViewById(R.id.logVisible);
        logVisible.setVisibility(View.GONE);
        //implement of the threedotsimageclick. If the threedots will be clicked, then the logout button comes out- or the opposite
        imgB.setOnClickListener(new View.OnClickListener() {
            //ToDo = The functionality of the logout button missed
            @Override
            public void onClick(View v) {
                logVisible = findViewById(R.id.logVisible);
                if (logVisible.getVisibility() == View.VISIBLE) {
                    logVisible.setVisibility(View.GONE);
                } else {
                    logVisible.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
