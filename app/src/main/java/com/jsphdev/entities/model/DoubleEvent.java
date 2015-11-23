package com.jsphdev.entities.model;

import com.jsphdev.abstrct.Event;
import com.jsphdev.abstrct.User;

/**
 * Created by vikramn on 11/13/15.
 */
public class DoubleEvent extends Event {

    private User secondUser;
    private User created_by;


    @Override
    public void deleteEvent() {

    }
}
