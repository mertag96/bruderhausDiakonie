package com.example.firsttest.firsttest;

public class Menu {
    private int id;
    private String description;
    private int menuid;
    private int foodtyp;
    private int menge;
    private int KW;

    public Menu(int id, String description, int menuid, int foodtyp, int KW){
        setId(id);
        setFoodtyp(foodtyp);
        setMenuid(menuid);
        setDescription(description);
        setMenge(0);
        setKW(KW);
    }

    private void setId(int id){
        if(id>=0){
            this.id = id;
        }
    }

    private void setDescription(String description){
        if(description !=null){
            this.description = description;
        }
    }

    private void setMenuid(int menuid){
        if(menuid>=0){
            this.menuid = menuid;
        }
    }

    private void setFoodtyp(int foodtyp){
        if(foodtyp>=0){
            this.foodtyp = foodtyp;
        }
    }

    private void setMenge(int menge){
        if(menge>=0){
            this.menge = menge;
        }
    }

    private void setKW(int KW){
        if(KW>=0){
            this.KW = KW;
        }
    }

    public int getId() {
        return this.id;
    }

    public int getMenge() {
        return this.menge;
    }

    public int getFoodtyp() {
        return this.foodtyp;
    }

    public int getMenuid() {
        return this.menuid;
    }

    public String getDescription() {
        return this.description;
    }

    public int getKW() {
        return KW;
    }
}
