package com.jsphdev.utils;


import com.jsphdev.DBLayout.User.CreateUser;
import com.jsphdev.abstrct.Event;
import com.jsphdev.abstrct.User;
import com.jsphdev.adapter.IUserUtils;
import com.jsphdev.entities.model.Calendar;
import com.jsphdev.entities.model.Profile;
import com.jsphdev.entities.model.Student;
import com.jsphdev.entities.model.Workspace;
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
    }

}
