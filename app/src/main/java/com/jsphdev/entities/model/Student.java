package com.jsphdev.entities.model;

import com.jsphdev.abstrct.Event;
import com.jsphdev.abstrct.User;
import com.jsphdev.adapter.IStudent;

/**
 * Created by vikramn on 11/13/15.
 */
public class Student extends User implements IStudent {


    private boolean isBlocked;

    public Student(){
        this.isBlocked=false;
    }

    @Override
    public void deleteEvent(Event event) {

    }
}
