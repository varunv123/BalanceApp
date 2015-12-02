package com.jsphdev.abstrct;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

import com.jsphdev.abstrct.User;
import com.jsphdev.entities.model.Location;

/**
 * Created by vikramn on 11/13/15.
 */

public abstract class Event implements Serializable{
    protected String name;
    protected int identifier;
    protected Date startDate;
    protected Date endDate;
    protected Location location;
    protected int createridentifier;

    public Event(){

    }

    public Event(String name, Date startDate, Date endDate,Location location){
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
    }

    public String getName(){
        return this.name;
    }

    public int getIdentifier(){
        return this.identifier;
    }

    public Date getStartDate(){
        return this.startDate;
    }

    public Date getEndDate(){
        return this.endDate;
    }

    public Location getLocation(){
        return this.location;
    }

    public void setIdentifier(int identifier){
        this.identifier = identifier;
    }


    abstract public void deleteEvent();

    public void updateEvent(Location newLocation){
        this.location = newLocation;
    }

    public void setCreateridentifier(int createridentifier){
        this.createridentifier = createridentifier;
    }

    public int getCreateridentifier(){
        return this.createridentifier;
    }


    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Event Name: " + this.name + ", "
                + "Event Start Date: " + this.startDate.toString() + ", "
                + "Event End Date: " + this.endDate.toString() + ", "
                + "Event Location: (" + this.location.getxCoordinate() + "," + this.location.getyCoordinate() + ")");
        return sb.toString();
    }





}
