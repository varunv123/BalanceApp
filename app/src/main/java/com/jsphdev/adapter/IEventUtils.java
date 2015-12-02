package com.jsphdev.adapter;

import android.content.Context;

import com.jsphdev.abstrct.Event;
import com.jsphdev.abstrct.User;
import com.jsphdev.entities.model.Profile;

import java.util.List;

/**
 * Created by vikramn on 11/13/15.
 */
public interface IEventUtils {

    public boolean createEvent();
    public boolean modifyEvent(Event e);
    public boolean deleteEventFromUser(Event event, User user);
    public List<Profile> getProfilesRegisteredForEvent(int eventid,Context context);
    public boolean registerUserForEvent(int eventid, int userid,Context context);
    public boolean unRegisterUserForEvent(int eventid, int userid,Context context);

}
