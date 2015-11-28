package com.jsphdev.DBLayout.User;

<<<<<<< HEAD
import android.content.Context;

import com.jsphdev.abstrct.User;
import com.jsphdev.entities.model.Admin;
import com.jsphdev.entities.model.Student;
=======
import android.content.ContentValues;
import android.content.Context;

import com.jsphdev.abstrct.User;
import com.jsphdev.database.DatabaseHelper;
>>>>>>> f98046478d0899d0f691b4d495580d4a6c384800

/**
 * Created by vikramn on 11/13/15.
 */
public class CreateUser {

<<<<<<< HEAD
    public static CreateUser createUser;

    public static CreateUser get_instance(){
        if(createUser==null)
            createUser = new CreateUser();
        return  createUser;
    }


    public Student createNewStudent(){
        Student student = new Student();
        return student;
    }

    public Admin createNewAdmin(){
        Admin admin = new Admin();
        return  admin;
=======
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
>>>>>>> f98046478d0899d0f691b4d495580d4a6c384800
    }
}
