package com.jsphdev.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import com.jsphdev.exception.CustomReadException;
import com.jsphdev.model.Log;

/**
 * Created by vikramn on 11/13/15.
 */
public class DatabaseIO {

    private static final String DATABASE_NAME="LOGDB";
    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;

    public DatabaseIO(Context context)
    {
        databaseHelper =  new DatabaseHelper(context, DATABASE_NAME, null, 1);
    }

    public void open() throws SQLException
    {
        database = databaseHelper.getWritableDatabase();
    }

    public void close()
    {
        if (database != null)
            database.close(); // close the database connection
    }

    public boolean insertLogData(Log logs) {
        try {
            ContentValues newLogValue = new ContentValues();
            newLogValue.put("log_name", logs.getName());
            newLogValue.put("log_type", logs.getType());
            newLogValue.put("log_message", logs.getMessage());
            open();
            database.insert("logs", null, newLogValue);
            close();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void deleteDB() {
        try {
            database.delete("logs",null,null);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Log> getAllLogs() throws CustomReadException {
        ArrayList<Log> logList = new ArrayList<>();
        open();
        Cursor cursor = database.query("logs", new String[]{"_id", "log_name", "log_type",
                "log_message"}, null, null, null, null, "_id");

        while (cursor.moveToNext()) {
            String name =  cursor.getString(cursor.getColumnIndex("log_name"));
            String type =  cursor.getString(cursor.getColumnIndex("log_type"));
            String message =  cursor.getString(cursor.getColumnIndex("log_message"));
            logList.add(new Log(name,type,message));
        }
        return logList;
    }

}
