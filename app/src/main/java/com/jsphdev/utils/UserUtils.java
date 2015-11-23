package com.jsphdev.utils;


import com.jsphdev.abstrct.Event;
import com.jsphdev.abstrct.User;
import com.jsphdev.adapter.IUserUtils;
import com.jsphdev.ws.remote.UserService;
import com.jsphdev.ws.remote.WebServiceClient;


/**
 * Created by vikramn on 11/14/15.
 */
public class UserUtils implements IUserUtils {

    @Override
    public boolean isEventRegistered(Event e) {
        return false;
    }

    @Override
    public void registerUser(String username, String password, User user) throws Exception {
        UserService userService = new UserService();
        user.setIdentifier(userService.registerRemoteUser(username, password));
    }

    @Override
    public void registerProfile(User user) throws Exception {
        UserService userService = new UserService();
        user.getProfile().setIdentifier(userService.createProfile(user));
    }

}
