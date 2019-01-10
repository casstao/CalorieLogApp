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


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addFood( View view)
    {
        Intent intent = new Intent( this, AddActivity.class);
        startActivity(intent);
    }

    public void viewFood(View view)
    {
        Intent intent = new Intent(this, ViewActivity.class);
        startActivity( intent );
    }

    public void searchFoods(View view)
    {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity( intent );
    }

    public void removeFood(View view )
    {
        Intent intent = new Intent(this, RemoveActivity.class);
        startActivity( intent);
    }

    public void updateFood(View view)
    {
        Intent intent = new Intent( this, UpdateActivity.class);
        startActivity(intent);
    }
    public void CalculateCalories(View view)
    {
        Intent intent = new Intent( this, CalculateCalories.class);
        startActivity(intent);
    }
}
