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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RemoveActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private ChildEventListener childEventListener;

    private ArrayList<Food> foodList;

    private ArrayList<Food> searchResults;

    private FoodAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("foods");

        foodList = new ArrayList<Food>();
        searchResults = new ArrayList<Food>();

        childEventListener = new ChildEventListener() {

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

        listAdapter = new FoodAdapter(this, searchResults);

        ListView results = findViewById(R.id.listViewResults);
        results.setAdapter(listAdapter);

        results.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Food selectedItem = (Food) parent.getItemAtPosition(position);

                myRef.child(selectedItem.getUid()).removeValue();

                foodList.remove(selectedItem);

                refresh(selectedItem.getName());

            }
        });
    }

    public void refresh(String update) {
        listAdapter.clear();
        for (Food c : foodList) {
            if (c.getName().equalsIgnoreCase(update)) {
                listAdapter.add(c);
            }
        }
    }

    public void removeRecord(View view) {
        listAdapter.clear();
        boolean found = false;
        EditText text = (EditText) findViewById(R.id.editTextName);
        String search = text.getText().toString();
        for (Food c : foodList) {
            if (c.getName().equalsIgnoreCase(search)) {
                listAdapter.add(c);
                found = true;
            }
        }
        if (!found) {
            Toast.makeText(this, text.getText().toString() + " not found.", Toast.LENGTH_LONG).show();
        }
        text.setText("");
    }

    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
