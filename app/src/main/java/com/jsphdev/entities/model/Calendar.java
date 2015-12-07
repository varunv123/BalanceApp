package com.jsphdev.entities.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jsphdev.abstrct.Event;
import com.jsphdev.adapter.ICalendar;
import com.jsphdev.database.DatabaseIO;
import com.jsphdev.model.Log;

/**
 * Created by vikramn on 11/13/15.
 */
public class Calendar implements ICalendar {

    private List<Event> eventList;

    public Calendar(){
        eventList = new ArrayList<Event>();
    }

    @Override
    public boolean registerEvent(Event event,Context context) {
        System.out.println("In register event");
        try {
            this.eventList.add(event);
            System.out.println("In Calendar, creating dbIO");
            DatabaseIO dbIO = new DatabaseIO(context);
            System.out.println("In Calendar, opening dbIO");
            dbIO.open();
            System.out.println("In Calendar, opened dbIO");
            System.out.println("In Calendar, trying to reg event dbIO");
            Log log = new Log("Create Event Log","Insert Log","Created Event: " + event.getName());
            dbIO.insertLogData(log);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean createEvent(Event event) {
        System.out.println("In create event");
        try {
            this.eventList.add(event);
            DatabaseIO dbIO = new DatabaseIO(Workspace.get_instance().getCurrContext());
            dbIO.open();
            Log log = new Log("Create Event Log","Insert Log","Created Event: " + event.getName());
            dbIO.insertLogData(log);
            return dbIO.createEvent(event);
        } catch (Exception e){
            e.printStackTrace();
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

    public List<Event> getEventList(){ return eventList; }

    public Event getEventFromList(int eventId){
        for(Event event:eventList){
            if(event.getIdentifier()==eventId)
                return event;
        }
        return null;
    }

    public boolean isEventRegistered(Event event){
        for(Event e:eventList){
            if(e.getIdentifier()==event.getIdentifier())
                return true;
        }
        return false;
    }
}
