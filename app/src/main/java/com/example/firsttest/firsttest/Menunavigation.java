package com.example.firsttest.firsttest;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Menunavigation extends AppCompatActivity {
    ImageView newUser, allUser, menuplan, allOrders;
    String userID, userName;
    Intent goToRegister, goToAllUser, goToMenuPlan, goToAllOrders;
    ImageButton imgB;
    Button logVisible;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_menunavigation);


        newUser = findViewById((R.id.neuerBenutzer));
        allUser = findViewById(R.id.alleUser);
        menuplan = findViewById(R.id.menuplan);
        allOrders = findViewById(R.id.allorders);
        //imgB = findViewById(R.id.logoutButton);
        logVisible = findViewById(R.id.logVisible);
        logVisible.setVisibility(View.GONE);

        userID = getIntent().getExtras().get("userid").toString();
        userName = getIntent().getExtras().get("username").toString();


        //Navigating to 4 different activities
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegister = new Intent(Menunavigation.this, RegisterActivity.class);
                goToRegister.putExtra("userid",userID);
                goToRegister.putExtra("username",userName);
                startActivity(goToRegister);
            }
        });

        allUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAllUser = new Intent(Menunavigation.this, alluser.class);
                goToAllUser.putExtra("userid",userID);
                goToAllUser.putExtra("username",userName);
                startActivity(goToAllUser);
            }
        });

        menuplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMenuPlan = new Intent(Menunavigation.this, Menuplan.class);
                goToMenuPlan.putExtra("userid",userID);
                goToMenuPlan.putExtra("username",userName);
                startActivity(goToMenuPlan);
            }
        });

        allOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAllOrders = new Intent(Menunavigation.this, AllOrders.class);
                goToAllOrders.putExtra("userid",userID);
                goToAllOrders.putExtra("username",userName);
                startActivity(goToAllOrders);
            }
        });

        logVisible = findViewById(R.id.logVisible);
        logVisible.setVisibility(View.GONE);
        //implement of the threedotsimageclick. If the threedots will be clicked, then the logout button comes out- or the opposite
        /*imgB.setOnClickListener(new View.OnClickListener() {
            //ToDo = The functionality of the logout button missed
            @Override
            public void onClick(View v) {
                logVisible = findViewById(R.id.logVisible);
                if (logVisible.getVisibility() == View.VISIBLE) {
                    logVisible.setVisibility(View.GONE);
                } else {
                    logVisible.setVisibility(View.VISIBLE);

                    logVisible.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                            //set title
                            alertDialogBuilder.setTitle("Hinweis");

                            //set Dialog Message
                            alertDialogBuilder.setMessage("Möchten Sie sich ausloggen?").setCancelable(false)
                                    .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //if this button is clicked, close currect activity
                                            //ToDo: implementieren, dass die eingegebenen Daten dann tatsächlich gespeichert werden
                                            startActivity(new Intent(Menunavigation.this, MainActivity.class));
                                            Toast.makeText(getApplicationContext(), "Benutzer wurde erfolgreich abgemeldet", Toast.LENGTH_SHORT).show();
                                        }
                                    }).setNegativeButton("Nein", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //if this button is clicked, just close the dialog box and do nothing
                                    dialog.cancel();
                                }
                            });

                            //create alert dialog
                            AlertDialog alertDialog = alertDialogBuilder.create();

                            //show it
                            alertDialog.show();
                        }
                    });

                }
            }


        });*/
    }
}
