package com.jsphdev.adapter;

import android.content.Context;

import java.util.Date;
import java.util.List;

import com.jsphdev.abstrct.Event;
import com.jsphdev.abstrct.User;
import com.jsphdev.entities.model.Location;

/**
 * Created by vikramn on 11/13/15.
 */
public interface ICalendarUtils {

    public List<Event> getAllEvents(Context context);
    public List<Event> getEventByUser(User user);
    public List<Event> getEventByDay(Date currDate);
    public List<Event> getEventByMonth(Date currDate);
    public List<Event> getEventByYear(Date currDate);
    public List<Event> getEventByName(Context context, String name);
    public List<Event> getEventByLocation(Location location);
    public Event getEventById(int identifier,Context context);

}

