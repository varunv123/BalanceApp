package com.jsphdev.abstrct;

import com.jsphdev.entities.model.Calendar;
import com.jsphdev.entities.model.Profile;

import java.util.ArrayList;

/**
 * Created by vikramn on 11/13/15.
 */
public abstract class User {
    protected int identifier;
    protected int localIdentifier;
    protected Profile profile;
    protected Calendar calendar;
    static protected ArrayList<User> allUsers;

    public int getIdentifier() {
        return identifier;
    }

    public int getLocalIdentifier () { return localIdentifier;}

    public void setLocalIdentifier(int identifier) { this.localIdentifier = localIdentifier; }

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
