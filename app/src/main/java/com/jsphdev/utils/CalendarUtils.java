package com.jsphdev.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.google.android.gms.maps.model.LatLng;
import com.jsphdev.abstrct.Event;
import com.jsphdev.abstrct.User;
import com.jsphdev.adapter.ICalendarUtils;
import com.jsphdev.database.DatabaseIO;
import com.jsphdev.entities.model.Location;
import com.jsphdev.entities.model.Workspace;
import com.jsphdev.exception.InvalidInputException;
import com.jsphdev.model.Log;

/**
 * Created by vikramn on 11/14/15.
 */
public class CalendarUtils implements ICalendarUtils {

    private static CalendarUtils calUtils;

    public static CalendarUtils get_instance(){
        if(calUtils==null)
            calUtils = new CalendarUtils();
        return calUtils;
    }

    @Override
    public List<Event> getAllEvents(Context context) {
        System.out.println("Initializing dbIO");
        DatabaseIO dbIO = new DatabaseIO(context);
        System.out.println("Getting allEvents from dbIO");
        Log log = new Log("Get All Event Log","Read Log","Read All Events.");
        dbIO.insertLogData(log);
        return dbIO.getAllEvents();
    }

    @Override
    public List<Event> getEventByUser(User user) {
        return null;
    }

    @Override
    public List<Event> getEventByDay(Date currDate) {
        return null;
    }

    @Override
    public List<Event> getEventByMonth(Date currDate) {
        return null;
    }

    @Override
    public List<Event> getEventByYear(Date currDate) {
        return null;
    }

    @Override
    public List<Event> getEventByName(Context context, String name) {
        System.out.println("Initializing dbIO for getEventByName");
        DatabaseIO dbIO = new DatabaseIO(context);
        System.out.println("Getting allEventByName: + " + name + " from dbIO");
        Log log = new Log("Search Event Log","Read Log","Searched Event by name: " + name);
        dbIO.insertLogData(log);
        return dbIO.getEventByName(name);
    }

    public Location getLocationObject(String eventLocation,LatLng position){
        com.jsphdev.entities.model.Location location = new com.jsphdev.entities.model.Location();
        location.setxCoordinate(position.latitude);
        location.setyCoordinate(position.longitude);
        location.setName(eventLocation);
        return location;
    }



    @Override
    public List<Event> getEventByLocation(Location location) {
        return null;
    }

    @Override
    public Event getEventById(int identifier,Context context){
        System.out.println("Initializing dbIO for getEventById");
        DatabaseIO dbIO = new DatabaseIO(context);
        System.out.println("Getting allEventById: + " + identifier + " from dbIO");
        Log log = new Log("Search Event Log","Read Log","Searched Event by id: " + identifier);
        dbIO.insertLogData(log);
        return dbIO.getEventById(identifier);
    }


    public Date getBalDateObject (String dateTime) throws InvalidInputException {
        Date date = null;
        try{
            DateFormat format = new SimpleDateFormat("dd/MM/yy,HH:mm", Locale.ENGLISH);
            date = format.parse(dateTime);
        }catch (java.text.ParseException e) {
            e.printStackTrace();
            throw new InvalidInputException("Invalid date input");
        }
        return date;
    }

    public Date getDateObject (String dateTime) throws InvalidInputException {
        Date date = null;
        try{
            DateFormat format = new SimpleDateFormat("dd-MM-yy HH:mm:ss", Locale.ENGLISH);
            date = format.parse(dateTime);
        }catch (java.text.ParseException e) {
            e.printStackTrace();
            throw new InvalidInputException("Invalid date input");
        }
        return date;
    }

    public String getDateStrng (Date date){
        DateFormat format = new SimpleDateFormat("dd/MM/yy,HH:mm", Locale.ENGLISH);
        return format.format(date);
    }


}
