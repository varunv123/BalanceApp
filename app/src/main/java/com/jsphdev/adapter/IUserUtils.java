package com.jsphdev.adapter;

import android.content.Context;

import com.jsphdev.abstrct.Event;
import com.jsphdev.abstrct.User;
import com.jsphdev.entities.model.Profile;

/**
 * Created by vikramn on 11/13/15.
 */
public interface IUserUtils {

    public boolean isEventRegistered(Event e);
    public void verifyUser(String username, String password) throws Exception;
    public User registerUser(String username, String password, User user) throws Exception;
    public User createUser(String firstName, String lastName, String andrewId, String department,
                           String emailId,String phoneNo);
    public void registerUser(String username, String password, User user,Context context) throws Exception;
    public void registerProfile(User user,Profile profile,Context context) throws Exception;
    public Profile getProfile(String identifier, Context context) throws Exception;
}
