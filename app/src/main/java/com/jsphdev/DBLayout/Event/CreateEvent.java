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

    public boolean createEvent(Event event,Context context, boolean registered){
        System.out.println("In createEvent, creating event");
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_EVENTNAME, event.getName());
        values.put(DatabaseHelper.COLUMN_STARTDATE, event.getStartDate().toString());
        values.put(DatabaseHelper.COLUMN_ENDDATE, event.getEndDate().toString());
        values.put(DatabaseHelper.COLUMN_LOCATION_NAME,event.getLocation().getName());
        values.put(DatabaseHelper.COLUMN_LATITUDE, event.getLocation().getxCoordinate());
        values.put(DatabaseHelper.COLUMN_LONGITUDE, event.getLocation().getyCoordinate());
        if(registered)
            values.put(DatabaseHelper.COLUMN_CREATORID, event.getCreateridentifier());
        else
            values.put(DatabaseHelper.COLUMN_CREATORID, Workspace.get_instance().getCurrentUser().getIdentifier());
        System.out.println("In createEvent, inserting event into table");
        return DatabaseHelper.get_instance(context).insertValueToTable(TABLE_EVENTS,values);
    }
}
