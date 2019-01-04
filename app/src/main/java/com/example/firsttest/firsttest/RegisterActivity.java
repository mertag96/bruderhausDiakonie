package com.example.firsttest.firsttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class RegisterActivity extends AppCompatActivity {

    private Spinner spinner;
    private static final String [] items = {"Diakonie Kunde", "Küchenmitarbeiter"};
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        spinner = (Spinner)findViewById(R.id.dropdownperm);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_item, items);

        register = findViewById(R.id.buttonRegistrieren2);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      //  spinner.setAdapter(adapter);
      //  spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (getApplicationContext(), PopActivity.class);
                startActivity(i);
            }
        });
    }




}
