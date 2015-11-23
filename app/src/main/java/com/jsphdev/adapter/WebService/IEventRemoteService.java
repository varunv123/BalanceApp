package com.jsphdev.adapter.WebService;

import java.util.Date;
import java.util.List;

import com.jsphdev.abstrct.Event;
import com.jsphdev.abstrct.User;

/**
 * Created by vikramn on 11/13/15.
 */
public interface IEventRemoteService {

    public int createRemoteEvent(Event event);
    public void deleteRemoteEvent(Event event);
    public void registerRemoteEven(Event event, User user);
    public void deRegisterRemoteEvent(Event event, User user);
    public List<Event> getAllRemoteEvents();
    public List<Event> getAllRegisteredRemoteEvents(User user);
    public List<Event> getRemoteEventsByDay(Date currDate);
    public List<Event> getRemoteEventsByMonth(Date currDate);
    public List<Event> getRemoteEventsByYear(Date currDate);

    public List<Event> getRemoteEventsByDay(Date currDate, User user);
    public List<Event> getRemoteEventsByMonth(Date currDate, User user);
    public List<Event> getRemoteEventsByYear(Date currDate, User user);


}
