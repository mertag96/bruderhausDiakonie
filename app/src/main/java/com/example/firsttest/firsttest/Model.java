package com.example.firsttest.firsttest;

import android.widget.NumberPicker;

public class Model {

    //needed for the Menuplan class

    private String title;
    private String inhaltVorspeise1;
    private String inhaltMittagessen;
    private String inhaltNachspeise;
   // private NumberPicker np1;
    // EditText title, vorspeise, mittag, nachspeise;



    public Model(String title, String inhaltVorspeise1, String inhaltMittagessen, String inhaltNachspeise ) {
        this.title = title;
        this.inhaltVorspeise1 = inhaltVorspeise1;
        this.inhaltMittagessen = inhaltMittagessen;
        this.inhaltNachspeise = inhaltNachspeise;

        //this.np1=np1;
    }

    public Model(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInhaltVorspeise1() {
        return inhaltVorspeise1;
    }

    public void setInhaltVorspeise1(String inhaltVorspeise1) {
        this.inhaltVorspeise1 = inhaltVorspeise1;
    }

    public String getInhaltMittagessen() {
        return inhaltMittagessen;
    }

    public void setInhaltMittagessen(String inhaltMittagessen) {
        this.inhaltMittagessen = inhaltMittagessen;
    }

    public String getInhaltNachspeise() {
        return inhaltNachspeise;
    }

    public void setInhaltNachspeise(String inhaltNachspeise) {
        this.inhaltNachspeise = inhaltNachspeise;
    }

      /*public NumberPicker getNp1() {
        return np1;
    }

    public void setNp1(NumberPicker np1) {
        this.np1 = np1;
    }  */
}
