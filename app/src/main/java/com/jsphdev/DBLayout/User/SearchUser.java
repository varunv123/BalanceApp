package com.jsphdev.DBLayout.User;

import android.content.Context;

import com.jsphdev.abstrct.User;
import com.jsphdev.database.DatabaseHelper;

/**
 * Created by Varun on 11/30/15.
 */
public class SearchUser {

    public void getUserId(String email, User user,Context context){
        DatabaseHelper.get_instance(context).getUserId(email,user);
    }

    public boolean verifyUser(String email, String password,Context context){
        return DatabaseHelper.get_instance(context).verifyUser(email,password);
    }

    public boolean checkUsername(String email, User user,Context context){
        return DatabaseHelper.get_instance(context).checkUsername(email,user);
    }
}
