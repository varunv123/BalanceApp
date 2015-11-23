package com.jsphdev.utils;

import com.jsphdev.abstrct.Event;
import com.jsphdev.abstrct.User;
import com.jsphdev.adapter.IEventUtils;

/**
 * Created by vikramn on 11/14/15.
 */
public class EventUtils implements IEventUtils {

    @Override
    public boolean createEvent() {
        return false;
    }

    @Override
    public boolean modifyEvent(Event e) {
        return false;
    }

    @Override
    public boolean deleteEventFromUser(Event event, User user) {
        return false;
    }
}
