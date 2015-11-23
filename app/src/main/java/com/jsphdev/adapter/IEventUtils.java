package com.jsphdev.adapter;

import com.jsphdev.abstrct.Event;
import com.jsphdev.abstrct.User;

/**
 * Created by vikramn on 11/13/15.
 */
public interface IEventUtils {

    public boolean createEvent();
    public boolean modifyEvent(Event e);
    public boolean deleteEventFromUser(Event event, User user);

}
