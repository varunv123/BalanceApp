package com.jsphdev.utils;

import android.content.Context;

import java.util.Date;
import java.util.List;

import com.jsphdev.abstrct.Event;
import com.jsphdev.abstrct.User;
import com.jsphdev.adapter.ICalendarUtils;
import com.jsphdev.database.DatabaseIO;
import com.jsphdev.entities.model.Location;
import com.jsphdev.model.Log;

/**
 * Created by vikramn on 11/14/15.
 */
public class CalendarUtils implements ICalendarUtils {
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

    @Override
    public List<Event> getEventByLocation(Location location) {
        return null;
    }
}
