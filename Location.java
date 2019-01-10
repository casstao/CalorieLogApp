package com.example.kevinctran.a45j_application;

/*
Kevin Tran was the driver (typing code) for this page
Cass Tao was the observer/navigator

Pair programming done in person on every page
 */


public class Location {
    private String place;
    public String time;

    public Location()
    {
        place = "";
        time = "";
    }

    public Location(String place, String time)
    {
        this.place = place;
        this.time = time;
    }

    public String getPlace()
    {
        return place;
    }

    public String getTime()
    {
        return time;
    }

    public String toString()
    {
        return getPlace() + " @" + getTime();
    }
}

