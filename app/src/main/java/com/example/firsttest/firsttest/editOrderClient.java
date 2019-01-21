package com.example.firsttest.firsttest;

import android.animation.ArgbEvaluator;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class editOrderClient extends AppCompatActivity {

    private Button backToOrders, saveChanges;
    private Spinner spinner;
    private TextView orderDate;
    private Context context = this;
    private static final String [] items = {"Bitte Kunde auswählen"};

    ViewPager viewPager;
    Adapter adapter;
    List<Model> models;
    Integer [] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_order_client);

        //Menuplan
        models = new ArrayList<>();
        models.add(new Model("Menüplan 1",  " Selleriecremesuppe",
                " Putenschnitzel \n Bratensoße \n Feine Nudeln \n Gemischter Salat", " Latte- Machhiatopudding"));
        models.add(new Model("Menüplan 2",  " Selleriecremesuppe",
                " Bunte Gemüßeplatte \n Soße Hollondaise \n Eieromlette \n Salzkartoffel", " Latte- Machhiatopudding"));
        models.add(new Model("Menüplan 3",  " Selleriecremesuppe",
                " Nudel-Gemüseauflauf \n mit Schinken \n Kräutersoße \n Gemischter Salat", " Latte- Machhiatopudding"));
        models.add(new Model("Menüplan 4",  " Selleriecremesuppe",
                " Nudel-Gemüseauflauf \n mit Schinken \n Kräutersoße \n Gemischter Salat", " Latte- Machhiatopudding"));


        adapter = new Adapter(models, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130,0,130,0);

        Integer [] colors_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4)
        };

        colors = colors_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position < (adapter.getCount()- 1)&& position <(colors.length -1)){
                    viewPager.setBackgroundColor(
                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]));

                }else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        orderDate = findViewById(R.id.orderDate);
        //Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(" EEE  dd.MM.yyyy");
        String currentDate = sdf.format(new Date());
        orderDate.setText("Lieferdatum: " + currentDate);

        saveChanges = findViewById(R.id.saveChanges);
        backToOrders = findViewById(R.id.backToOrders);

        //implementing spinner of customer
        spinner = findViewById(R.id.spinner);
        /*ArrayAdapter<String>adapter = new ArrayAdapter<String>(editOrderClient.this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        Calender for the first Button to select "Von"
        fromDate.setOnClickListener(new View.OnClickListener() {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(editOrderClient.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                fromTxtView.setText(day + "." + (month + 1) + "." + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }

        });*/




        backToOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                //set title
                alertDialogBuilder.setTitle("Veränderungen gehen verloren!");

                //set Dialog Message
                alertDialogBuilder.setMessage("Möchten Sie die Seite wirklich verlassen?").setCancelable(true)
                        .setPositiveButton("Nein", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //if this button is clicked, just close the dialog box and do nothing
                                dialog.cancel();
                            }
                        }).setNegativeButton("Ja", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if this button is clicked, close currect activity
                        startActivity(new Intent(editOrderClient.this, MyOrdersClient.class));
                    }
                });

                //create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                //show it
                alertDialog.show();
            }
        });

        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                //set title
                alertDialogBuilder.setTitle("Veränderungen speichern? ");

                //set Dialog Message
                alertDialogBuilder.setMessage("Wenn Sie auf speichern klicken, werden ihre Veränderungen zur Bestellung hinzugefügt.").setCancelable(false)
                        .setPositiveButton("Speichern", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //if this button is clicked, close currect activity
                                //ToDo: implementieren, dass die eingegebenen Daten dann tatsächlich gespeichert werden
                                startActivity(new Intent(editOrderClient.this, MyOrdersClient.class));
                                Toast.makeText(getApplicationContext(), "Ihre Bestellung wurde erfolgreich abgespeichert.", Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
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



    } }


