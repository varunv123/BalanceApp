package com.jsphdev.entities.model;

import java.util.List;

import com.jsphdev.abstrct.Event;
import com.jsphdev.abstrct.User;

/**
 * Created by vikramn on 11/13/15.
 */
public class GroupEvent extends Event {

    private List<User> userList;


    @Override
    public void deleteEvent() {
        
    }
}
