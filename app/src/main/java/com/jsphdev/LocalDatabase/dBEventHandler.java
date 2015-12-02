package com.jsphdev.LocalDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.android.gms.analytics.ecommerce.Product;
import com.jsphdev.abstrct.Event;
import com.jsphdev.entities.model.DoubleEvent;
import com.jsphdev.entities.model.Location;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Varun on 11/26/15.
 */
public class dBEventHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "eventDB.db";
    public static final String TABLE_EVENTS = "events";

    public static final String COLUMN_EVENTNAME = "eventname";
    public static final String COLUMN_IDENTTIFIER = "eventidentifier";
    public static final String COLUMN_STARTDATE = "eventstartdate";
    public static final String COLUMN_ENDDATE = "eventstopdate";
    public static final String COLUMN_LATITUDE = "eventlatitude";
    public static final String COLUMN_LONGITUDE = "eventlongitude";

    public dBEventHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EVENTS_TABLE = "CREATE TABLE " +
                TABLE_EVENTS + "("
                + COLUMN_EVENTNAME
                + " TEXT," + COLUMN_IDENTTIFIER + " INTEGER" + COLUMN_STARTDATE
                + " TEXT," + COLUMN_ENDDATE + " TEXT," + COLUMN_LATITUDE
                + " REAL," + COLUMN_LONGITUDE + " REAL" + ")";

        db.execSQL(CREATE_EVENTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        onCreate(db);
    }

    public void addEvent(Event event){
        ContentValues values = new ContentValues();
        values.put(COLUMN_EVENTNAME, event.getName());
        values.put(COLUMN_IDENTTIFIER, event.getIdentifier());
        values.put(COLUMN_STARTDATE, event.getStartDate().toString());
        values.put(COLUMN_IDENTTIFIER, event.getEndDate().toString());
        values.put(COLUMN_LATITUDE, event.getLocation().getxCoordinate());
        values.put(COLUMN_LONGITUDE, event.getLocation().getxCoordinate());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_EVENTS, null, values);
        db.close();
    }

    public Event findEvent(String eventname){
        String query = "SELECT * FROM " + TABLE_EVENTS + " WHERE " + COLUMN_EVENTNAME + " = \"" + eventname + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        String name;
        int identifier;
        String startDateStr;
        String endDateStr;
        Date startDate = null;
        Date endDate = null;
        Location location = null;
        Event event = null;
        double xLocation,yLocation;
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            identifier = (Integer.parseInt(cursor.getString(0)));
            name = (cursor.getString(1));
            startDateStr = cursor.getString(2);
            DateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:mm", Locale.ENGLISH);
            try {
                startDate = format.parse(startDateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            endDateStr = cursor.getString(3);
            try {
                endDate = format.parse(endDateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            xLocation = cursor.getDouble(4);
            yLocation = cursor.getDouble(5);
            location = new Location(xLocation,yLocation);
            if (startDate != null && endDate != null){
                event = new DoubleEvent(name,startDate,endDate,location);
                event.setIdentifier(identifier);
            }
        }
        return event;
    }

}
