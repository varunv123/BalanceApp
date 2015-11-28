package com.jsphdev.ws.remote;


import android.content.Intent;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.List;
import com.jsphdev.abstrct.User;
import com.jsphdev.adapter.WebService.IUserRemoteService;
import com.jsphdev.adapter.WebService.IWebServiceConstants;
import com.jsphdev.balance.ProfileActivity;
import com.jsphdev.entities.model.Profile;
import com.jsphdev.entities.model.Student;
import com.jsphdev.entities.model.Workspace;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sun.jersey.api.client.ClientResponse;

import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vikramn on 11/14/15.
 */
public class UserService implements IUserRemoteService {

    @Override
    public void verifyUser(String userName, String passWord) {
        RequestParams params = new RequestParams();
        params.put("username", userName);
        params.put("password", passWord);
        String url = IWebServiceConstants.GET_USER_URL;

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("status")) {
                        Student student = new Student();
                        student.setIdentifier(Integer.parseInt(obj.getString("userid")));
                        Workspace.get_instance().setCurrentUser(student);
                        UserService userService = new UserService();
                        userService.getProfile();
                    } else {
                        Toast.makeText(Workspace.get_instance().getCurrContext(), "Login Failed", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(Workspace.get_instance().getCurrContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }
            }

            // When the response returned by REST has Http response code other than '200'
            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {
                if (statusCode == 404) {
                    Toast.makeText(Workspace.get_instance().getCurrContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                }
                // When Http response code is '500'
                else if (statusCode == 500) {
                    Toast.makeText(Workspace.get_instance().getCurrContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                }
                // When Http response code other than 404, 500
                else {
                    Toast.makeText(Workspace.get_instance().getCurrContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void registerUser(String userName, String passWord, final User user) {
        System.out.println("Registering User");
        System.out.println(userName);
        System.out.println(passWord);
        RequestParams params = new RequestParams();
        params.put("username", userName);
        params.put("password", passWord);
        String url = IWebServiceConstants.REGISTER_USER_URL;

        AsyncHttpClient client = new AsyncHttpClient();

        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("status")) {
                        user.setIdentifier(Integer.parseInt(obj.getString("userid")));
                        Workspace.get_instance().setCurrentUser(user);
                        UserService userService = new UserService();
                        userService.registerProfile(user.getProfile());
                    } else {
                        Toast.makeText(Workspace.get_instance().getCurrContext(), "Registeration Failed", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(Workspace.get_instance().getCurrContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }
            }

            // When the response returned by REST has Http response code other than '200'
            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {
                if (statusCode == 404) {
                    Toast.makeText(Workspace.get_instance().getCurrContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                }
                // When Http response code is '500'
                else if (statusCode == 500) {
                    Toast.makeText(Workspace.get_instance().getCurrContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                }
                // When Http response code other than 404, 500
                else {
                    Toast.makeText(Workspace.get_instance().getCurrContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
                }
            }
        });
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


    public void registerProfile(final Profile profile){
        System.out.println("Registering profile");
        RequestParams params = new RequestParams();
        System.out.println(Workspace.get_instance().getCurrentUser().getIdentifier());
        params.put("userid", String.valueOf(Workspace.get_instance().getCurrentUser().getIdentifier()));
        System.out.println("*****" + profile.getDepartment());
        System.out.println("*****" + profile.getPhoneNo());
        params.put("department",profile.getDepartment());
        params.put("andrewid",profile.getAndrewId());
        params.put("email",profile.getEmail());
        params.put("firstname",profile.getFirstName());
        params.put("lastname",profile.getLastName());
        params.put("phoneno", profile.getPhoneNo());
        System.out.println(params);
        String url = IWebServiceConstants.REGISTER_PROFILE_URL;

        AsyncHttpClient client = new AsyncHttpClient();
        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
                try {
                    System.out.println(response);
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("status")) {
                        Workspace.get_instance().getCurrentUser().setProfile(profile);
                        Toast.makeText(Workspace.get_instance().getCurrContext(), "You are successfully registered!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Workspace.get_instance().getCurrContext(), ProfileActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Workspace.get_instance().getCurrContext().startActivity(intent);
                    } else {
                        Toast.makeText(Workspace.get_instance().getCurrContext(), "Profile Creation Failed", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(Workspace.get_instance().getCurrContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }
            }

            // When the response returned by REST has Http response code other than '200'
            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {
                if (statusCode == 404) {
                    Toast.makeText(Workspace.get_instance().getCurrContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                }
                // When Http response code is '500'
                else if (statusCode == 500) {
                    Toast.makeText(Workspace.get_instance().getCurrContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                }
                // When Http response code other than 404, 500
                else {
                    Toast.makeText(Workspace.get_instance().getCurrContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void getProfile() {
        RequestParams params = new RequestParams();
        System.out.println(Workspace.get_instance().getCurrentUser().getIdentifier());
        params.put("userid", String.valueOf(Workspace.get_instance().getCurrentUser().getIdentifier()));
        System.out.println(params);
        String url = IWebServiceConstants.GET_USER_PROFILE_URL;

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
                try {
                    System.out.println(response);
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("status")) {
                        Profile profile = new Profile();
                        profile.setDepartment(obj.getString("department"));
                        profile.setEmail(obj.getString("email"));
                        profile.setFirstName(obj.getString("firstname"));
                        profile.setLastName(obj.getString("lastname"));
                        profile.setPhoneNo(obj.getString("phoneno"));
                        profile.setProfilePicture(obj.getString("profilepic"));
                        profile.setAndrewId(obj.getString("andrewid"));
                        Workspace.get_instance().getCurrentUser().setProfile(profile);

                        Toast.makeText(Workspace.get_instance().getCurrContext(), "You are successfully logged in!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Workspace.get_instance().getCurrContext(), ProfileActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Workspace.get_instance().getCurrContext().startActivity(intent);
                    } else {
                        Toast.makeText(Workspace.get_instance().getCurrContext(), "Profile Fetch Failed", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(Workspace.get_instance().getCurrContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }
            }

            // When the response returned by REST has Http response code other than '200'
            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {
                if (statusCode == 404) {
                    Toast.makeText(Workspace.get_instance().getCurrContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                }
                // When Http response code is '500'
                else if (statusCode == 500) {
                    Toast.makeText(Workspace.get_instance().getCurrContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                }
                // When Http response code other than 404, 500
                else {
                    Toast.makeText(Workspace.get_instance().getCurrContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
