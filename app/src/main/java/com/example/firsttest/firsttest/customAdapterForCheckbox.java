package com.example.firsttest.firsttest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by hardik on 9/1/17.
 */
public class customAdapterForCheckbox  extends BaseAdapter {

    private Context context;
    public static ArrayList<orderedMenu> modelArrayList;


    public customAdapterForCheckbox(Context context, ArrayList<orderedMenu> modelArrayList) {

        this.context = context;
        this.modelArrayList = modelArrayList;

    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return modelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        /*if (convertView == null) {
            holder = new ViewHolder();*/
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.my_order_list_item, null, true);

            /*holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
            holder.tvDate = (TextView) convertView.findViewById(R.id.date);
            holder.tvMenu = (TextView) convertView.findViewById(R.id.menuType);
            holder.tvAppetizer = (TextView) convertView.findViewById(R.id.appetizer);
            holder.tvMainCourse = (TextView) convertView.findViewById(R.id.mainCourse);
            holder.tvDessert = (TextView) convertView.findViewById(R.id.dessert);*/

            CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
            TextView tvDate = (TextView) convertView.findViewById(R.id.date);
            TextView tvMenu = (TextView) convertView.findViewById(R.id.menuType);
            TextView tvAppetizer = (TextView) convertView.findViewById(R.id.appetizer);
            TextView tvMainCourse = (TextView) convertView.findViewById(R.id.mainCourse);
            TextView tvDessert = (TextView) convertView.findViewById(R.id.dessert);

        /*    convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }*/


        //holder.checkBox.setText(""+ position);
        tvDate.setText(modelArrayList.get(position).getDate());
        tvMenu.setText(modelArrayList.get(position).getMenu());
        tvAppetizer.setText(modelArrayList.get(position).getAppetizer());
        tvMainCourse.setText(modelArrayList.get(position).getMainCourse());
        tvDessert.setText(modelArrayList.get(position).getDessert());

        checkBox.setChecked(modelArrayList.get(position).getSelected());

        checkBox.setTag(R.integer.btnplusview, convertView);
        checkBox.setTag( position);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*View tempview = (View) holder.checkBox.getTag(R.integer.btnplusview);
                TextView tv = (TextView) tempview.findViewById(R.id.date);
                TextView tvMenu = (TextView) tempview.findViewById(R.id.menuType);
                TextView tvAppetizer = (TextView) tempview.findViewById(R.id.appetizer);
                TextView tvMainCourse = (TextView) tempview.findViewById(R.id.mainCourse);
                TextView tvDessert = (TextView) tempview.findViewById(R.id.dessert);
                Integer pos = (Integer)  holder.checkBox.getTag();
                //Toast.makeText(context, "Checkbox "+pos+" clicked!", Toast.LENGTH_SHORT).show();

                if(modelArrayList.get(pos).getSelected()){
                    modelArrayList.get(pos).setSelected(false);
                }else {
                    modelArrayList.get(pos).setSelected(true);
                }*/

            }
        });

        return convertView;
    }

    private class ViewHolder {

        protected CheckBox checkBox;
        private TextView tvDate;
        private TextView tvMenu;
        private TextView tvAppetizer;
        private TextView tvMainCourse;
        private TextView tvDessert;
    }

}