package com.example.firsttest.firsttest;

import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Date;


public class Invoices {
// wird für AllOrders benötigt


    public InvoiceData[] getInvoices() {
        //ist noch nicht ganz dynamisch
        //hier werden die Zeilen der Tabelle gefüllt
        InvoiceData[] data = new InvoiceData[20]; //hardcode



        for(int i = 0; i < data.length; i ++) {
            InvoiceData row = new InvoiceData();
            /*
            row.id = (i+1);
            row.invoiceNumber = row.id;

            row.amountDue = BigDecimal.valueOf(20.00 * i);
            row.invoiceAmount = BigDecimal.valueOf(120.00 * (i+1));
            row.invoiceDate = new Date();

            row.customerName =  "Thomas John Beckett";
            row.customerAddress = "1112, Hash Avenue, NYC";
            data[i] = row;
            */
            //erste Spalte
            row.id=(i+1);
            row.invoiceNr= row.id;

            //zweite Spalte
            row.name = "Max Mustermann " + (i+1);

            //dritte Spalte
            row.mealNr = "Nr.: " + (i + 1);

            //vierte Spalte
            row.date = new Date();

            data[i] = row;

        }
        return data;
    }
}
