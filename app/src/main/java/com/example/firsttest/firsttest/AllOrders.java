package com.example.firsttest.firsttest;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AllOrders extends AppCompatActivity {

    private Button fromDate, toDate, edit, back;
    private TextView fromTxtView, toTxtView;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_orders);

        fromDate = findViewById(R.id.fromDate);
        fromTxtView = findViewById(R.id.fromtvSelectedDate);

        toDate = findViewById(R.id.toDate);
        toTxtView = findViewById(R.id.totvSelectedDate);
        edit = findViewById(R.id.bearbeiten);
        back = findViewById(R.id.zuruck);

        //Calender for the first Button to select "Von"
        fromDate.setOnClickListener(new View.OnClickListener() {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AllOrders.this,
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

        //Calender for the Second Button to select "Bis"
        toDate.setOnClickListener(new View.OnClickListener() {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AllOrders.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                toTxtView.setText(day + "." + (month + 1) + "." + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        //back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllOrders.this, Menunavigation.class));
            }
        });

        //edit button
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                //set title
                alertDialogBuilder.setTitle("Hinweis");

                //set Dialog Message
                alertDialogBuilder.setMessage("Wie möchten Sie vorgehen?").setCancelable(false)
                        .setPositiveButton("Löschen", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //if this button is clicked, close currect activity

                                //ToDo: Implement to delete correctly and officialy
                                Toast.makeText(getApplicationContext(), "Erfolgreich gelöscht.", Toast.LENGTH_SHORT).show();
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
