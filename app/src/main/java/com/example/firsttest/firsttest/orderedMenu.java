package com.example.firsttest.firsttest;

import java.util.ArrayList;

public class orderedMenu {

    private boolean isSelected;
    private String menu, date, appetizer, mainCourse, dessert;

    /*public orderedMenu(String menu, String date, String appetizer, String mainCourse, String dessert){
        this.menu = menu;
        this.date = date;
        this.appetizer = appetizer;
        this.mainCourse = mainCourse;
        this.dessert = dessert;
    }*/

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getAppetizer() {
        return appetizer;
    }

    public void setAppetizer(String appetizer) {
        this.appetizer = appetizer;
    }

    public String getMainCourse() {
        return mainCourse;
    }

    public void setMainCourse(String mainCourse ) {
        this.mainCourse = mainCourse;
    }

    public String getDessert() {
        return dessert;
    }

    public void setDessert(String dessert) {
        this.dessert = dessert;
    }

    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }



    public String toString(){
        return "ist noch nicht definiert, erst definieren falls nötig";
    }
}
