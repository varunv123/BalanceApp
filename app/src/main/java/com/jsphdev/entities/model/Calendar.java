package com.jsphdev.entities.model;

import java.util.Date;
import java.util.List;

import com.jsphdev.abstrct.Event;
import com.jsphdev.adapter.ICalendar;

/**
 * Created by vikramn on 11/13/15.
 */
public class Calendar implements ICalendar {

    private List<Event> eventList;

    @Override
    public boolean registerEvent(Event event) {
        try {
            this.eventList.add(event);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deRegisterEvent(Event event) {
        try {
            this.eventList.remove(event);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Event> getEventsOfDay(Date currDate) {
        return null;
    }

    @Override
    public List<Event> getEventsOfMonth(Date currDate) {
        return null;
    }

    @Override
    public List<Event> getEventsOfYear(Date currDate) {
        return null;
    }

    @Override
    public void refreshCalendar(){};
}
