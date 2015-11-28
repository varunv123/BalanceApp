package com.jsphdev.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jsphdev.abstrct.Event;
import com.jsphdev.entities.model.DoubleEvent;
import com.jsphdev.entities.model.Location;
import com.jsphdev.entities.model.Profile;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by vikramn on 11/13/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper dbHelper;
    private static final String DATABASE_NAME="LOGDB";
    public static final String TABLE_EVENTS = "event";
    public static final String TABLE_USERS = "user";
    public static final String TABLE_PROFILES = "profile";


    public static final String COLUMN_EVENTNAME = "eventname";
    public static final String COLUMN_IDENTTIFIER = "eventidentifier";
    public static final String COLUMN_STARTDATE = "eventstartdate";
    public static final String COLUMN_ENDDATE = "eventstopdate";
    public static final String COLUMN_LATITUDE = "eventlatitude";
    public static final String COLUMN_LONGITUDE = "eventlongitude";

    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "userpassword";

    public static final String COLUMN_FIRSTNAME = "firstname";
    public static final String COLUMN_LASTNAME = "lastname";
    public static final String COLUMN_DEPARTMENT = "department";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONENO = "phoneno";
    public static final String COLUMN_ANDREWID = "andrewif";
    public static final String COLUMN_PROFILEPIC = "profilepic";
    public static final String COLUMN_USERID = "user_id";


    public static DatabaseHelper get_instance(Context context){
        if (dbHelper==null) {
            dbHelper = new DatabaseHelper(context, DATABASE_NAME, null, 6);
            System.out.println("Created DB");
        }
        System.out.println(dbHelper);
        return dbHelper;
    }

    public DatabaseHelper(Context context, String name,
                          SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createTableQuery = "CREATE TABLE logs" +
                "(_id integer primary key autoincrement," +
                "log_name text, log_type text, log_message text);";
        db.execSQL(createTableQuery);
        String CREATE_EVENTS_TABLE = "CREATE TABLE " +
                TABLE_EVENTS + "( ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_EVENTNAME
                + " TEXT," + COLUMN_IDENTTIFIER + " INTEGER," + COLUMN_STARTDATE
                + " TEXT," + COLUMN_ENDDATE + " TEXT," + COLUMN_LATITUDE
                + " REAL," + COLUMN_LONGITUDE + " REAL" + ")";
        db.execSQL(CREATE_EVENTS_TABLE);
        String CREATE_USERS_TABLE = "CREATE TABLE " +
                TABLE_USERS + "( ID INTEGER PRIMARY KEY, "
                + COLUMN_USERNAME
                + " TEXT," + COLUMN_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);
        String CREATE_PROFILE_TABLE = "CREATE TABLE " +
                TABLE_PROFILES + "( ID INTEGER PRIMARY KEY, "
                + COLUMN_FIRSTNAME
                + " TEXT, " + COLUMN_LASTNAME + " TEXT, "
                + COLUMN_DEPARTMENT + " TEXT, " + COLUMN_EMAIL + " TEXT,"
                + COLUMN_PHONENO + " TEXT,"
                + COLUMN_ANDREWID + " TEXT,"
                + COLUMN_PROFILEPIC + " TEXT,"
                + "FOREIGN KEY(ID) REFERENCES "
                + TABLE_USERS + "(ID) "+ ")";
        System.out.println(CREATE_PROFILE_TABLE);
        db.execSQL(CREATE_PROFILE_TABLE);
        System.out.println("Created Table");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILES);
        db.execSQL("DROP TABLE IF EXISTS logs");
        onCreate(db);
    }

    public void open(){
        System.out.println(dbHelper);
        dbHelper.getWritableDatabase();
    }

    public void close(){
        if (dbHelper!=null)
            dbHelper.getWritableDatabase().close();
    }

    public boolean insertValueToTable(String table, ContentValues values){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.insert(table,null,values);
        database.close();
        System.out.println("In dbHelper, inserted into " + table);
        return true;
    }

    public boolean deleteTable(String table){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(table, null, null);
        database.close();
        return true;
    }

    public Cursor doLogQuery(String table, String[] columns, String selection,
                             String[] selectionArgs, String groupBy, String having,
                             String orderBy){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query("logs", new String[]{"_id", "log_name", "log_type",
                "log_message"}, null, null, null, null, "_id");
        return cursor;
    }

    public List<Event> getAllEvents(){
        System.out.println("DbHelper, getting all events.");
        String query = "SELECT * FROM " + TABLE_EVENTS + ";";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        String name;
        int identifier;
        String startDateStr;
        String endDateStr;
        Date startDate = null;
        Date endDate = null;
        Location location = null;
        Event event = null;
        List<Event> events = new ArrayList<Event>();
        double xLocation,yLocation;
        while (cursor.moveToNext()) {
            name = (cursor.getString(0));
            identifier = (Integer.parseInt(cursor.getString(1)));
            startDateStr = cursor.getString(2);
            DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
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
            System.out.println(startDate.toString());
            System.out.println(endDate.toString());
            xLocation = cursor.getDouble(4);
            yLocation = cursor.getDouble(5);
            location = new Location(xLocation,yLocation);
            if (startDate != null && endDate != null){
                event = new DoubleEvent(name,identifier,startDate,endDate,location);
                events.add(event);
                event = null;
            }
        }
        System.out.println("DbHelper, got all events.");
        return events;
    }

    public List<Event> getEventByName(String query){
        System.out.println("DbHelper, getting all events.");
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        String name;
        int identifier;
        String startDateStr;
        String endDateStr;
        Date startDate = null;
        Date endDate = null;
        Location location = null;
        Event event = null;
        List<Event> events = new ArrayList<Event>();
        double xLocation,yLocation;
        while (cursor.moveToNext()) {
            name = (cursor.getString(1));
            identifier = (Integer.parseInt(cursor.getString(2)));
            startDateStr = cursor.getString(3);
            DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
            try {
                startDate = format.parse(startDateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            endDateStr = cursor.getString(4);
            try {
                endDate = format.parse(endDateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(startDate.toString());
            System.out.println(endDate.toString());
            xLocation = cursor.getDouble(5);
            yLocation = cursor.getDouble(6);
            location = new Location(xLocation,yLocation);
            if (startDate != null && endDate != null){
                event = new DoubleEvent(name,identifier,startDate,endDate,location);
                events.add(event);
                event = null;
            }
        }
        System.out.println("DbHelper, searched by event name.");
        return events;
    }

    public Profile getProfileByIdentifier(String query){
        System.out.println("DbHelper, getting all events.");
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Profile profile = new Profile();
        if (cursor.moveToFirst()){
            cursor.moveToFirst();
            profile.setFirstName(cursor.getString(1));
            profile.setLastName(cursor.getString(2));
            profile.setDepartment(cursor.getString(3));
            profile.setEmail(cursor.getString(4));
            profile.setPhoneNo(cursor.getString(5));
            cursor.close();
        }
        else
            profile = null;
        return profile;
    }

}
