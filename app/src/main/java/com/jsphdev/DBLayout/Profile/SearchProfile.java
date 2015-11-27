package com.jsphdev.DBLayout.Profile;

import android.content.Context;

import com.jsphdev.database.DatabaseHelper;
import com.jsphdev.entities.model.Profile;

/**
 * Created by Varun on 11/27/15.
 */
public class SearchProfile {

    public static final String TABLE_PROFILES = "profile";
    public static final String COLUMN_FIRSTNAME = "firstname";
    public static final String COLUMN_LASTNAME = "lastname";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONENO = "phoneno";
    public static final String COLUMN_USERID = "user_id";

    public Profile searchProfile(String identifier,Context context){
        System.out.println("In searchProfile, calling getProfileByIdentifier in dbHelper");
        String query = String.format("SELECT * FROM " + TABLE_PROFILES + " WHERE " + "ID" + " = \'%s\'",identifier);
        System.out.println(query);
        return DatabaseHelper.get_instance(context).getProfileByIdentifier(query);
    }
}
