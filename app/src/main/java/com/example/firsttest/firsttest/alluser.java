package com.example.firsttest.firsttest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.Inflater;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class alluser extends AppCompatActivity implements HorizontalScroll.ScrollViewListener, VerticalScroll.ScrollViewListener {

  // private String [] tableHeader={"ID", "Benutzername", "Vorname", "Nachname", "BerechtigungID", "Telefon"};
  // private String [][] tableContent;
   private Button goback, newUser;
   private SearchView searchview;
   private String userID, userName;
   private Intent goToMenuNav, goToRegister;



    private static int SCREEN_HEIGHT;
    private static int SCREEN_WIDTH;
    RelativeLayout relativeLayoutMain;

    RelativeLayout relativeLayoutA;
    RelativeLayout relativeLayoutB;
    RelativeLayout relativeLayoutC;
    RelativeLayout relativeLayoutD;

    TableLayout tableLayoutA;
    TableLayout tableLayoutB;
    TableLayout tableLayoutC;
    TableLayout tableLayoutD;



    TableRow tableRow;
    TableRow tableRowB;

    HorizontalScroll horizontalScrollViewB;
    HorizontalScroll horizontalScrollViewD;

    VerticalScroll scrollViewC;
    VerticalScroll scrollViewD;
    private JSONArray da;

    /*
         This is for counting how many columns are added in the row.
    */
    int tableColumnCountB= 0;

    /*
         This is for counting how many row is added.
    */
    int tableRowCountC= 0;

   //ToDo Beim horizontal scrollen, ist es mit der view implementiert aber die Spaltenzeilen an sich passen sich noch nicht ganz an und es kürz auptmatisch mit ... ab (Bug)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alluser);

        goback = findViewById(R.id.zuruck);
        newUser = findViewById(R.id.neuerBenutzer);

        userID = getIntent().getExtras().get("userid").toString();
        userName = getIntent().getExtras().get("username").toString();

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMenuNav = new Intent(alluser.this, Menunavigation.class);
                goToMenuNav.putExtra("userid",userID);
                goToMenuNav.putExtra("username",userName);
                startActivity(goToMenuNav);
            }
        });

        newUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToRegister = new Intent(alluser.this, RegisterActivity.class);
                goToRegister.putExtra("userid",userID);
                goToRegister.putExtra("username",userName);
                startActivity(goToRegister);
            }
        });

        /*
            Mandatory Content
         */
        //getAllUsers();
        relativeLayoutMain= (RelativeLayout)findViewById(R.id.relativeLayoutMain);
        getScreenDimension();
        initializeRelativeLayout();
        initializeScrollers();
        initializeTableLayout();
        horizontalScrollViewB.setScrollViewListener(this);
        horizontalScrollViewD.setScrollViewListener(this);
        scrollViewC.setScrollViewListener(this);
        scrollViewD.setScrollViewListener(this);
        addRowToTableA();
        initializeRowForTableB();

        /*  There is two unused functions
            Have a look on these functions and try to recreate and use it.
            createCompleteColumn();
            createCompleteRow();
        */
        for(int i=0; i<1; i++){
            // addColumnsToTableB("Head" + i, i);
            //addColumnsToTableB("ID", 0);
            addColumnsToTableB("VORNAME", 0);
            addColumnsToTableB("NACHNAME", 1);
            addColumnsToTableB("STRASSE", 2);
            addColumnsToTableB("HAUSNUMMER", 3);
            addColumnsToTableB("ZUSATZADRESSE", 4);
            addColumnsToTableB("PLZ", 5);
            addColumnsToTableB("ORT", 6);
            addColumnsToTableB("TELEFONNUMMER", 7);
            addColumnsToTableB("ROLE", 8);

        }

        //hier ist es noch nicht dynamisch die Zeilen
        getAllUsers();
    }

    private void getScreenDimension(){
        WindowManager wm= (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        SCREEN_WIDTH= size.x;
        SCREEN_HEIGHT = size.y;
    }

    //! diese Werte so lassen, sie beinträchtigen sonst negativ das design
    private void initializeRelativeLayout(){

        relativeLayoutA= new RelativeLayout(getApplicationContext());
        relativeLayoutA.setId(R.id.relativeLayoutA);
        relativeLayoutA.setPadding(0,0,0,0);

        relativeLayoutB= new RelativeLayout(getApplicationContext());
        relativeLayoutB.setId(R.id.relativeLayoutB);
        relativeLayoutB.setPadding(0,0,0,0);

        relativeLayoutC= new RelativeLayout(getApplicationContext());
        relativeLayoutC.setId(R.id.relativeLayoutC);
        relativeLayoutC.setPadding(0,0,0,0);

        relativeLayoutD= new RelativeLayout(getApplicationContext());
        relativeLayoutD.setId(R.id.relativeLayoutD);
        relativeLayoutD.setPadding(0,0,0,0);


        //für was screen_Height ist, check ich immernoch nicht so ganz
        relativeLayoutA.setLayoutParams(new RelativeLayout.LayoutParams(SCREEN_WIDTH/5,SCREEN_HEIGHT/15));
        this.relativeLayoutMain.addView(relativeLayoutA);


        RelativeLayout.LayoutParams layoutParamsRelativeLayoutB= new RelativeLayout.LayoutParams(SCREEN_WIDTH- (SCREEN_WIDTH/5), SCREEN_HEIGHT/15);
        layoutParamsRelativeLayoutB.addRule(RelativeLayout.RIGHT_OF, R.id.relativeLayoutA);
        relativeLayoutB.setLayoutParams(layoutParamsRelativeLayoutB);
        this.relativeLayoutMain.addView(relativeLayoutB);

        RelativeLayout.LayoutParams layoutParamsRelativeLayoutC= new RelativeLayout.LayoutParams(SCREEN_WIDTH/5, SCREEN_HEIGHT - (SCREEN_HEIGHT/20));
        layoutParamsRelativeLayoutC.addRule(RelativeLayout.BELOW, R.id.relativeLayoutA);
        relativeLayoutC.setLayoutParams(layoutParamsRelativeLayoutC);
        this.relativeLayoutMain.addView(relativeLayoutC);

        RelativeLayout.LayoutParams layoutParamsRelativeLayoutD= new RelativeLayout.LayoutParams(SCREEN_WIDTH- (SCREEN_WIDTH/5), SCREEN_HEIGHT - (SCREEN_HEIGHT/20));
        layoutParamsRelativeLayoutD.addRule(RelativeLayout.BELOW, R.id.relativeLayoutB);
        layoutParamsRelativeLayoutD.addRule(RelativeLayout.RIGHT_OF, R.id.relativeLayoutC);
        relativeLayoutD.setLayoutParams(layoutParamsRelativeLayoutD);
        this.relativeLayoutMain.addView(relativeLayoutD);

    }

    private void initializeScrollers(){
        horizontalScrollViewB= new HorizontalScroll(getApplicationContext());
        horizontalScrollViewB.setPadding(0,0,0,0);

        horizontalScrollViewD= new HorizontalScroll(getApplicationContext());
        horizontalScrollViewD.setPadding(0,0,0,0);

        scrollViewC= new VerticalScroll(getApplicationContext());
        scrollViewC.setPadding(0,0,0,0);

        scrollViewD= new VerticalScroll(getApplicationContext());
        scrollViewD.setPadding(0,0,0,0);

        horizontalScrollViewB.setLayoutParams(new ViewGroup.LayoutParams(SCREEN_WIDTH- (SCREEN_WIDTH/5), SCREEN_HEIGHT/5));
        scrollViewC.setLayoutParams(new ViewGroup.LayoutParams(SCREEN_WIDTH/5 ,SCREEN_HEIGHT - (SCREEN_HEIGHT/5)));
        scrollViewD.setLayoutParams(new ViewGroup.LayoutParams(SCREEN_WIDTH- (SCREEN_WIDTH/5), SCREEN_HEIGHT - (SCREEN_HEIGHT/5) ));
        horizontalScrollViewD.setLayoutParams(new ViewGroup.LayoutParams(SCREEN_WIDTH- (SCREEN_WIDTH/5), SCREEN_HEIGHT - (SCREEN_HEIGHT/20) ));

        this.relativeLayoutB.addView(horizontalScrollViewB);
        this.relativeLayoutC.addView(scrollViewC);
        this.scrollViewD.addView(horizontalScrollViewD);
        this.relativeLayoutD.addView(scrollViewD);

    }

    private  void initializeTableLayout(){

        tableLayoutA= new TableLayout(getApplicationContext());
        tableLayoutA.setPadding(0,0,0,0);
        tableLayoutB= new TableLayout(getApplicationContext());
        tableLayoutB.setPadding(0,0,0,0);
        tableLayoutB.setId(R.id.tableLayoutB);

        tableLayoutC= new TableLayout(getApplicationContext());
        tableLayoutC.setPadding(0,0,0,0);


        tableLayoutD= new TableLayout(getApplicationContext());
        tableLayoutD.setPadding(0,0,0,0);

        TableLayout.LayoutParams layoutParamsTableLayoutA= new TableLayout.LayoutParams(SCREEN_WIDTH/5, SCREEN_HEIGHT/5);

        tableLayoutA.setLayoutParams(layoutParamsTableLayoutA);
        //  tableLayoutA.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        this.relativeLayoutA.addView(tableLayoutA);

        TableLayout.LayoutParams layoutParamsTableLayoutB= new TableLayout.LayoutParams(SCREEN_WIDTH -(SCREEN_WIDTH/5), SCREEN_HEIGHT/5);
        tableLayoutB.setLayoutParams(layoutParamsTableLayoutB);
        tableLayoutB.setBackgroundColor(getResources().getColor(R.color.colorSecondary));
        this.horizontalScrollViewB.addView(tableLayoutB);

        TableLayout.LayoutParams layoutParamsTableLayoutC= new TableLayout.LayoutParams(SCREEN_WIDTH/5, SCREEN_HEIGHT - (SCREEN_HEIGHT/5));
        tableLayoutC.setLayoutParams(layoutParamsTableLayoutC);
        tableLayoutC.setBackgroundColor(getResources().getColor(R.color.colorSecondary));
        this.scrollViewC.addView(tableLayoutC);

        TableLayout.LayoutParams layoutParamsTableLayoutD= new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);
        tableLayoutD.setLayoutParams(layoutParamsTableLayoutD);
        this.horizontalScrollViewD.addView(tableLayoutD);

    }

    @Override
    public void onScrollChanged(HorizontalScroll scrollView, int x, int y, int oldx, int oldy) {
        if(scrollView == horizontalScrollViewB){
            horizontalScrollViewD.scrollTo(x,y);
        }
        else if(scrollView == horizontalScrollViewD){
            horizontalScrollViewB.scrollTo(x, y);
        }

    }

    @Override
    public void onScrollChanged(VerticalScroll scrollView, int x, int y, int oldx, int oldy) {
        if(scrollView == scrollViewC){
            scrollViewD.scrollTo(x,y);
        }
        else if(scrollView == scrollViewD){
            scrollViewC.scrollTo(x,y);
        }
    }

    private void addRowToTableA(){
        tableRow= new TableRow(getApplicationContext());
        TableRow.LayoutParams layoutParamsTableRow= new TableRow.LayoutParams(SCREEN_WIDTH/1, SCREEN_HEIGHT/5);
        tableRow.setLayoutParams(layoutParamsTableRow);
        TextView label_date = new TextView(getApplicationContext());
        //label_date.setText("Item/ID");
        tableRow.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        label_date.setTextSize(getResources().getDimension(R.dimen.cell_text_size));
        tableRow.addView(label_date);
        this.tableLayoutA.addView(tableRow);
    }

    private void initializeRowForTableB(){
        tableRowB= new TableRow(getApplicationContext());
        tableRow.setPadding(0,0,0,0);
        this.tableLayoutB.addView(tableRowB);
    }
    //hier kann man sagen die anzahl der Spalten die sich immer in einer Sicht angezeigt werden
    private synchronized void addColumnsToTableB(String text, final int id){
        tableRow= new TableRow(getApplicationContext());
        TableRow.LayoutParams layoutParamsTableRow= new TableRow.LayoutParams(SCREEN_WIDTH/2, SCREEN_HEIGHT/5);
        tableRow.setPadding(3,3,3,3);
        tableRow.setLayoutParams(layoutParamsTableRow);
        TextView label_date = new TextView(getApplicationContext());
        label_date.setText(text);
        label_date.setTextSize(getResources().getDimension(R.dimen.cell_text_size));
        this.tableRow.addView(label_date);
        this.tableRow.setTag(id);
        this.tableRowB.addView(tableRow);
        tableColumnCountB++;
    }

    private synchronized void addRowToTableC(String text){
        TableRow tableRow1= new TableRow(getApplicationContext());
        TableRow.LayoutParams layoutParamsTableRow1= new TableRow.LayoutParams(SCREEN_WIDTH/5, SCREEN_HEIGHT/10);
        tableRow1.setPadding(3,3,3,4);
        tableRow1.setLayoutParams(layoutParamsTableRow1);
        TextView label_date = new TextView(getApplicationContext());
        label_date.setText(text);
        label_date.setTextSize(getResources().getDimension(R.dimen.cell_text_size));
        tableRow1.addView(label_date);

        TableRow tableRow= new TableRow(getApplicationContext());
        TableRow.LayoutParams layoutParamsTableRow= new TableRow.LayoutParams(SCREEN_WIDTH/1, SCREEN_HEIGHT/10);
        tableRow.setPadding(3,3,3,4);
        tableRow.setLayoutParams(layoutParamsTableRow);
        tableRow.addView(tableRow1);
        this.tableLayoutC.addView(tableRow, tableRowCountC);
        tableRowCountC++;
    }

    //hier??
    private synchronized void initializeRowForTableD(int pos){
        TableRow tableRowB= new TableRow(getApplicationContext());
        TableRow.LayoutParams layoutParamsTableRow= new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, SCREEN_HEIGHT/10);
        tableRowB.setPadding(0,0,0,0);
        tableRowB.setLayoutParams(layoutParamsTableRow);
        this.tableLayoutD.addView(tableRowB, pos);
    }

    //Tabellen Inhaltspalten größe definieren
    private synchronized void addColumnToTableAtD(final int rowPos, String text){
        TableRow tableRowAdd= (TableRow) this.tableLayoutD.getChildAt(rowPos);
        tableRow= new TableRow(getApplicationContext());
        TableRow.LayoutParams layoutParamsTableRow= new TableRow.LayoutParams(SCREEN_WIDTH/2, SCREEN_HEIGHT/10);
        tableRow.setPadding(3,3,3,4);
        tableRow.setBackground(getResources().getDrawable(R.drawable.cell_bacground));
        tableRow.setLayoutParams(layoutParamsTableRow);
        TextView label_date = new TextView(getApplicationContext());
        label_date.setText(text);
        label_date.setTextSize(getResources().getDimension(R.dimen.cell_text_size));
        tableRow.setTag(label_date);
        this.tableRow.addView(label_date);
        tableRowAdd.addView(tableRow);
    }


    private void createCompleteColumn(String value){
        int i=0;
        int j=tableRowCountC-1;
        for(int k=i; k<=j; k++){
            addColumnToTableAtD(k, value);
        }
    }

    private void createCompleteRow(String value) {
        initializeRowForTableD(0);
        int i = 0;
        int j = tableColumnCountB - 1;
        int pos = tableRowCountC - 1;
        for (int k = i; k <= j; k++) {
            addColumnToTableAtD(pos, value);
        }





      /*  final TableView<String[]> tb = (TableView<String[]>) findViewById(R.id.tableView);
        tb.setColumnCount(tableHeader.length);
        tb.setHeaderBackgroundColor(Color.parseColor("#187ED1"));
*/
        //Insert data
        // fillData();
/*
        //Adapter
        tb.setHeaderAdapter(new SimpleTableHeaderAdapter(this, tableHeader));
        tb.setDataAdapter(new SimpleTableDataAdapter(this, tableContent));

        tb.addDataClickListener(new TableDataClickListener<String[]>() {
            @Override
            public void onDataClicked(int rowIndex, String[] clickedData) {
                Toast.makeText(alluser.this, ((String[])clickedData)[1], Toast.LENGTH_SHORT).show();
            }
        }); */

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
                startActivity(new Intent(alluser.this, RegisterActivity.class));
            }
        });
    }

        //binding the searchview into tableView
    /*    searchview = findViewById(R.id.searchView);
        query = searchview.getQuery(); // gets the query string currently in the text field

        //perform set on query text listener event
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //do something on text submit
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //do something when text changes
                return false;
            }
        });
        */

    public void rebuildTable(JSONArray data) throws JSONException {

        //System.out.println("Aufbau Methode wird gestartet");
        System.out.println("Aufbaudaten " + data.toString());
        String[] ids= {"VORNAME","NACHNAME","STRASSE","HAUSNUMMER","ZUSATZADRESSE","PLZ","ORT","TELEFONNUMMER","ROLE"};
        for(int i=0; i<data.length(); i++){
            initializeRowForTableD(i);
            JSONObject datarow = data.getJSONObject(i);
            addRowToTableC(""+ datarow.optString("ID"));
            for(int j =0; j<ids.length;j++){

                addColumnToTableAtD(i, datarow.optString(ids[j]));



                tableRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(alluser.this, "Test", Toast.LENGTH_SHORT).show();
                        //Implement stuff!!!!!
                    }
                });



            }
        }
    }

    public void getAllUsers(){
        NetworkHandler networkHelper = new NetworkHandler();

        networkHelper.get("http://134.103.176.137:8081/users", new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }
            @Override
            public void onResponse(Response response) throws IOException {
                String responseStr = response.body().string();
                //System.out.println("Aufbau Text = "+responseStr);
                try {
                    da = new JSONArray(responseStr);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                rebuildTable(da);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                } catch (JSONException e) {
                    System.out.println(e);
                    e.printStackTrace();
                }
            }
        });
    }
}

