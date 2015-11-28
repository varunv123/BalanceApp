package com.jsphdev.DBLayout.Event;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jsphdev.abstrct.Event;
import com.jsphdev.database.DatabaseHelper;
import com.jsphdev.entities.model.DoubleEvent;
import com.jsphdev.entities.model.Location;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Varun on 11/26/15.
 */
public class SearchEvent {

    public static final String TABLE_EVENTS = "event";

    public static final String COLUMN_EVENTNAME = "eventname";
    public static final String COLUMN_IDENTTIFIER = "eventidentifier";
    public static final String COLUMN_STARTDATE = "eventstartdate";
    public static final String COLUMN_ENDDATE = "eventstopdate";
    public static final String COLUMN_LATITUDE = "eventlatitude";
    public static final String COLUMN_LONGITUDE = "eventlongitude";

    public List<Event> getAllEvents(Context context){
        System.out.println("In SearchEvent, calling getAllEvents in dbHelper");
        return DatabaseHelper.get_instance(context).getAllEvents();
    }

    public List<Event> getEventByName(Context context,String name){
        System.out.println("In SearchEvent, calling getEventByName in dbHelper");
        String query = String.format("SELECT * FROM " + TABLE_EVENTS + " WHERE " + COLUMN_EVENTNAME + " = \'%s\'",name);
        System.out.println(query);
        return DatabaseHelper.get_instance(context).getEventByName(query);
    }
}
