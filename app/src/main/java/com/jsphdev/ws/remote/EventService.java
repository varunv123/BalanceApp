package com.jsphdev.ws.remote;

import java.util.Date;
import java.util.List;

import com.jsphdev.abstrct.Event;
import com.jsphdev.abstrct.User;
import com.jsphdev.adapter.WebService.IEventRemoteService;

/**
 * Created by vikramn on 11/14/15.
 */
public class EventService implements IEventRemoteService {

    @Override
    public int createRemoteEvent(Event event) {
        return 0;
    }

    @Override
    public void deleteRemoteEvent(Event event) {

    }

    @Override
    public void registerRemoteEven(Event event, User user) {

    }

    @Override
    public void deRegisterRemoteEvent(Event event, User user) {

    }

    @Override
    public List<Event> getAllRemoteEvents() {
        return null;
    }

    @Override
    public List<Event> getAllRegisteredRemoteEvents(User user) {
        return null;
    }

    @Override
    public List<Event> getRemoteEventsByDay(Date currDate) {
        return null;
    }

    @Override
    public List<Event> getRemoteEventsByMonth(Date currDate) {
        return null;
    }

    @Override
    public List<Event> getRemoteEventsByYear(Date currDate) {
        return null;
    }

    @Override
    public List<Event> getRemoteEventsByDay(Date currDate, User user) {
        return null;
    }

    @Override
    public List<Event> getRemoteEventsByMonth(Date currDate, User user) {
        return null;
    }

    @Override
    public List<Event> getRemoteEventsByYear(Date currDate, User user) {
        return null;
    }
}
