package com.jsphdev.ws.remote;

import com.sun.jersey.api.client.ClientResponse;

import java.util.List;

import com.jsphdev.abstrct.User;
import com.jsphdev.adapter.WebService.IUserRemoteService;
import com.jsphdev.adapter.WebService.IWebServiceConstants;

/**
 * Created by vikramn on 11/14/15.
 */
public class UserService implements IUserRemoteService {

    @Override
    public int registerRemoteUser(String userName, String passWord) throws Exception {
        WebServiceClient client = new WebServiceClient();
        String url = IWebServiceConstants.REGISTER_USER_URL+"/"+userName+"/"+passWord+"/";
        ClientResponse response = client.getResponse(url);
        int userID = response.getEntity(Integer.class);
        return userID;
    }



    @Override
    public int verifyUser(String userName, String passWord) {
        return 0;
    }

    @Override
    public int removeUser(User user) {
        return 0;
    }

    @Override
    public List<User> getAllUser() {
        return null;
    }

    @Override
    public List<User> getUserByUserName(String username) {
        return null;
    }

    @Override
    public void updateProfile(User user) {

    }

    @Override
    public int createProfile(User user) throws Exception {
        WebServiceClient client = new WebServiceClient();
        String url = IWebServiceConstants.CREATE_USER_PROFILE_URL;
        ClientResponse response = client.getResponse(url,user.getProfile().toString());
        int userID = response.getEntity(Integer.class);
        return userID;
    }
}
