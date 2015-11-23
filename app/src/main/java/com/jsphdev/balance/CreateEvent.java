package com.jsphdev.balance;

import android.content.Intent;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jsphdev.exception.InvalidInputException;

public class CreateEvent extends FragmentActivity implements OnMapReadyCallback {

    private SupportMapFragment mapFragment;
    private GoogleMap gMap1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment==null){
        }
        else{
            mapFragment.getMapAsync(this);
        }

        Button ButtonSendFeedback = (Button)findViewById(R.id.ButtonSendFeedback);
        ButtonSendFeedback.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        try{
                            getEventDetails(v);
                            //Toast to notify successful event creation
                            Toast.makeText(getApplicationContext(), "Event Created successful.",
                                    Toast.LENGTH_SHORT).show();
                            //go to Profile page
                            Intent intent = new Intent(v.getContext(),Profile.class);
                            startActivity(intent);
                        }catch (InvalidInputException e) {
                            Log.e("CreateEventActivity", e.getMessage());
                        }
                    }
                }
        );
    }

    public void getEventDetails(View v) throws InvalidInputException{
        String eventName;
        String eventStartTime;
        String eventEndTime;
        String eventLocation;
        String input;

        EditText givenEventName = (EditText) findViewById(R.id.EditEventName);
        input = givenEventName.getText().toString();
        if ((input == null) || input.isEmpty()){
            givenEventName.setError("Invalid input");
            throw new InvalidInputException();
        }
        else
            eventName = input;

        EditText givenStartTime = (EditText) findViewById(R.id.EditEventStartTime);
        input = givenStartTime.getText().toString();
        if ((input == null) || input.isEmpty()){
            givenStartTime.setError("Invalid input");
            throw new InvalidInputException();
        }
        else
            eventStartTime = input;

        EditText givenEndTime = (EditText) findViewById(R.id.EditEventStopTime);
        input = givenEndTime.getText().toString();
        if ((input == null) || input.isEmpty()){
            givenEndTime.setError("Invalid input");
            throw new InvalidInputException();
        }
        else
            eventEndTime = input;

        EditText givenLocation = (EditText) findViewById(R.id.EditEventLocation);
        input = givenLocation.getText().toString();
        if ((input == null) || input.isEmpty()){
            givenLocation.setError("Invalid input");
            throw new InvalidInputException();
        }
        else
            eventLocation = input;
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
