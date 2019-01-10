package com.example.kevinctran.a45j_application;

/*
Kevin Tran was the driver (typing code) for this page
Cass Tao was the observer/navigator

Pair programming done in person on every page
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends ArrayAdapter<Food> {
    private Context mContext;
    private List<Food> foodList = new ArrayList<Food>();

    public FoodAdapter( Context context, ArrayList<Food> list)
    {
        super( context, 0, list);
        mContext = context;
        foodList = list;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.contact_view,parent,false);

        Food currentFood = foodList.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.textView_Name);
        name.setText(currentFood.getName());

        TextView calories = (TextView) listItem.findViewById(R.id.textView_Calories);
        calories.setText(currentFood.getNumber());

        TextView location= (TextView) listItem.findViewById(R.id.textView_Location);
        location.setText(currentFood.getLocation().toString());




        return listItem;
    }
}
