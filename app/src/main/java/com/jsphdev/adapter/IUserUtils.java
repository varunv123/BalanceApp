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
    public void registerUser(String username, String password, User user,Context context) throws Exception;
    public void registerProfile(User user,Profile profile,Context context) throws Exception;
    public Profile getProfile(String identifier, Context context) throws Exception;

}
