package com.jsphdev.adapter.WebService;

import java.util.List;

import com.jsphdev.abstrct.User;

/**
 * Created by vikramn on 11/13/15.
 */
public interface IUserRemoteService {

    public void verifyUser(String userName, String passWord);
    public void registerUser(String userName,String passWord);
    public int removeUser(User user);
    public List<User> getAllUser();
    public List<User> getUserByUserName(String username);
    public void updateProfile(User user);
    public void getProfile();

}
