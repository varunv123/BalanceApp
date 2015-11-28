package com.jsphdev.DBLayout.User;

import android.content.Context;

import com.jsphdev.abstrct.User;
import com.jsphdev.entities.model.Admin;
import com.jsphdev.entities.model.Student;

/**
 * Created by vikramn on 11/13/15.
 */
public class CreateUser {

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
    }
}
