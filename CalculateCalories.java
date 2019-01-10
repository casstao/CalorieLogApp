package com.example.kevinctran.a45j_application;

/*
Cass Tao was the driver (typing code) for this page
Kevin Tran was the observer/navigator

Pair programming done in person on every page
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;



public class CalculateCalories extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private ChildEventListener childEventListener;

    private FoodAdapter listAdapter;
    public int caloriecount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculate_calories);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("foods");

        ArrayList<Food> foodList = new ArrayList<Food>();


        childEventListener = new ChildEventListener(){
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Food f = dataSnapshot.getValue(Food.class);
                caloriecount += Integer.parseInt(f.getNumber());
                listAdapter.add( dataSnapshot.getValue(Food.class));


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };

        myRef.addChildEventListener(childEventListener);
        listAdapter = new FoodAdapter(this, foodList );



    }
    public void showNumber(View view)
    {
        TextView CalorieView2 = (TextView)findViewById(R.id.CalorieView2);
        CalorieView2.setText(Integer.toString(caloriecount) + " Calories");
    }

    public void goHome(View view)
    {
        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);
    }
}