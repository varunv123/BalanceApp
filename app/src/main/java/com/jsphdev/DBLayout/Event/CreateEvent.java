package com.jsphdev.DBLayout.Event;

import android.content.ContentValues;
import android.content.Context;

import com.jsphdev.abstrct.Event;
import com.jsphdev.database.DatabaseHelper;

/**
 * Created by vikramn on 11/13/15.
 */
public class CreateEvent {

    public static final String TABLE_EVENTS = "events";

    public static final String COLUMN_EVENTNAME = "eventname";
    public static final String COLUMN_IDENTTIFIER = "eventidentifier";
    public static final String COLUMN_STARTDATE = "eventstartdate";
    public static final String COLUMN_ENDDATE = "eventstopdate";
    public static final String COLUMN_LATITUDE = "eventlatitude";
    public static final String COLUMN_LONGITUDE = "eventlongitude";

    public boolean createEvent(Event event,Context context){
        System.out.println("In createEvent, creating event");
        ContentValues values = new ContentValues();
        values.put(COLUMN_EVENTNAME, event.getName());
        values.put(COLUMN_IDENTTIFIER, event.getIdentifier());
        values.put(COLUMN_STARTDATE, event.getStartDate().toString());
        values.put(COLUMN_ENDDATE, event.getEndDate().toString());
        values.put(COLUMN_LATITUDE, event.getLocation().getxCoordinate());
        values.put(COLUMN_LONGITUDE, event.getLocation().getyCoordinate());
        System.out.println("In Calendar, inserting event into table");
        return DatabaseHelper.get_instance(context).insertValueToTable(TABLE_EVENTS,values);
    }
}
