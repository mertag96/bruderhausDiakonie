package com.example.firsttest.firsttest;

import android.animation.ArgbEvaluator;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddMenu extends AppCompatActivity {

    private Button fromDate, cancel, save;

    private TextView fromTxtView, t1, t2,t3, t4;
    private Context context = this;

    private EditText  vM1, hM1, nM1;
    private EditText vM2, hM2, nM2;
    private EditText vM3, hM3, nM3;
    private EditText vM4, hM4, nM4;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_menu);

        t1 = findViewById(R.id.txtMenu1);
        t2 = findViewById(R.id.txtMenu2);
        t3 = findViewById(R.id.txtMenu3);
        t4 = findViewById(R.id.txtMenu4);

        vM1 = findViewById(R.id.vM1);
        hM1 = findViewById(R.id.hM1);
        nM1 = findViewById(R.id.nM1);

        vM2 = findViewById(R.id.vM2);
        hM2 = findViewById(R.id.hM2);
        nM2 = findViewById(R.id.nM2);

        vM3 = findViewById(R.id.vM3);
        hM3 = findViewById(R.id.hM3);
        nM3 = findViewById(R.id.nM3);

        vM4 = findViewById(R.id.vM4);
        hM4 = findViewById(R.id.hM4);
        nM4 = findViewById(R.id.nM4);

        fromDate = findViewById(R.id.fromDate);
        fromTxtView = findViewById(R.id.fromtvSelectedDate);

        cancel = findViewById(R.id.AddMenu);
        save = findViewById(R.id.bestellen);

        //Calender for the first Button to select "Von"
        fromDate.setOnClickListener(new View.OnClickListener() {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddMenu.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                fromTxtView.setText(day + "." + (month + 1) + "." + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Hinweis");
                alertDialogBuilder.setMessage("Änderungen gehen verloren.").setCancelable(false)
                        .setPositiveButton("Verlassen", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(AddMenu.this, MenuplanKitchen.class));
                            }
                        }).setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Hinweis");
                alertDialogBuilder.setMessage("Möchten Sie Ihre Eingaben speichern?").setCancelable(false)
                        .setPositiveButton("Speichern", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(AddMenu.this, MenuplanKitchen.class));
                                Toast.makeText(getApplicationContext(), "Ihre Eingaben wurden erfolgreich erfasst.", Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton("Später", new DialogInterface.OnClickListener() {
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
}
