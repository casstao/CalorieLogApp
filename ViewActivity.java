package com.example.kevinctran.a45j_application;

/*
Kevin Tran was the driver (typing code) for this page
Cass Tao was the observer/navigator

Pair programming done in person on every page
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;



public class ViewActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private ChildEventListener childEventListener;

    private FoodAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("foods");

        ArrayList<Food> foodList = new ArrayList<Food>();

        childEventListener = new ChildEventListener(){
            @Override

            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

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
        ListView results = findViewById(R.id.listViewResults);
        results.setAdapter(listAdapter);


    }

    public void goHome(View view)
    {
        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);
    }
}