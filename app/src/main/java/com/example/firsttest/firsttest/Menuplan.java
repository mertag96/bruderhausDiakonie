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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Menuplan extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button fromDate, back, order;
    private Spinner spinner;
    private String userID, userName;
    private TextView fromTxtView;
    private Context context = this;
    private int tag, monat, jahr;
    //  private static final String firstItem = "Bitte Kunde auswählen";

    ViewPager viewPager;
    Adapter adapter;
    ArrayList models = null;
    Integer [] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    private JSONArray da;
    //private ArrayList<Menu> menues= new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menuplan);

        userID = getIntent().getExtras().get("userid").toString();
        userName = getIntent().getExtras().get("username").toString();

        models = new ArrayList<>();
        getMenu();

        fromDate = findViewById(R.id.fromDate);
        fromTxtView = findViewById(R.id.fromtvSelectedDate);

        //back = findViewById(R.id.zuruck);
        order = findViewById(R.id.bestellen);

        //implementing spinner of customer
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this); //

        List<String> categories = new ArrayList<String>();
        categories.add("Bitte auswählen");
        categories.add("Hier müssen die Usernamen dynamisch geladen werden");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Menuplan.this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


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
                                tag = day;
                                monat = month;
                                jahr = year;
                                fromTxtView.setText(day + "." + (month + 1) + "." + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }

        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                //set title
                alertDialogBuilder.setTitle("Hinweis");

                //set Dialog Message
                alertDialogBuilder.setMessage("Wenn Sie auf Bestellen klicken, wird Ihre Bestellung endgültig auf das System aufgegeben.").setCancelable(false)
                        .setPositiveButton("Bestellen", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                placeOrder();
                                //startActivity(new Intent(Menuplan.this, AllOrders.class));
                                //Toast.makeText(getApplicationContext(), "Ihre Bestellung wurde erfolgreich erfasst.", Toast.LENGTH_SHORT).show();
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



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        if (!(item.equals("Bitte auswählen"))) {
            Toast.makeText(parent.getContext(), "Ausgewählt: " + item, Toast.LENGTH_LONG).show();
        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        //To do autogenerated method stub
    }
    public void getMenu(){
        NetworkHandler networkHelper = new NetworkHandler();

        networkHelper.get("http://134.103.176.137:8081/foods", new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }
            @Override
            public void onResponse(Response response) throws IOException {
                String responseStr = response.body().string();
                //System.out.println("Aufbau Text = "+responseStr);
                try {
                    da = new JSONArray(responseStr);
                    System.out.println("TEST123"+da);

                    rebuildMenu(da);


                } catch (JSONException e) {
                    System.out.println(e);
                    e.printStackTrace();
                }
            }
        });
    }

    public void rebuildMenu(JSONArray data) throws JSONException {
       // ArrayList<Map>  maps = new ArrayList<>();

        //System.out.println("Aufbau Methode wird gestartet");
        System.out.println("Aufbaudaten " + data.toString());
        String[] ids= {"ID","DESCRIPTION","MENUID","FOODTYP","KW"};
        ArrayList<String> menu1 = new ArrayList<>();
        ArrayList<String> menu2 = new ArrayList<>();
        ArrayList<String> menu3 = new ArrayList<>();
        ArrayList<String> menu4 = new ArrayList<>();
        ArrayList<ArrayList<String>> menuestemp= new ArrayList<>();

        for(int i=0; i<data.length(); i++){
            JSONObject datarow = data.getJSONObject(i);
            switch(Integer.parseInt(datarow.optString(ids[2]))){
                case 1: switch(Integer.parseInt(datarow.optString(ids[3]))){
                    case 1: menu1.add(0,datarow.optString(ids[1]));break;
                    case 2: menu1.add(1,datarow.optString(ids[1]));break;
                    case 3: menu1.add(2,datarow.optString(ids[1]));break;
                } break;
                case 2: switch(Integer.parseInt(datarow.optString(ids[3]))){
                    case 1: menu2.add(0,datarow.optString(ids[1]));break;
                    case 2: menu2.add(1,datarow.optString(ids[1]));break;
                    case 3: menu2.add(2,datarow.optString(ids[1]));break;
                } break;
                case 3: switch(Integer.parseInt(datarow.optString(ids[3]))){
                    case 1: menu3.add(0,datarow.optString(ids[1]));break;
                    case 2: menu3.add(1,datarow.optString(ids[1]));break;
                    case 3: menu3.add(2,datarow.optString(ids[1]));break;
                } break;
                case 4: switch(Integer.parseInt(datarow.optString(ids[3]))){
                    case 1: menu4.add(0,datarow.optString(ids[1]));break;
                    case 2: menu4.add(1,datarow.optString(ids[1]));break;
                    case 3: menu4.add(2,datarow.optString(ids[1]));break;
                } break;
            }
        }
        menuestemp.add(menu1);
        menuestemp.add(menu2);
        menuestemp.add(menu3);
        menuestemp.add(menu4);
        for(int i=0;i<menuestemp.size();i++){
            models.add(new Model("Menüplan " + (i+1),  menuestemp.get(i).get(0),
                    menuestemp.get(i).get(1), menuestemp.get(i).get(2)));
        }
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

    }

    public void placeOrder() {
        NetworkHandler networkHelper = new NetworkHandler();
        int[] menu1,menu2,menu3,menu4;
        JSONObject json = new JSONObject();
        menu1 = adapter.getNumberPickerValue(0);
        menu2 = adapter.getNumberPickerValue(1);
        menu3 = adapter.getNumberPickerValue(2);
        menu4 = adapter.getNumberPickerValue(3);
       try {
           json.put("v1", menu1[0]);
           json.put("h1", menu1[1]);
           json.put("d1", menu1[2]);
           json.put("v2", menu2[0]);
           json.put("h2", menu2[1]);
           json.put("d2", menu2[2]);
           json.put("v3", menu3[0]);
           json.put("h3", menu3[1]);
           json.put("d3", menu3[2]);
           json.put("v4", menu4[0]);
           json.put("h4", menu4[1]);
           json.put("d4", menu4[2]);
           json.put("day",tag);
           json.put("month",monat);
           json.put("year",jahr);
           json.put("userid",Integer.parseInt(userID));
           json.put("orderfor", fromDate.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        networkHelper.post("http://134.103.176.137:8081/orderslist", json.toString(), new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }
            @Override
            public void onResponse(Response response) throws IOException {
                String responseStr = response.body().string();
                if(response.code()==200){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Bestellung erfolgreich!", Toast.LENGTH_LONG).show();
                        }
                    });
                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Bestellung fehlerhaft!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

    }
}


