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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class todayOrder extends AppCompatActivity {

    private Button fromDate, menuplan, allOrder;
    private Spinner spinner;
    private TextView fromTxtView;
    private Context context = this;
    //private static final String [] items = {"Bitte Kunde auswählen"};

    ViewPager viewPager;
    Adapter adapter;
    List<Model> models;
    Integer [] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_today_order);

        //Menuplan cikaartttimmmmmmmmmmmmmmmmmm
        models = new ArrayList<>();
        models.add(new Model("Menüplan 1",  "15x Suppe",
                "20x Mittagsessen", "10x Nachspeise"));
        models.add(new Model("Menüplan 2",  "15x Suppe",
                "20x Mittagsessen", "10x Nachspeise"));
        models.add(new Model("Menüplan 3",  "15x Suppe",
                "20x Mittagsessen", "10x Nachspeise"));
        models.add(new Model("Menüplan 4",  "15x Suppe",
                "20x Mittagsessen", "10x Nachspeise"));




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


        fromDate = findViewById(R.id.fromDate);
        fromTxtView = findViewById(R.id.fromtvSelectedDate);

        menuplan = findViewById(R.id.menuplan);
        allOrder = findViewById(R.id.allOrder);

        /*
        //implementing spinner of customer
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(todayOrder.this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter); */


        //Calender for the first Button to select "Von"
        fromDate.setOnClickListener(new View.OnClickListener() {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);


            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(todayOrder.this,
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


        menuplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(todayOrder.this, MenuplanKitchen.class));
            }
        });

        allOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(todayOrder.this, allOrdersKitchen.class));
            }
        });
    }
}
