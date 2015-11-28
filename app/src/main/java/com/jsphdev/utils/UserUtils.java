package com.jsphdev.utils;


<<<<<<< HEAD
import com.jsphdev.DBLayout.User.CreateUser;
import com.jsphdev.abstrct.Event;
import com.jsphdev.abstrct.User;
import com.jsphdev.adapter.IUserUtils;
import com.jsphdev.entities.model.Calendar;
import com.jsphdev.entities.model.Profile;
import com.jsphdev.entities.model.Student;
import com.jsphdev.entities.model.Workspace;
=======
import android.content.Context;

import com.jsphdev.abstrct.Event;
import com.jsphdev.abstrct.User;
import com.jsphdev.adapter.IUserUtils;
import com.jsphdev.database.DatabaseIO;
import com.jsphdev.entities.model.Profile;
import com.jsphdev.model.Log;
>>>>>>> f98046478d0899d0f691b4d495580d4a6c384800
import com.jsphdev.ws.remote.UserService;
import com.jsphdev.ws.remote.WebServiceClient;


/**
 * Created by vikramn on 11/14/15.
 */
public class UserUtils implements IUserUtils {


    private static UserUtils userUtils;

    public static UserUtils get_instance(){
        if(userUtils==null)
            userUtils = new UserUtils();
        return userUtils;
    }
    @Override
    public boolean isEventRegistered(Event e) {
        return false;
    }

<<<<<<< HEAD
    public void verifyUser(String username,String password) throws Exception {
        UserService userService = new UserService();
        userService.verifyUser(username, password);
    }


    public User registerUser(String username, String password, User user) throws Exception {
        UserService userService = new UserService();
        userService.registerUser(username,password,user);
        return null;
    }

    @Override
    public User createUser(String firstName, String lastName, String andrewId, String department,
                           String emailId,String phoneNo) {

        Profile profile = new Profile();
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setPhoneNo(phoneNo);
        profile.setEmail(emailId);
        profile.setDepartment(department);
        profile.setAndrewId(andrewId);

        Student student = new Student();
        student.setProfile(profile);
        return student;
=======
    @Override
    public void registerUser(String username, String password, User user, Context context) throws Exception {
//        UserService userService = new UserService();
//        user.setIdentifier(userService.registerRemoteUser(username, password));
        DatabaseIO dbIO = new DatabaseIO(context);
        Log log = new Log("Register User","Insert Log","Saved User of name: " + username);
        dbIO.insertLogData(log);
        dbIO.registerUser(username, password, user);
        System.out.println("Registered User");
    }

    @Override
    public void registerProfile(User user,Profile profile,Context context) throws Exception {
//        UserService userService = new UserService();
//        user.getProfile().setIdentifier(userService.createProfile(user));
        DatabaseIO dbIO = new DatabaseIO(context);
        Log log = new Log("Register ProfileActivity","Insert Log","Saved ProfileActivity of name: " + profile.getFirstName()+", " + profile.getLastName()+".");
        dbIO.insertLogData(log);
        dbIO.registerProfile(profile);
        System.out.println("Registered ProfileActivity");
    }

    @Override
    public Profile getProfile(String identifier,Context context) throws Exception {
        DatabaseIO dbIO = new DatabaseIO(context);
        Log log = new Log("Getting ProfileActivity","Read Log","Got User of name: " + identifier+".");
        dbIO.insertLogData(log);
        return dbIO.getProfile(identifier);
>>>>>>> f98046478d0899d0f691b4d495580d4a6c384800
    }

}
