package com.example.firsttest.firsttest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends PagerAdapter {


    // needed for Menuplan


    private List<Model> models;
    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<NumberPicker[]> nplist = new ArrayList<>();


    public Adapter(List<Model> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    public int[] getNumberPickerValue (int menue){
        int[] temp = new int[3];
        temp[0] = nplist.get(menue)[0].getValue();
        temp[1] = nplist.get(menue)[1].getValue();
        temp[2] = nplist.get(menue)[2].getValue();
        return temp;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position){
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item, container, false);
        TextView title, inhaltVorspeise1, inhaltMittagessen, inhaltNachspeise;

        NumberPicker[] nparr = new NumberPicker[3];

        title = view.findViewById(R.id.title);
        inhaltVorspeise1= view.findViewById(R.id.inhaltVorspeise1);
        inhaltMittagessen = view.findViewById(R.id.inhaltMittagessen);
        inhaltNachspeise = view.findViewById(R.id.inhaltNachspeise);

        nparr[0] = view.findViewById(R.id.numberpicker1);
        nparr[1] = view.findViewById(R.id.numberpicker2);
        nparr[2] = view.findViewById(R.id.numberpicker3);

        nparr[0].setMinValue(0);
        nparr[0].setMaxValue(5);
        nparr[0].setWrapSelectorWheel(true);

        nparr[1].setMinValue(0);
        nparr[1].setMaxValue(5);
        nparr[1].setWrapSelectorWheel(true);

        nparr[2].setMinValue(0);
        nparr[2].setMaxValue(5);
        nparr[2].setWrapSelectorWheel(true);

        nplist.add(nparr);

        /*
  numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                String text = "Changed from " + oldVal + " to " + newVal;
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });


*/
        title.setText(models.get(position).getTitle());
        inhaltVorspeise1.setText(models.get(position).getInhaltVorspeise1());
        inhaltMittagessen.setText(models.get(position).getInhaltMittagessen());
        inhaltNachspeise.setText(models.get(position).getInhaltNachspeise());

        container.addView(view,0 );

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object){
        container.removeView((View)object);
    }

}
