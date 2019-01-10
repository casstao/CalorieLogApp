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
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity {


    private DatabaseReference myRef;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("foods");
    }

    public void addFood(View view)
    {
        EditText editName = findViewById(R.id.editTextName);
        String name = editName.getText().toString();
        EditText editNumber = findViewById(R.id.editTextCalories);
        String phone = editNumber.getText().toString();
        EditText editPlace = findViewById(R.id.editTextPlace);
        String place = editPlace.getText().toString();
        EditText editTime = findViewById(R.id.editTextTime);
        String time = editTime.getText().toString();
        if( name.length() > 0 )
        {
            String key = myRef.push().getKey();
            Location l = new Location(place, time);
            Food c = new Food(name, phone, key, l);
            myRef.child(key).setValue(c);
            Toast.makeText(this, c.getName() + " successfully added.", Toast.LENGTH_LONG).show();
        }


        editName.setText("");
        editNumber.setText("");
        editPlace.setText("");
        editTime.setText("");
    }

    public void goHome( View view )
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity( intent);
    }
}
