package com.example.firsttest.firsttest;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Menuplan extends AppCompatActivity {

    private Button fromDate, toDate, back, order;
    private TextView fromTxtView, toTxtView;
    private Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuplan);

        fromDate = findViewById(R.id.fromDate);
        fromTxtView = findViewById(R.id.fromtvSelectedDate);

        toDate = findViewById(R.id.toDate);
        toTxtView = findViewById(R.id.totvSelectedDate);

        back = findViewById(R.id.zuruck);
        order = findViewById(R.id.bestellen);

        //Calender for the first Button to select "Von"
        fromDate.setOnClickListener(new View.OnClickListener() {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Menuplan.this,
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(Menuplan.this,
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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menuplan.this, Menunavigation.class));
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                View mView = getLayoutInflater().inflate(R.layout.activity_search_customer_popup, null);

                //set title
                alertDialogBuilder.setTitle("Hinweis");

                final Spinner mSpinner = mView.findViewById(R.id.spinner);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Menuplan.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.customers));
                //ToDo Binding the Customer list instead of the dummy values
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner.setAdapter(adapter);

                alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!mSpinner.getSelectedItem().toString().equalsIgnoreCase("Bitte wählen Sie eine Option aus...")) {
                            Toast.makeText(Menuplan.this, mSpinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });

                alertDialogBuilder.setNegativeButton("Zurück", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alertDialogBuilder.setView(mView);
                AlertDialog dialog = alertDialogBuilder.create();
                dialog.show();

            }
        });
    }}


                /*
                //set Dialog Message
                alertDialogBuilder.setMessage("Wählen Sie bitte den Kunden aus.").setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //if this button is clicked, close currect activity
                                startActivity(new Intent(Menuplan.this, searchCustomerPopup.class));
                            }
                        }).setNegativeButton("Zurück", new DialogInterface.OnClickListener() {
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
} */