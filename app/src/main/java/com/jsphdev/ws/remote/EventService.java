package com.jsphdev.ws.remote;

import android.content.Intent;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import com.jsphdev.abstrct.Event;
import com.jsphdev.abstrct.User;
import com.jsphdev.adapter.WebService.IEventRemoteService;
import com.jsphdev.adapter.WebService.IWebServiceConstants;
import com.jsphdev.balance.ProfileActivity;
import com.jsphdev.balance.SearchEvent;
import com.jsphdev.entities.model.DoubleEvent;
import com.jsphdev.entities.model.Student;
import com.jsphdev.entities.model.Workspace;
import com.jsphdev.utils.CalendarUtils;
import com.jsphdev.utils.EventUtils;
import com.jsphdev.utils.UserUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vikramn on 11/14/15.
 */
public class EventService implements IEventRemoteService {

    @Override
    public void createRemoteEvent(final Event event) {
        RequestParams params = new RequestParams();
        params.put("creatorid", String.valueOf(Workspace.get_instance().getCurrentUser().getIdentifier()));
        params.put("eventname", event.getName());
        params.put("startdatetime", CalendarUtils.get_instance().getDateStrng(event.getStartDate()));
        params.put("enddatetime",CalendarUtils.get_instance().getDateStrng(event.getEndDate()));
        params.put("locationname",event.getLocation().getName());
        params.put("latitude", String.valueOf(event.getLocation().getxCoordinate()));
        params.put("longitude",String.valueOf(event.getLocation().getyCoordinate()));

        String url = IWebServiceConstants.CREATE_EVENT;

        AsyncHttpClient client = new AsyncHttpClient();
        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
                try {
                    System.out.println(response);
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("status")) {
                        System.out.println("After event creation");
                        event.setIdentifier(Integer.parseInt(obj.getString("eventid")));
                        Workspace.get_instance().getCurrentUser().getCalendar().createEvent(event);
                        Toast.makeText(Workspace.get_instance().getCurrContext(), "Event Successfully Created!", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(Workspace.get_instance().getCurrContext(), ProfileActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Workspace.get_instance().getCurrContext().startActivity(intent);
                    } else {
                        Toast.makeText(Workspace.get_instance().getCurrContext(), "Profile Creation Failed", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(Workspace.get_instance().getCurrContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                } catch (Exception e){
                    Toast.makeText(Workspace.get_instance().getCurrContext(), "Error Occured [Inputting into DB]!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {
                if (statusCode == 404) {
                    Toast.makeText(Workspace.get_instance().getCurrContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                }
                else if (statusCode == 500) {
                    Toast.makeText(Workspace.get_instance().getCurrContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(Workspace.get_instance().getCurrContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void deleteRemoteEvent(Event event) {

    }

    @Override
    public void registerRemoteEvent(int eventId, int userId) {
        RequestParams params = new RequestParams();
        params.put("eventid",String.valueOf(eventId));
        params.put("userid",String.valueOf(userId));
        String url = IWebServiceConstants.REGISTER_EVENT_URL;

        AsyncHttpClient client = new AsyncHttpClient();
        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("status")) {
                        Toast.makeText(Workspace.get_instance().getCurrContext(), "Event Successfully Registered!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Workspace.get_instance().getCurrContext(), "Event Registeration Failed", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(Workspace.get_instance().getCurrContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                } catch (Exception e){
                    Toast.makeText(Workspace.get_instance().getCurrContext(), "Error Occured [Inputting into DB]!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {
                if (statusCode == 404) {
                    Toast.makeText(Workspace.get_instance().getCurrContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                }
                else if (statusCode == 500) {
                    Toast.makeText(Workspace.get_instance().getCurrContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(Workspace.get_instance().getCurrContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void deRegisterRemoteEvent(Event event, User user) {

    }

    @Override
    public List<Event> getAllRemoteEvents() {
        return null;
    }

    @Override
    public void getAllUserEvents() {

        //Delete all the events for that user

        System.out.println("Coming to the call ");

        RequestParams params = new RequestParams();
        params.put("userid", String.valueOf(Workspace.get_instance().getCurrentUser().getIdentifier()));
        String url = IWebServiceConstants.GET_USER_EVENTS;

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
                try {
                    System.out.println("Successfully coming bak");
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("status")) {

                        JSONArray userEvents = new JSONArray();
                        userEvents = obj.getJSONArray("userevents");

                        for(int i=0;i<userEvents.length();i++){
                            JSONObject currEvent = userEvents.getJSONObject(i);
                            System.out.println(currEvent.toString());
                            EventUtils utils = new EventUtils();
                            utils.createDBDoubleEvent(currEvent.getInt("id"),currEvent.getString("name"), currEvent.getInt("creatorid"), currEvent.getString("locationname"),
                                    currEvent.getDouble("latitude"), currEvent.getDouble("longitude"), currEvent.getString("starttime"), currEvent.getString("endtime"),
                                    Workspace.get_instance().getCurrentUser().getCalendar());
                        }

                        Toast.makeText(Workspace.get_instance().getCurrContext(), "You are successfully logged in!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Workspace.get_instance().getCurrContext(), ProfileActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Workspace.get_instance().getCurrContext().startActivity(intent);
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
    public void getLatestEvents() {
        //Delete all the events for that user
        System.out.println("Coming to the search call");
        RequestParams params = new RequestParams();
        params.put("userid", String.valueOf(Workspace.get_instance().getCurrentUser().getIdentifier()));
        //params.put("userid", String.valueOf(14));

        String url = IWebServiceConstants.GET_LATEST_EVENTS;

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
                try {
                    System.out.println("Successfully coming bak");
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("status")) {

                        JSONArray userEvents = new JSONArray();
                        userEvents = obj.getJSONArray("events");

                        for(int i=0;i<userEvents.length();i++){
                            JSONObject currEvent = userEvents.getJSONObject(i);

                            EventUtils utils = new EventUtils();
                            Event event = utils.createTempDoubleEvent(currEvent.getInt("id"),currEvent.getString("name"), currEvent.getInt("creatorid"), currEvent.getString("locationname"),
                                    currEvent.getDouble("latitude"), currEvent.getDouble("longitude"), currEvent.getString("starttime"), currEvent.getString("endtime"));
                            System.out.println("Adding the latest event");
                            Workspace.get_instance().addToLatestEvents(event);
                        }

                        Intent intent = new Intent(Workspace.get_instance().getCurrContext(), SearchEvent.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Workspace.get_instance().getCurrContext().startActivity(intent);
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
    public List<Event> getRemoteEventsByDay(Date currDate) {
        return null;
    }

    @Override
    public List<Event> getRemoteEventsByMonth(Date currDate) {
        return null;
    }

    @Override
    public List<Event> getRemoteEventsByYear(Date currDate) {
        return null;
    }

    @Override
    public List<Event> getRemoteEventsByDay(Date currDate, User user) {
        return null;
    }

    @Override
    public List<Event> getRemoteEventsByMonth(Date currDate, User user) {
        return null;
    }

    @Override
    public List<Event> getRemoteEventsByYear(Date currDate, User user) {
        return null;
    }
}
