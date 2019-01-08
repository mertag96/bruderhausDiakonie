package com.example.firsttest.firsttest;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private Spinner spinner;
    private static final String [] items = {"Diakonie Kunde", "Küchenmitarbeiter"};
    private Button register, back;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        spinner = (Spinner)findViewById(R.id.dropdownperm);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_item, items);

        register = findViewById(R.id.buttonRegistrieren2);
        back = findViewById(R.id.zuruck);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      //  spinner.setAdapter(adapter);
      //  spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, Menunavigation.class));
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                //set title
                alertDialogBuilder.setTitle("Hinweis");

                //set Dialog Message
                alertDialogBuilder.setMessage("Möchten Sie die Daten endgültig speichern?").setCancelable(false)
                        .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //if this button is clicked, close currect activity
                                //ToDo: implementieren, dass die eingegebenen Daten dann tatsächlich gespeichert werden
                                startActivity(new Intent(RegisterActivity.this, Menunavigation.class));
                                Toast.makeText(getApplicationContext(), "Benutzer wurde erfolgreich registriert", Toast.LENGTH_SHORT).show();
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