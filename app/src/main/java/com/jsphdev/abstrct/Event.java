package com.jsphdev.abstrct;

import java.util.Date;

import com.jsphdev.abstrct.User;
import com.jsphdev.entities.model.Location;

/**
 * Created by vikramn on 11/13/15.
 */

public abstract class Event {
    protected String name;
    protected int identifier;
    protected Date startDate;
    protected Date endDate;
    protected Location location;

    abstract public void deleteEvent();

    public void updateEvent(Location newLocation){
        this.location = newLocation;
    }



}
