package com.jsphdev.adapter;

import java.util.Date;
import java.util.List;

import com.jsphdev.abstrct.Event;

/**
 * Created by vikramn on 11/13/15.
 */
public interface ICalendar {
    public boolean registerEvent(Event event);
    public boolean deRegisterEvent(Event event);
    public List<Event> getEventsOfDay(Date currDate);
    public List<Event> getEventsOfMonth(Date currDate);
    public List<Event> getEventsOfYear(Date currDate);
    public void refreshCalendar();
}

