package com.jsphdev.entities.model;

import android.content.Context;

import com.jsphdev.abstrct.Event;
import com.jsphdev.abstrct.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vikramn on 11/26/15.
 */
public class Workspace {

    private static Workspace workspace;
    private User currentUser;
    private boolean isOnline;
    private boolean isStudent;
    private List<Event> latestEvents;
    private Context currContext;

    public Workspace(){
        this.isStudent=false;
        this.latestEvents = new ArrayList<Event>();
    }

    public static Workspace get_instance(){
        if(workspace==null)
            workspace = new Workspace();
        return workspace;
    }

    public List<Event> getLatestEvents(){
        return latestEvents;
    }

    public void addToLatestEvents(Event event){
        this.latestEvents.add(event);
    }

    public void setCurrentUser(User user) {
        System.out.println("Setting the instance");
        this.currentUser = user;
        if (user instanceof Student)
            isStudent = true;
    }

    public void setCurrentContext(Context context){
        this.currContext = context;
    }

    public Context getCurrContext(){
        return currContext;
    }

    public User getCurrentUser(){
        return currentUser;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }
}
