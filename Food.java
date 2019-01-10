package com.example.kevinctran.a45j_application;

/*
Cass Tao was the driver (typing code) for this page
Kevin Tran was the observer/navigator

Pair programming done in person on every page
 */

public class Food {
    public String name;
    private String calories;
    private String uid;
    private Location foodLocation;

    public Food()
    {
        name ="NA";
        calories="NA";
        foodLocation = new Location();
    }

    public Food( String name, String number, String uid, Location location )
    {
        this.name = name;
        this.calories = number;
        this.uid = uid;
        this.foodLocation = location;
    }

    public Food(Food f)
    {
        this.name = f.name;
        this.calories = f.calories;
        this.uid = f.uid;
        this.foodLocation = f.foodLocation;
    }

    public String getName()
    {
        return name;
    }

    public String getNumber()
    {
        return calories;
    }
    public Location getLocation()
    {
        return foodLocation;
    }
    public String getUid()
    {
        return uid;
    }

    public void setNumber(String n) { this.calories = n; }
    public void setLocation(Location l) { this.foodLocation = l; }
    public void setUid(String u) { this.uid = u; }

    public String toString()
    {
        return name + ": " + calories + " location: " + getLocation().toString();
    }
}
