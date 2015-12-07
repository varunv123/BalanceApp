package com.jsphdev.utils;

import com.jsphdev.DBLayout.User.CreateUser;
import com.jsphdev.abstrct.Event;
import com.jsphdev.abstrct.User;
import com.jsphdev.adapter.IUserUtils;
import com.jsphdev.entities.model.Calendar;
import com.jsphdev.entities.model.Profile;
import com.jsphdev.entities.model.Student;
import com.jsphdev.entities.model.Workspace;
import android.content.Context;
import com.jsphdev.database.DatabaseIO;
import com.jsphdev.entities.model.Profile;
import com.jsphdev.model.Log;
import com.jsphdev.ws.remote.UserService;


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

    public void verifyUser(String username,String password) throws Exception {
        UserService userService = new UserService();
        userService.verifyUser(username, password);
    }

    public boolean verifyUserLocal(String username,String password) throws Exception {
        DatabaseIO dbIO = new DatabaseIO(Workspace.get_instance().getCurrContext());
        Log log = new Log("Verify User Local","Read Log","Verified User of name: " + username);
        dbIO.insertLogData(log);
        boolean res = dbIO.verifyUser(username, password);
        System.out.println("Verify User: " + res);
        return res;
    }

    public boolean checkUsernameLocal(User user,String username) throws Exception{
        DatabaseIO dbIO = new DatabaseIO(Workspace.get_instance().getCurrContext());
        Log log = new Log("Verify User Local","Read Log","Verified User of name: " + username);
        dbIO.insertLogData(log);
        boolean res = dbIO.checkUsername(username, user);
        System.out.println("Verify User: " + res);
        return res;
    }


    public User registerUser(String username, String password) throws Exception {
        UserService userService = new UserService();
        userService.registerUser(username, password);
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
    }

    @Override
    public void createDBUser(String username, String password) throws Exception {
        if(!verifyUserLocal(username, password)) {
            DatabaseIO dbIO = new DatabaseIO(Workspace.get_instance().getCurrContext());
            Log log = new Log("Creating User", "Insert Log", "Saved User of name: " + username);
            dbIO.insertLogData(log);
            boolean res = dbIO.createDBUser(username, password);
            System.out.println("Registered User: " + res);
        }
    }

    @Override
    public void createDBProfile () throws Exception {
        DatabaseIO dbIO = new DatabaseIO(Workspace.get_instance().getCurrContext());
        Log log = new Log("Register ProfileActivity","Insert Log","Saved ProfileActivity of name: " + Workspace.get_instance().getCurrentUser().getProfile().getFirstName());
        dbIO.insertLogData(log);
        dbIO.createProfile();
    }

    @Override
    public Profile getProfile(int identifier,Context context) throws Exception {
        DatabaseIO dbIO = new DatabaseIO(context);
        Log log = new Log("Getting ProfileActivity","Read Log","Got User of name: " + identifier+".");
        dbIO.insertLogData(log);
        return dbIO.getProfile(identifier);
    }

    @Override
    public void getUserId(String email, User user, Context context) throws Exception {
        DatabaseIO dbIO = new DatabaseIO(context);
        Log log = new Log("Getting userid","Read Log","Got Userid of name: " + email+".");
        dbIO.insertLogData(log);
        dbIO.getUserId(email, user);
    }

}
