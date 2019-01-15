package com.example.firsttest.firsttest;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import android.graphics.Color;
import android.os.AsyncTask;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableRow;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;


public class AllOrders extends AppCompatActivity {

    private Button fromDate, toDate, edit, back;
    private TextView fromTxtView, toTxtView;
    final Context context = this;
    private Spinner spinner;
    private static final String[] items = {"Bitte Kunde auswählen"};
    private TableLayout mTableLayout;
    ProgressDialog mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_orders);

        //Trying the show dynamic data in a tablelayout
        //Used classes: dimens.xml / activity_all_orders / invoiceData.java / Invoices.java
        mProgressBar = new ProgressDialog(this);
        // setup the table
        mTableLayout = (TableLayout) findViewById(R.id.tableInvoices);
        mTableLayout.setStretchAllColumns(true);
        startLoadData();

        //implementing spinner of customer
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AllOrders.this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

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
                //datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
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
                //datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
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
                alertDialogBuilder.setTitle("Hinweis");
                alertDialogBuilder.setMessage("Löschen?").setCancelable(false)
                        .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //ToDo: Implement to delete correctly and officialy
                                Toast.makeText(getApplicationContext(), "Erfolgreich gelöscht.", Toast.LENGTH_SHORT).show();
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

    //weitere Methoden zur Implementierung für die Tabelle
    public void startLoadData() {
        mProgressBar.setCancelable(false);
        mProgressBar.setMessage("Die Tabelle wird geladen..");
        mProgressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressBar.show();
        new LoadDataTask().execute(0);
    }
    public void loadData() {
        int leftRowMargin=0;
        int topRowMargin=0;
        int rightRowMargin=0;
        int bottomRowMargin = 0;
        int textSize = 0, smallTextSize =0, mediumTextSize = 0;
        textSize = (int) getResources().getDimension(R.dimen.font_size_verysmall);
        smallTextSize = (int) getResources().getDimension(R.dimen.font_size_small);
        mediumTextSize = (int) getResources().getDimension(R.dimen.font_size_medium);
        Invoices invoices = new Invoices();
        InvoiceData[] data = invoices.getInvoices();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        int rows = data.length;
        //getSupportActionBar().setTitle("Invoices (" + String.valueOf(rows) + ")");
        TextView textSpacer = null;
        mTableLayout.removeAllViews();
        // -1 means heading row
        for(int i = -1; i < rows; i ++) {
            InvoiceData row = null;
            if (i > -1)
            row = data[i];
            else {
                textSpacer = new TextView(this);
                textSpacer.setText("");
            }
            // data columns
            // erste Spalte oben header beschriftung: anmerkung: tv(i) bedeutet jede einzelne spalte oben am header
            final TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setGravity(Gravity.LEFT);
            tv.setPadding(5, 15, 0, 15);
            if (i == -1) {
                tv.setText("Nr.");
                tv.setBackgroundColor(Color.parseColor("#f0f0f0"));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumTextSize);
            } else {
                tv.setBackgroundColor(Color.parseColor("#f8f8f8"));
                tv.setText(String.valueOf(row.invoiceNr));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            }
            final TextView tv2 = new TextView(this);
            if (i == -1) {
                tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumTextSize);
            } else {
                tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            }

            tv2.setGravity(Gravity.LEFT);
            tv2.setPadding(5, 15, 0, 15);
            if (i == -1) {
                tv2.setText("Menüplan");
                tv2.setBackgroundColor(Color.parseColor("#f7f7f7"));
            }else {
                tv2.setBackgroundColor(Color.parseColor("#ffffff"));
                tv2.setTextColor(Color.parseColor("#000000"));
                tv2.setText(row.mealNr);
            }
            final LinearLayout layCustomer = new LinearLayout(this);
            layCustomer.setOrientation(LinearLayout.VERTICAL);
            layCustomer.setPadding(0, 10, 0, 10);
            layCustomer.setBackgroundColor(Color.parseColor("#f8f8f8"));
            final TextView tv3 = new TextView(this);
            if (i == -1) {
                tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tv3.setPadding(5, 5, 0, 5);
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumTextSize);
            } else {
                tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tv3.setPadding(5, 0, 0, 5);
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            }
            tv3.setGravity(Gravity.TOP);
            if (i == -1) {
                tv3.setText("Kundenname");
                tv3.setBackgroundColor(Color.parseColor("#f0f0f0"));
            } else {
                tv3.setBackgroundColor(Color.parseColor("#f8f8f8"));
                tv3.setTextColor(Color.parseColor("#000000"));
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                tv3.setText(row.name); //bezogen auf die Var in klasse InvoiceData
            }
            layCustomer.addView(tv3);
/*
            if (i > -1) {
                final TextView tv3b = new TextView(this);
                tv3b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv3b.setGravity(Gravity.RIGHT);
                tv3b.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                tv3b.setPadding(5, 1, 0, 5);
                tv3b.setTextColor(Color.parseColor("#aaaaaa"));
                tv3b.setBackgroundColor(Color.parseColor("#f8f8f8"));
                tv3b.setText(row.customerAddress);
                layCustomer.addView(tv3b);
            } */
            final LinearLayout layAmounts = new LinearLayout(this);
            layAmounts.setOrientation(LinearLayout.VERTICAL);
            layAmounts.setGravity(Gravity.RIGHT);
            layAmounts.setPadding(0, 10, 0, 10);
            layAmounts.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT));
            final TextView tv4 = new TextView(this);
            if (i == -1) {
                tv4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tv4.setPadding(5, 5, 1, 5);
                layAmounts.setBackgroundColor(Color.parseColor("#f7f7f7"));
            } else {
                tv4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv4.setPadding(5, 0, 1, 5);
                layAmounts.setBackgroundColor(Color.parseColor("#ffffff"));
            }
            tv4.setGravity(Gravity.RIGHT);
            if (i == -1) {
                tv4.setText("Bestelldatum");
                tv4.setBackgroundColor(Color.parseColor("#f7f7f7"));
                tv4.setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumTextSize);
            } else {
                tv4.setBackgroundColor(Color.parseColor("#ffffff"));
                tv4.setTextColor(Color.parseColor("#000000"));
                tv4.setText(dateFormat.format(row.date));
                tv4.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            }
            layAmounts.addView(tv4);

           /* if (i > -1) {

                final TextView tv4b = new TextView(this);

                tv4b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));

                tv4b.setGravity(Gravity.RIGHT);

                tv4b.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                tv4b.setPadding(2, 2, 1, 5);

                tv4b.setTextColor(Color.parseColor("#00afff"));

                tv4b.setBackgroundColor(Color.parseColor("#ffffff"));

                String due = "";

                if (row.amountDue.compareTo(new BigDecimal(0.01)) == 1) {

                    due = "Due:" + decimalFormat.format(row.amountDue);

                    due = due.trim();

                }

                tv4b.setText(due);

                layAmounts.addView(tv4b);

            } */
            // add table row
            final TableRow tr = new TableRow(this);
            tr.setId(i + 1);
            TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT);
            trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
            tr.setPadding(0,0,0,0);
            tr.setLayoutParams(trParams);
            tr.addView(tv);
            tr.addView(tv2);
            tr.addView(layCustomer);
            tr.addView(layAmounts);



            if (i > -1) {
                // ich glaub hier kann man sagen wegen tabellen zeile anklickbar und das löschen implementieren
                tr.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        InvoiceData rows = new InvoiceData();
                        final TableRow tr = (TableRow) v;
                        System.out.println("Zeilenr:" + tr.getId() + "Kundenname: " + tv3.getText());

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                        alertDialogBuilder.setTitle("Hinweis");
                        alertDialogBuilder.setMessage("Möchten Sie die Bestellung endgültig löschen?").setCancelable(false)
                                .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mTableLayout.removeView(tr); // Zeile beim anklicken löschen
                                        //ToDo=hier muss dann auch dementsprechend auch die Bestellung in db gelöscht werden und hier dann wieder aktualisiert angezeigt werden
                                        Toast.makeText(AllOrders.this, "Die Bestellung wurde gelöscht", Toast.LENGTH_SHORT).show();
                                        //startActivity(new Intent(AllOrders.this, Menunavigation.class));
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

            mTableLayout.addView(tr, trParams);

            if (i > -1) {

                // add separator row
                final TableRow trSep = new TableRow(this);
                TableLayout.LayoutParams trParamsSep = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT);
                trParamsSep.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
                trSep.setLayoutParams(trParamsSep);
                TextView tvSep = new TextView(this);
                TableRow.LayoutParams tvSepLay = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT);
                tvSepLay.span = 4; //??
                tvSep.setLayoutParams(tvSepLay);
                tvSep.setBackgroundColor(Color.parseColor("#d9d9d9"));
                tvSep.setHeight(2);
                trSep.addView(tvSep);
                mTableLayout.addView(trSep, trParamsSep);

            }
        }
    }
        // The params are dummy and not used
    class LoadDataTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected String doInBackground(Integer... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Fertig..";
        }

        @Override
        protected void onPostExecute(String result) {
            mProgressBar.hide();
            loadData();
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
        }
    }
}