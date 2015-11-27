package com.jsphdev.DBLayout.User;

import android.content.ContentValues;
import android.content.Context;

import com.jsphdev.abstrct.User;
import com.jsphdev.database.DatabaseHelper;

/**
 * Created by vikramn on 11/13/15.
 */
public class CreateUser {

    public static final String TABLE_USERS = "user";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "userpassword";

    public boolean createUser(String username, String password, User user,Context context){
        System.out.println("In createUser, creating User");
        ContentValues values = new ContentValues();
        values.put("ID", user.getIdentifier());
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        System.out.println("In createUser, inserting event into table");
        return DatabaseHelper.get_instance(context).insertValueToTable(TABLE_USERS,values);
    }
}
