package com.example.firsttest.firsttest;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Date;

public class MyOrdersClient extends AppCompatActivity implements checkboxInterface {

    private Button fromDate, toDate, newOrder, cancelSelected;
    private FloatingActionButton floatingPinkButton;
    private TextView fromTxtView, toTxtView;
    final Context context = this;

    private String[]dateArray = {"datum1","datum2","datum3","datum4","datum5"};
    private String[]menuArray= {"Menü 2","Menü 3","Menü 4","Menü3","Menü 1"};
    private String[]appetizerArray = {"1x   Selleriecremesuppe","","3x    Selleriecremesuppe","1x   Selleriecremesuppe",""};
    private String[]mainCourseArray= {  "1x Putenschnitzel \n" + "Bratensoße \n" + "Feine Nudeln \n" + "Gemischter Salat",
                                        "2x Putenschnitzel \n" + "Bratensoße \n" + "Feine Nudeln \n" + "Gemischter Salat",
                                        "",
                                        "1x Putenschnitzel \n" + "Bratensoße \n" + "Feine Nudeln \n" + "Gemischter Salat",
                                        "",};
    private String[]dessertArray= {"1x  Latte-Machhiatopudding","","","2x   Latte-Machhiatopudding","1x Latte-Machhiatopudding"};

    private ListView orderListView;
    private customAdapterForCheckbox customAdapterForCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders_client);

        orderListView = (ListView) findViewById(R.id.order_list);
        customAdapterForCheckbox = new customAdapterForCheckbox(this);
        orderListView.setAdapter(customAdapterForCheckbox);

        fromDate = findViewById(R.id.fromDate);
        fromTxtView = findViewById(R.id.fromtvSelectedDate);
        SimpleDateFormat sdf = new SimpleDateFormat(" EEE  dd.MM.yyyy");
        Calendar calendar = Calendar.getInstance();
        final Date today = calendar.getTime();
        fromTxtView.setText(sdf.format(today));

        toDate = findViewById(R.id.toDate);
        toTxtView = findViewById(R.id.totvSelectedDate);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        final Date tomorrow = calendar.getTime();
        toTxtView.setText(sdf.format(tomorrow));

        floatingPinkButton = findViewById(R.id.floatingActionButton);

        newOrder = findViewById(R.id.myOrdersBtn);
        cancelSelected = findViewById(R.id.loeschenBtn);
        cancelSelected.setEnabled(false);

        //Calender for the first Button to select "Von"
        fromDate.setOnClickListener(new View.OnClickListener() {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MyOrdersClient.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                fromTxtView.setText(day + "." + (month + 1) + "." + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(today.getTime());
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(MyOrdersClient.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                toTxtView.setText(day + "." + (month + 1) + "." + year);
                            }
                        }, year, month, dayOfMonth+1);
                datePickerDialog.getDatePicker().setMinDate(tomorrow.getTime());
                datePickerDialog.show();
            }
        });

        /*
        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), editOrderClient.class);
                startActivity(intent);
            }
        });
        */

        //back button
        newOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                //set title
                alertDialogBuilder.setTitle("Seite verlassen?");

                //set Dialog Message
                alertDialogBuilder.setMessage("Die ausgewählten Bestellungen gehen verloren?").setCancelable(false)
                        .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //if this button is clicked, close currect activity
                                startActivity(new Intent(MyOrdersClient.this, MenuplanClient.class));
                                //ToDo: Implement to delete correctly and officialy

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

        //edit button
        cancelSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                //set title
                alertDialogBuilder.setTitle("Bestellungen stornieren?");

                //set Dialog Message
                alertDialogBuilder.setMessage("Möchten Sie die ausgewählten Bestellungen stornieren?").setCancelable(false)
                        .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //if this button is clicked, close currect activity
                                finish();
                                startActivity(getIntent());
                                //ToDo: Implement to delete correctly and officialy
                                Toast.makeText(getApplicationContext(), "Die ausgewählten Bestellungen wurden storniert.", Toast.LENGTH_SHORT).show();
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

    int selectedCount = 0; // Global variable

    @Override
    public void checkboxListener(boolean isSelected){
        if (isSelected)
            selectedCount++;
        else
            selectedCount--;
        if (selectedCount == 1)
            findViewById(R.id.loeschenBtn).setEnabled(true);
        else
            findViewById(R.id.loeschenBtn).setEnabled(false);
    }

    class customAdapterForCheckbox  extends BaseAdapter {

        private checkboxInterface checkboxInterface1;

        public customAdapterForCheckbox(checkboxInterface myInterface){

            this.checkboxInterface1 = myInterface;
        }

        @Override
        public int getCount() {
            return dateArray.length;
        }

        @Override
        public Object getItem(int i) { return null; }

        @Override
        public long getItemId(int i) {
            return 0;
        }


        @Override
        public View getView(int i, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.my_order_list_item, null, true);

            final CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
            TextView tvDate = (TextView) convertView.findViewById(R.id.date);
            TextView tvMenu = (TextView) convertView.findViewById(R.id.menuType);
            TextView tvAppetizer = (TextView) convertView.findViewById(R.id.appetizer);
            TextView tvMainCourse = (TextView) convertView.findViewById(R.id.mainCourse);
            TextView tvDessert = (TextView) convertView.findViewById(R.id.dessert);
            ImageButton editOrder = (ImageButton) convertView.findViewById(R.id.editOrderButton);


            tvDate.setText(dateArray[i]);
            tvMenu.setText(menuArray[i]);
            tvAppetizer.setText(appetizerArray[i]);
            tvMainCourse.setText(mainCourseArray[i]);
            tvDessert.setText(dessertArray[i]);

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });


            editOrder.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MyOrdersClient.this, editOrderClient.class);
                    startActivity(intent);
                }
            });
            //final Button buttonCancel = (Button)findViewById( R.id.loeschenBtn);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    checkboxStatus(isChecked);
                    buttonView.setChecked(isChecked);
                }

                private void checkboxStatus(boolean isChecked) {
                    checkboxInterface1.checkboxListener(isChecked);
                }
            });

            return convertView;
        }
    }
}
