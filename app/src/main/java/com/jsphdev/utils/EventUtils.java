package com.jsphdev.utils;

import android.content.Context;

import com.jsphdev.abstrct.Event;
import com.jsphdev.abstrct.User;
import com.jsphdev.adapter.IEventUtils;
import com.jsphdev.database.DatabaseIO;
import com.jsphdev.entities.model.Profile;
import com.jsphdev.model.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vikramn on 11/14/15.
 */
public class EventUtils implements IEventUtils {

    private static EventUtils eventUtils;

    public static EventUtils get_instance(){
        if(eventUtils==null)
            eventUtils = new EventUtils();
        return eventUtils;
    }

    @Override
    public boolean createEvent() {
        return false;
    }

    @Override
    public boolean modifyEvent(Event e) {
        return false;
    }

    @Override
    public boolean deleteEventFromUser(Event event, User user) {
        return false;
    }

    @Override
    public List<Profile> getProfilesRegisteredForEvent(int eventid,Context context){
        DatabaseIO dbIO = new DatabaseIO(context);
        Log log = new Log("get event profiles","Read Log","Get profiles for eventid: " + eventid);
        dbIO.insertLogData(log);
        return dbIO.getProfilesRegisteredForEvent(eventid);
    }

    @Override
    public boolean registerUserForEvent(int eventid, int userid,Context context){
        DatabaseIO dbIO = new DatabaseIO(context);
        Log log = new Log("Registering user for event","Insert Log","Registering useid: " +
                userid + ", for eventid: " + eventid);
        dbIO.insertLogData(log);
        return dbIO.registerUserForEvent(eventid, userid);

    }

    @Override
    public boolean unRegisterUserForEvent(int eventid, int userid,Context context){
        DatabaseIO dbIO = new DatabaseIO(context);
        Log log = new Log("UnRegistering user for event","Insert Log","UnRegistering userid: " +
                userid + ", for eventid: " + eventid);
        dbIO.insertLogData(log);
        return dbIO.unRegisterUserForEvent(eventid,userid);

    }

}
