package com.example.firsttest.firsttest;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.TextView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private Button register, back;
    String userID, userName;
    Intent goToMenuNav;
    final Context context = this;
    private String item;
    private TextView vorname, nachname, strasse, hausnummer, zusatzadresse, plz, ort, telefonnummer, loginUsername, loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);


        vorname= findViewById((R.id.vorname));
        nachname= findViewById((R.id.nachname));
        strasse= findViewById((R.id.strasse));
        hausnummer= findViewById((R.id.hausnummer));
        zusatzadresse= findViewById((R.id.zusatzadresse));
        plz= findViewById((R.id.plz));
        ort= findViewById((R.id.Ort));
        telefonnummer= findViewById((R.id.telefonnummer));
        loginUsername= findViewById((R.id.loginUsername));
        loginPassword= findViewById((R.id.loginPassword));

        userID = getIntent().getExtras().get("userid").toString();
        userName = getIntent().getExtras().get("username").toString();


        //spinner
        spinner = findViewById(R.id.spinner1);
        spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("Bitte auswählen");
        categories.add("Service");
        categories.add("Küche");
        categories.add("Kunde");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        register = findViewById(R.id.buttonRegistrieren2);
        back = findViewById(R.id.zuruck);

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
                alertDialogBuilder.setTitle("Hinweis");
                alertDialogBuilder.setMessage("Möchten Sie die Daten endgültig speichern?").setCancelable(false)
                        .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //if this button is clicked, close currect activity
                                if(vorname.getText().toString().trim().equals("") || nachname.getText().toString().trim().equals("")
                                        || strasse.getText().toString().trim().equals("") || hausnummer.getText().toString().trim().equals("")
                                        || plz.getText().toString().trim().equals("")
                                        || ort.getText().toString().trim().equals("") || telefonnummer.getText().toString().trim().equals("")
                                        || loginPassword.getText().toString().trim().equals("") || loginUsername.getText().toString().trim().equals("")){
                                    Toast.makeText(getApplicationContext(), "Bitte füllen Sie alle Felder aus!", Toast.LENGTH_SHORT).show();
                                }else{
                                    register();
                                }
                                //startActivity(new Intent(RegisterActivity.this, Menunavigation.class));
                                //Toast.makeText(getApplicationContext(), "Benutzer wurde erfolgreich registriert", Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton("Nein", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    @Override

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Ausgewählt: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        //To do autogenerated method stub
    }


    public void register() {
        NetworkHandler networkHelper = new NetworkHandler();
        JSONObject json = new JSONObject();
        try {
            json.put("vorname", vorname.getText());
            json.put("nachname", nachname.getText());
            json.put("strasse", strasse.getText());
            json.put("hausnummer", hausnummer.getText());
            json.put("zusatzadresse", zusatzadresse.getText());
            json.put("plz", plz.getText());
            json.put("ort", ort.getText());
            json.put("telefonnummer", telefonnummer.getText());
            json.put("username", loginUsername.getText());
            json.put("password", loginPassword.getText());
            json.put("role", item);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        networkHelper.post("http://134.103.176.137:8081/users", json.toString(), new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }
            @Override
            public void onResponse(Response response) throws IOException {
                String responseStr = response.body().string();
                System.out.println("HALLOOO"+response.code());

                if(response.code()==200){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Registrierung erfolgreich!", Toast.LENGTH_LONG).show();
                            goToMenuNav = new Intent(RegisterActivity.this, Menunavigation.class);
                            goToMenuNav.putExtra("userid",userID);
                            goToMenuNav.putExtra("username",userName);
                            startActivity(goToMenuNav);
                        }
                    });
                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Registrierung fehlerhaft!", Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        });
    }

}