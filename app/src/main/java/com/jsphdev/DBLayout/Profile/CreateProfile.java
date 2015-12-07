package com.jsphdev.DBLayout.Profile;

import android.content.ContentValues;
import android.content.Context;

import com.jsphdev.abstrct.User;
import com.jsphdev.database.DatabaseHelper;
import com.jsphdev.entities.model.Profile;
import com.jsphdev.entities.model.Workspace;

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


    public boolean createProfile(){
        System.out.println("In createProfile, creating ProfileActivity");
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRSTNAME, Workspace.get_instance().getCurrentUser().getProfile().getFirstName());
        values.put(COLUMN_LASTNAME, Workspace.get_instance().getCurrentUser().getProfile().getLastName());
        values.put(COLUMN_EMAIL, Workspace.get_instance().getCurrentUser().getProfile().getEmail());
        values.put(COLUMN_DEPARTMENT, Workspace.get_instance().getCurrentUser().getProfile().getDepartment());
        values.put(COLUMN_PHONENO, Workspace.get_instance().getCurrentUser().getProfile().getPhoneNo());
        values.put(COLUMN_USERID, Workspace.get_instance().getCurrentUser().getIdentifier());
        System.out.println("In createUser, inserting event into table");
        return DatabaseHelper.get_instance(Workspace.get_instance().getCurrContext()).insertValueToTable(TABLE_PROFILES,values);
    }
}
