package com.jsphdev.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.jsphdev.DBLayout.Event.CreateEvent;
import com.jsphdev.DBLayout.Event.SearchEvent;
import com.jsphdev.DBLayout.Profile.CreateProfile;
import com.jsphdev.DBLayout.Profile.SearchProfile;
import com.jsphdev.DBLayout.User.CreateUser;
import com.jsphdev.abstrct.Event;
import com.jsphdev.abstrct.User;
import com.jsphdev.entities.model.Profile;
import com.jsphdev.exception.CustomReadException;
import com.jsphdev.model.Log;

/**
 * Created by vikramn on 11/13/15.
 */
public class DatabaseIO {

    public static final String TABLE_EVENTS = "events";

    public static final String COLUMN_EVENTNAME = "eventname";
    public static final String COLUMN_IDENTTIFIER = "eventidentifier";
    public static final String COLUMN_STARTDATE = "eventstartdate";
    public static final String COLUMN_ENDDATE = "eventstopdate";
    public static final String COLUMN_LATITUDE = "eventlatitude";
    public static final String COLUMN_LONGITUDE = "eventlongitude";
    private Context context;

    public DatabaseIO(Context context)
    {
        this.context = context;
    }

    public void open() throws SQLException
    {
        System.out.println("In dbIO, tring to open dbHelper");
        DatabaseHelper.get_instance(context).open();
    }

    public void close()
    {
        DatabaseHelper.get_instance(context).close();
    }

    public boolean insertLogData(Log logs) {
        try {
            ContentValues newLogValue = new ContentValues();
            newLogValue.put("log_name", logs.getName());
            newLogValue.put("log_type", logs.getType());
            newLogValue.put("log_message", logs.getMessage());
            return DatabaseHelper.get_instance(context).insertValueToTable("logs",newLogValue);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void deleteDB() {
        try {
            DatabaseHelper.get_instance(context).deleteTable("logs");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Log> getAllLogs() throws CustomReadException {
        ArrayList<Log> logList = new ArrayList<>();
        open();

        Cursor cursor = DatabaseHelper.get_instance(context).doLogQuery("logs", new String[]{"_id", "log_name", "log_type",
                "log_message"}, null, null, null, null, "_id");


        while (cursor.moveToNext()) {
            String name =  cursor.getString(cursor.getColumnIndex("log_name"));
            String type =  cursor.getString(cursor.getColumnIndex("log_type"));
            String message =  cursor.getString(cursor.getColumnIndex("log_message"));
            logList.add(new Log(name,type,message));
        }
        return logList;
    }

    public boolean registerEvent(Event event){
        System.out.println("In dbIO, registering");
        CreateEvent createEvent = new CreateEvent();
        return createEvent.createEvent(event,context);
    }

    public List<Event> getAllEvents(){
        SearchEvent searchEvent = new SearchEvent();
        System.out.println("In dbIO, calling getAllEvents in SearchEvents ");
        return searchEvent.getAllEvents(context);
    }

    public List<Event> getEventByName(String name){
        SearchEvent searchEvent = new SearchEvent();
        System.out.println("In dbIO, calling getEventByName in SearchEvents ");
        return searchEvent.getEventByName(context, name);
    }

    public boolean registerUser(String username, String password, User user){
        CreateUser createUser = new CreateUser();
        return createUser.createUser(username, password, user, context);
    }

    public boolean registerProfile(Profile profile){
        CreateProfile createProfile = new CreateProfile();
        return createProfile.createProfile(profile, context);
    }

    public Profile getProfile(String identifier){
        SearchProfile searchProfile = new SearchProfile();
        return searchProfile.searchProfile(identifier,context);
    }


}
