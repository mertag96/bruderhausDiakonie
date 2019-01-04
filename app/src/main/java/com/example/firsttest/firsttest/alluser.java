package com.example.firsttest.firsttest;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class alluser extends AppCompatActivity {

    String [] tableHeader={"ID", "Benutzername", "BerechtigungID", "Telefon"};
    String [][] tableContent;

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
