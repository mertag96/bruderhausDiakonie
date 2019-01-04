package com.example.firsttest.firsttest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class PopActivity extends AppCompatActivity {

    //dieser popup ist für das fenster 3 also um einen neuen benutzer zu registrieren
    private Button btnOk, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        btnOk= findViewById(R.id.ok);
        btnBack = findViewById(R.id.zuruck);

        //um pop up hintergrundfarbe style zu ändern unter /drawable/pop_bg.xml
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        //die nächste Zeile bestimmt durch die Zahlen wie groß das Popup Fenster sein soll, je kleiner die Zahl desto kleiner der Fenster
        getWindow().setLayout((int)(width*.8),(int)(height*.4));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y= -20;

        getWindow().setAttributes((params));


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (getApplicationContext(), Menunavigation.class);
                startActivity(i);
            }
        });


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast soll erst kommen, wenn daten erfolgreich gespeichert worden sind
                Toast.makeText(getApplicationContext(), "Benutzer wurde erfolgreich registriert", Toast.LENGTH_SHORT).show();

                //bei ok, sollte hier per SQL anweisung die daten in db eingespeichert werden

                Intent i = new Intent (getApplicationContext(), Menunavigation.class);
                startActivity(i);
            }
        });
    }
}
