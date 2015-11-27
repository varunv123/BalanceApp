package com.jsphdev.DBLayout.Profile;

import android.content.ContentValues;
import android.content.Context;

import com.jsphdev.database.DatabaseHelper;
import com.jsphdev.entities.model.Profile;

/**
 * Created by vikramn on 11/13/15.
 */
public class CreateProfile
{

    public static final String TABLE_PROFILES = "profile";
    public static final String COLUMN_FIRSTNAME = "firstname";
    public static final String COLUMN_LASTNAME = "lastname";
    public static final String COLUMN_DEPARTMENT = "department";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONENO = "phoneno";
    public static final String COLUMN_ANDREWID = "andrewif";
    public static final String COLUMN_PROFILEPIC = "profilepic";
    public static final String COLUMN_USERID = "user_id";


    public boolean createProfile(Profile profile,Context context){
        System.out.println("In createProfile, creating Profile");
        ContentValues values = new ContentValues();
        values.put("ID", profile.getIdentifier());
        values.put(COLUMN_FIRSTNAME, profile.getFirstName());
        values.put(COLUMN_LASTNAME, profile.getLastName());
        values.put(COLUMN_EMAIL, profile.getEmail());
        values.put(COLUMN_DEPARTMENT, profile.getDepartment());
        values.put(COLUMN_PHONENO, profile.getPhoneNo());
        System.out.println("In createUser, inserting event into table");
        return DatabaseHelper.get_instance(context).insertValueToTable(TABLE_PROFILES,values);
    }
}
