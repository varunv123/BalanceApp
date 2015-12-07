package com.jsphdev.DBLayout.User;

import android.content.Context;

import com.jsphdev.abstrct.User;
import android.content.ContentValues;
import com.jsphdev.database.DatabaseHelper;
import com.jsphdev.entities.model.Workspace;

/**
 * Created by vikramn on 11/13/15.
 */
public class CreateUser {

    public static final String TABLE_USERS = "user";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "userpassword";

    public boolean createUser(String username, String password){
        System.out.println("In createUser, creating User");
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        System.out.println("In createUser, inserting event into table");
        return DatabaseHelper.get_instance(Workspace.get_instance().getCurrContext()).insertValueToTable(TABLE_USERS,values);
    }
}
