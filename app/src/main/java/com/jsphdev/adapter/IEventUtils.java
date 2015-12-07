package com.jsphdev.adapter;

import android.content.Context;

import com.jsphdev.abstrct.Event;
import com.jsphdev.abstrct.User;
import com.jsphdev.entities.model.Calendar;
import com.jsphdev.entities.model.Location;
import com.jsphdev.entities.model.Profile;

import java.util.List;

/**
 * Created by vikramn on 11/13/15.
 */
public interface IEventUtils {

    public boolean createDBDoubleEvent(int identifier,String name, int creatorId, String locationName, double latitude, double longitude,
                      String starttime, String endtime, Calendar calendar);
    public Event createBaseDoubleEvent(String name, int creatorId, Location location,
                                     String starttime, String endtime);

    public Event createTempDoubleEvent(int identifier,String name, int creatorId, String locationName, double latitude, double longitude,
                                       String starttime, String endtime);

    public boolean modifyEvent(Event e);
    public boolean deleteEventFromUser(Event event, User user);
    public List<Profile> getProfilesRegisteredForEvent(int eventid,Context context);
    public boolean registerUserForEvent(int eventid, int userid,Context context);
    public boolean unRegisterUserForEvent(int eventid, int userid,Context context);

}
