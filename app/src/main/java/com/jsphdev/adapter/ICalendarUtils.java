package com.jsphdev.adapter;

import java.util.Date;
import java.util.List;

import com.jsphdev.abstrct.Event;
import com.jsphdev.abstrct.User;
import com.jsphdev.entities.model.Location;

/**
 * Created by vikramn on 11/13/15.
 */
public interface ICalendarUtils {

    public List<Event> getAllEvents();
    public List<Event> getEventByUser(User user);
    public List<Event> getEventByDay(Date currDate);
    public List<Event> getEventByMonth(Date currDate);
    public List<Event> getEventByYear(Date currDate);
    public List<Event> getEventByName(String name);
    public List<Event> getEventByLocation(Location location);

}

