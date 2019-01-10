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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private ChildEventListener childEventListener;

    private ArrayList<Food> foodList;
    private ArrayList<Food> searchResults;

    private FoodAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("foods");

        foodList = new ArrayList<Food>();

        searchResults = new ArrayList<Food>();


        childEventListener = new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                foodList.add(dataSnapshot.getValue(Food.class));
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };

        myRef.addChildEventListener(childEventListener);

        listAdapter = new FoodAdapter( this, searchResults);
        ListView results = findViewById(R.id.listViewResults);
        results.setAdapter(listAdapter);

        results.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Food selectedItem = (Food) parent.getItemAtPosition(position);

                EditText name = (EditText) findViewById( R.id.editTextName);

                EditText calories = (EditText) findViewById(R.id.editTextCalories);
                String updateCalories = calories.getText().toString();

                EditText Place = (EditText) findViewById(R.id.editTextPlace);
                String updatePlace = Place.getText().toString();

                EditText Time = (EditText) findViewById(R.id.editTextTime);
                String updateTime = Time.getText().toString();

                TextView instructions = (TextView)findViewById(R.id.textViewInstructions);

                if(!updateCalories.equals("")) {
                    if(updatePlace.equals(""))
                    {
                        updatePlace = selectedItem.getLocation().getPlace();
                    }
                    if(updateTime.equals(""))
                    {
                        updateTime = selectedItem.getLocation().getTime();
                    }
                    Location l = new Location(updatePlace, updateTime);
                    Food add = new Food(selectedItem.getName(), updateCalories, selectedItem.getUid(), l);
                    myRef.child(selectedItem.getUid()).setValue(add);
                    instructions.setText(add.getName() + " successfully updated.");
                    foodList.remove( selectedItem ); // Removes old contact
                    foodList.add( add ); // Adds updated (new) contact
                    refresh( add.getName()); // Refreshes the listView
                }
                else
                    instructions.setText("Invalid calories");

                name.setText("");
                calories.setText("");
                Place.setText("");
                Time.setText("");
            }
        });
    }

    public void refresh(String update)
    {
        listAdapter.clear();
        for( Food c: foodList)
        {
            if(c.getName().equalsIgnoreCase(update)) {

                listAdapter.add(c);
            }
        }
    }
    public void updateContact(View view) {
        TextView instructions = (TextView)findViewById(R.id.textViewInstructions);

        listAdapter.clear();
        boolean found = false;
        for (Food c : foodList) {
            EditText text = (EditText) findViewById(R.id.editTextName);
            String search = text.getText().toString();
            if (c.getName().equalsIgnoreCase(search)) {
                listAdapter.add(c);
                found = true;
            }
        }
        EditText search = (EditText) findViewById(R.id.editTextName);
        EditText calories = (EditText) findViewById( R.id.editTextCalories);
        if (!found) {
            Toast.makeText(this, search.getText().toString() + " not found.", Toast.LENGTH_LONG).show();
            search.setText("");
            calories.setText("");
            instructions.setText("");
        }
        else
            instructions.setText("Select food");
    }

    public void goHome(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity( intent);
    }
}
