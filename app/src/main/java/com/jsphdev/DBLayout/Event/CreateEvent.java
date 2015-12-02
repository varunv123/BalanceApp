package com.jsphdev.DBLayout.Event;

import android.content.ContentValues;
import android.content.Context;

import com.jsphdev.abstrct.Event;
import com.jsphdev.database.DatabaseHelper;
import com.jsphdev.entities.model.Workspace;

/**
 * Created by vikramn on 11/13/15.
 */
public class CreateEvent {

    public static final String TABLE_EVENTS = "event";

    public static final String COLUMN_EVENTNAME = "eventname";
    public static final String COLUMN_STARTDATE = "eventstartdate";
    public static final String COLUMN_ENDDATE = "eventstopdate";
    public static final String COLUMN_LATITUDE = "eventlatitude";
    public static final String COLUMN_LONGITUDE = "eventlongitude";
    public static final String COLUMN_CREATORID = "creator_id";

    public boolean createEvent(Event event,Context context){
        System.out.println("In createEvent, creating event");
        ContentValues values = new ContentValues();
        values.put(COLUMN_EVENTNAME, event.getName());
        values.put(COLUMN_STARTDATE, event.getStartDate().toString());
        values.put(COLUMN_ENDDATE, event.getEndDate().toString());
        values.put(COLUMN_LATITUDE, event.getLocation().getxCoordinate());
        values.put(COLUMN_LONGITUDE, event.getLocation().getyCoordinate());
        values.put(COLUMN_CREATORID, Workspace.get_instance().getCurrentUser().getIdentifier());
        System.out.println("In createEvent, inserting event into table");
        return DatabaseHelper.get_instance(context).insertValueToTable(TABLE_EVENTS,values);
    }
}
