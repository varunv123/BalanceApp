package com.jsphdev.abstrct;

import com.jsphdev.entities.model.Calendar;
import com.jsphdev.entities.model.Profile;

import java.util.ArrayList;

/**
 * Created by vikramn on 11/13/15.
 */
public abstract class User {
    protected String name;
    protected int identifier;
    protected Profile profile;
    protected Calendar calendar;
    static protected ArrayList<User> allUsers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    abstract public void deleteEvent(Event event);

}
