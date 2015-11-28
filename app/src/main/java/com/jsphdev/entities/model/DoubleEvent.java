package com.jsphdev.entities.model;

import com.jsphdev.abstrct.Event;
import com.jsphdev.abstrct.User;

import java.util.Date;

/**
 * Created by vikramn on 11/13/15.
 */
public class DoubleEvent extends Event {

    private User secondUser;
    private User created_by;

    public DoubleEvent(String name, int identifier, Date startDate, Date endDate,Location location){
        super(name, identifier, startDate, endDate,location);
    }

    public void setCreated_by(User user){
        this.created_by = user;
    }

    @Override
    public void deleteEvent() {

    }
}
