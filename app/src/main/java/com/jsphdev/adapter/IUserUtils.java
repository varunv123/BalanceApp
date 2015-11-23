package com.jsphdev.adapter;

import com.jsphdev.abstrct.Event;
import com.jsphdev.abstrct.User;

/**
 * Created by vikramn on 11/13/15.
 */
public interface IUserUtils {

    public boolean isEventRegistered(Event e);
    public void registerUser(String username, String password, User user) throws Exception;
    public void registerProfile(User user) throws Exception;

}
