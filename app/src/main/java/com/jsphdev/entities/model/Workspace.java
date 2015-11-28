package com.jsphdev.entities.model;

import android.content.Context;

import com.jsphdev.abstrct.User;

/**
 * Created by vikramn on 11/26/15.
 */
public class Workspace {

    private static Workspace workspace;
    private User currentUser;
    private boolean isStudent;
    private Context currContext;

    public Workspace(){
        this.isStudent=false;
    }

    public static Workspace get_instance(){
        if(workspace==null)
            workspace = new Workspace();
        return workspace;
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

}
