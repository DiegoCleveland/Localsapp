package com.example.cleveland.finding;

import android.location.Location;

/**
 * Created by Cleveland on 12-06-2015.
 */

class Local {

    String name;
    Location loc;

    Local(String n, Location a) {
        name = n;
        loc = a;
    }

    @Override
    public String toString() {
        return String.format("{name=%s, loc=%d}, loc=%d", name,(int) loc.getLatitude(),(int)loc.getLongitude());
    }
}

