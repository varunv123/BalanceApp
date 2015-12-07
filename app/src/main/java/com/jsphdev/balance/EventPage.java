package com.jsphdev.balance;

import android.content.Intent;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jsphdev.abstrct.Event;
import com.jsphdev.entities.model.Profile;
import com.jsphdev.entities.model.Workspace;
import com.jsphdev.utils.CalendarUtils;
import com.jsphdev.utils.EventUtils;
import com.jsphdev.utils.UserUtils;

import java.util.List;

public class EventPage extends FragmentActivity implements OnMapReadyCallback {

    private SupportMapFragment mapFragment;
    private GoogleMap gMap1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.Eventmap);
        if (mapFragment == null) {
        } else {
            mapFragment.getMapAsync(this);
        }
        final Button registrationButton = (Button) findViewById(R.id.RegisterEventbutton);
        Intent intent = getIntent();
        final int eventid = intent.getIntExtra("eventid", 0);
        List<Profile> profilesRegisteredForEvent =
                EventUtils.get_instance().getProfilesRegisteredForEvent(eventid, getApplicationContext());
        System.out.println("Event profiles length: " + profilesRegisteredForEvent.size());
        boolean isUserRegistered = false;
        for (Profile p1: profilesRegisteredForEvent){
            if (p1.getEmail().equals(Workspace.get_instance().getCurrentUser().getProfile().getEmail())){
                isUserRegistered = true;
                System.out.println("yayaya");
            }
        }
        if (!isUserRegistered){
            registrationButton.setVisibility(View.VISIBLE);
            registrationButton.setTag(1);
            registrationButton.setText("Register");
        }
        else{
            registrationButton.setVisibility(View.VISIBLE);
            registrationButton.setTag(0);
            registrationButton.setText("UnRegister");
        }
        Event event  = CalendarUtils.get_instance().getEventById(eventid, getApplicationContext());
        TextView eventName = (TextView) findViewById(R.id.TextEventName);
        eventName.setText("Event Name: " + event.getName());
        TextView eventLocation = (TextView) findViewById(R.id.TextEventLocation);
        eventLocation.setText("Event Location: (" + event.getLocation().getxCoordinate() + "," + event.getLocation().getyCoordinate() + ")");
        TextView eventStartTime = (TextView) findViewById(R.id.TextEventStartTime);
        eventStartTime.setText("Event Start Date: " + event.getStartDate());
        TextView eventStopTime = (TextView) findViewById(R.id.TextEventStopTime);
        eventStopTime.setText("Event Stop Date: " + event.getEndDate());
        TextView eventCreator = (TextView) findViewById(R.id.TextEventCreator);
        Profile creator = null;
        try {
            creator = UserUtils.get_instance().getProfile(event.getCreateridentifier(),getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (creator!=null)
            eventCreator.setText("Event Creator: " + creator.getFirstName() + " " +creator.getLastName());
        System.out.println(eventid + "," + event.getIdentifier());
        TextView eventProfiles = (TextView) findViewById(R.id.TextEventProfiles);
        StringBuilder sb = new StringBuilder();
        for (Profile p:profilesRegisteredForEvent){
            sb.append(p.getFirstName() + " " + p.getLastName());
            sb.append(", ");
        }
        String profilesRegistered = sb.toString();
        eventProfiles.setText("Event Atendees: " + profilesRegistered);

        registrationButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        final int status = (Integer) v.getTag();
                        if (status==1) {
                            System.out.println("Registering user for event.");
                            EventUtils.get_instance().registerUserForEvent(eventid, Workspace.get_instance().getCurrentUser().getIdentifier(), getApplicationContext());
                            Intent intent = new Intent(v.getContext(), EventPage.class);
                            intent.putExtra("eventid", eventid);
                            startActivity(intent);
                        }
                        else{
                            System.out.println("UnRegistering user for event.");
                            EventUtils.get_instance().unRegisterUserForEvent(eventid, Workspace.get_instance().getCurrentUser().getIdentifier(), getApplicationContext());
                            Intent intent = new Intent(v.getContext(), EventPage.class);
                            intent.putExtra("eventid", eventid);
                            startActivity(intent);
                        }
                    }
                }
        );

        final Button homeButton = (Button) findViewById(R.id.HomeButton);
        homeButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.setMyLocationEnabled(true);
        Location location = map.getMyLocation();
        LatLng myLocation;
        System.out.println(location);
        if (location != null){
            myLocation = new LatLng(location.getLatitude(),
                    location.getLongitude());
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 16));
        }
        map.addMarker(new MarkerOptions()
                .position(new LatLng(10, 10))
                .title("Event Location")
                .draggable(true));
    }
}