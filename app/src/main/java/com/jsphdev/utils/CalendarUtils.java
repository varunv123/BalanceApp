package com.jsphdev.utils;

import java.util.Date;
import java.util.List;

import com.jsphdev.abstrct.Event;
import com.jsphdev.abstrct.User;
import com.jsphdev.adapter.ICalendarUtils;
import com.jsphdev.entities.model.Location;

/**
 * Created by vikramn on 11/14/15.
 */
public class CalendarUtils implements ICalendarUtils {
    @Override
    public List<Event> getAllEvents() {
        return null;
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
    public List<Event> getEventByName(String name) {
        return null;
    }

    @Override
    public List<Event> getEventByLocation(Location location) {
        return null;
    }
}
