package com.example.firsttest.firsttest;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class alluser extends AppCompatActivity {

   private String [] tableHeader={"ID", "Benutzername", "BerechtigungID", "Telefon"};
   private String [][] tableContent;
   private Button goback, newUser;

   //ToDo Beim horizontal scrollen, ist es mit der view implementiert aber die Spaltenzeilen an sich passen sich noch nicht ganz an und es kürz auptmatisch mit ... ab (Bug)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alluser);

        final TableView<String[]> tb = (TableView<String[]>) findViewById(R.id.tableView);
        tb.setColumnCount(tableHeader.length);
        tb.setHeaderBackgroundColor(Color.parseColor("#187ED1"));

        //Insert data
        fillData();

        //Adapter
        tb.setHeaderAdapter(new SimpleTableHeaderAdapter(this, tableHeader));
        tb.setDataAdapter(new SimpleTableDataAdapter(this, tableContent));

        tb.addDataClickListener(new TableDataClickListener<String[]>() {
            @Override
            public void onDataClicked(int rowIndex, String[] clickedData) {
                Toast.makeText(alluser.this, ((String[])clickedData)[1], Toast.LENGTH_SHORT).show();
            }
        });

        goback = findViewById(R.id.zuruck);
        newUser = findViewById(R.id.neuerBenutzer);


        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(alluser.this, Menunavigation.class));
            }

        });

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent (alluser.this, RegisterActivity.class));
            }
        });

    }
    private void fillData(){
        AllUserTableTop usertable = new AllUserTableTop();
        ArrayList<AllUserTableTop> userlist = new ArrayList<>();

        //ToDo: getUsertable from database and insert it here dynamically instead static

        usertable.setId("1");
        usertable.setUsername("Dummy");
        usertable.setPermissionID("2");
        usertable.setPhone("074329876");
        userlist.add(usertable);

        usertable.setId("1");
        usertable.setUsername("test2");
        usertable.setPermissionID("2");
        usertable.setPhone("074329876");
        userlist.add(usertable);
        usertable.setId("1");

        usertable.setUsername("test3");
        usertable.setPermissionID("2");
        usertable.setPhone("074329876");
        userlist.add(usertable);

        usertable.setId("1");
        usertable.setUsername("Dummy");
        usertable.setPermissionID("2");
        usertable.setPhone("074329876");
        userlist.add(usertable);
        usertable.setId("1");
        usertable.setUsername("Dummy");
        usertable.setPermissionID("2");
        usertable.setPhone("074329876");
        userlist.add(usertable);
        usertable.setId("1");
        usertable.setUsername("Dummy");
        usertable.setPermissionID("2");
        usertable.setPhone("074329876");
        userlist.add(usertable);
        usertable.setId("1");
        usertable.setUsername("Dummy");
        usertable.setPermissionID("2");
        usertable.setPhone("074329876");
        userlist.add(usertable);
        usertable.setId("1");
        usertable.setUsername("Dummy");
        usertable.setPermissionID("2");
        usertable.setPhone("074329876");
        userlist.add(usertable);
        usertable.setId("1");
        usertable.setUsername("Dummy");
        usertable.setPermissionID("2");
        usertable.setPhone("074329876");
        userlist.add(usertable);
        usertable.setId("1");
        usertable.setUsername("Dummy");
        usertable.setPermissionID("2");
        usertable.setPhone("074329876");
        userlist.add(usertable);
        usertable.setId("1");
        usertable.setUsername("Dummy");
        usertable.setPermissionID("2");
        usertable.setPhone("074329876");
        userlist.add(usertable);
        usertable.setId("1");
        usertable.setUsername("Dummy");
        usertable.setPermissionID("2");
        usertable.setPhone("074329876");
        userlist.add(usertable);
        usertable.setId("1");
        usertable.setUsername("Dummy");
        usertable.setPermissionID("2");
        usertable.setPhone("074329876");
        userlist.add(usertable);
        usertable.setId("1");
        usertable.setUsername("Dummy");
        usertable.setPermissionID("2");
        usertable.setPhone("074329876");
        userlist.add(usertable);
        usertable.setId("1");
        usertable.setUsername("Dummy");
        usertable.setPermissionID("2");
        usertable.setPhone("074329876");
        userlist.add(usertable);

        tableContent = new String[userlist.size()][4];

        for(int i=0; i<userlist.size(); i++){
            AllUserTableTop a=userlist.get(i);

            tableContent[i][0] = a.getId();
            tableContent[i][1] = a.getUsername();
            tableContent[i][2] = a.getPermissionID();
            tableContent[i][3] = a.getPhone();
        }

    }
}
