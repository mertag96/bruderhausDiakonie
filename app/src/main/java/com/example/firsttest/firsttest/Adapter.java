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

import java.util.List;

public class Adapter extends PagerAdapter {


    // needed for Menuplan


    private List<Model> models;
    private LayoutInflater layoutInflater;
    private Context context;


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

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position){
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item, container, false);


        TextView title, inhaltVorspeise1, inhaltMittagessen, inhaltNachspeise;
        NumberPicker np1, np2, np3;

        title = view.findViewById(R.id.title);
        inhaltVorspeise1= view.findViewById(R.id.inhaltVorspeise1);
        inhaltMittagessen = view.findViewById(R.id.inhaltMittagessen);
        inhaltNachspeise = view.findViewById(R.id.inhaltNachspeise);

        np1 = view.findViewById(R.id.numberpicker1);
        np2 = view.findViewById(R.id.numberpicker2);
        np3 = view.findViewById(R.id.numberpicker3);

        np1.setMinValue(0);
        np1.setMaxValue(5);
        np1.setWrapSelectorWheel(true);

        np2.setMinValue(0);
        np2.setMaxValue(5);
        np2.setWrapSelectorWheel(true);

        np3.setMinValue(0);
        np3.setMaxValue(5);
        np3.setWrapSelectorWheel(true);


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
