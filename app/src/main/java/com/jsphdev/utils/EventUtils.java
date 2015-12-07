package com.jsphdev.utils;

import android.content.Context;

import com.jsphdev.abstrct.Event;
import com.jsphdev.abstrct.User;
import com.jsphdev.adapter.IEventUtils;
import com.jsphdev.database.DatabaseIO;
import com.jsphdev.entities.model.Calendar;
import com.jsphdev.entities.model.DoubleEvent;
import com.jsphdev.entities.model.Location;
import com.jsphdev.entities.model.Profile;
import com.jsphdev.entities.model.Workspace;
import com.jsphdev.exception.InvalidInputException;
import com.jsphdev.model.Log;
import com.jsphdev.ws.remote.EventService;
import com.jsphdev.ws.remote.UserService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
     public boolean createDBDoubleEvent(int identifier, String name, int creatorId, String locationName, double latitude, double longitude,
                                      String starttime, String endtime, Calendar calendar) {

        try {

            Location location = new Location();
            location.setxCoordinate(latitude);
            location.setyCoordinate(longitude);
            location.setName(locationName);

            Date startDate = CalendarUtils.get_instance().getDateObject(starttime);
            Date endDate = CalendarUtils.get_instance().getDateObject(endtime);

            DoubleEvent doubleEvent = new DoubleEvent(name, startDate, endDate, location);
            doubleEvent.setIdentifier(identifier);

            if (creatorId == Workspace.get_instance().getCurrentUser().getIdentifier())
                calendar.createEvent(doubleEvent);
            else
                calendar.registerEvent(doubleEvent, Workspace.get_instance().getCurrContext());
        }
        catch (InvalidInputException e){
            return false;
        }
        return true;
    }

    @Override
    public Event createTempDoubleEvent(int identifier,String name, int creatorId, String locationName, double latitude, double longitude,
                                       String starttime, String endtime) {

        try {

            Location location = new Location();
            location.setxCoordinate(latitude);
            location.setyCoordinate(longitude);
            location.setName(locationName);

            Date startDate = CalendarUtils.get_instance().getDateObject(starttime);
            Date endDate = CalendarUtils.get_instance().getDateObject(endtime);
            DoubleEvent doubleEvent = new DoubleEvent(name, startDate, endDate, location);
            doubleEvent.setIdentifier(identifier);
            return doubleEvent;
        }
        catch (InvalidInputException e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Event createBaseDoubleEvent(String name, int creatorId, Location location,
                                     String starttime, String endtime) {

        try {
            Date startDate = CalendarUtils.get_instance().getBalDateObject(starttime);
            Date endDate = CalendarUtils.get_instance().getBalDateObject(endtime);
            DoubleEvent doubleEvent = new DoubleEvent(name, startDate, endDate, location);
            return doubleEvent;
        }
        catch (InvalidInputException e){
            return null;
        }
    }

    public void createEvent(Event event) {
        EventService eventService = new EventService();
        eventService.createRemoteEvent(event);
    }

    public void getLatestEvents(){
        EventService eventService = new EventService();
        eventService.getLatestEvents();
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

        EventService eventService = new  EventService();
        eventService.registerRemoteEvent(eventid,userid);

        //Varun please change this to make it work for local DB

//        DatabaseIO dbIO = new DatabaseIO(context);
//        Log log = new Log("Registering user for event","Insert Log","Registering useid: " +
//                userid + ", for eventid: " + eventid);
//        dbIO.insertLogData(log);
//        return dbIO.registerUserForEvent(eventid, userid);
        return true;
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
