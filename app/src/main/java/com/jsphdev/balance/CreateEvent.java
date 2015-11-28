package com.jsphdev.balance;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jsphdev.abstrct.Event;
import com.jsphdev.entities.model.DoubleEvent;
import com.jsphdev.exception.InvalidInputException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreateEvent extends FragmentActivity implements OnMapReadyCallback {

    private SupportMapFragment mapFragment;
    private GoogleMap gMap1;
    private DatePicker datePicker;
    private Calendar calendar;

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

                        try {
                            getEventDetails(v);
                            //Toast to notify successful event creation

                            //go to ProfileActivity page
                            java.text.DateFormat format = new SimpleDateFormat("yyyy-MM-DD HH:mm", Locale.ENGLISH);
                            Date startDate = null;
                            Date endDate = null;
                            try {
                                startDate = format.parse("2013-03-02 10:10");
                                endDate = format.parse("2013-04-03 11:11");
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            com.jsphdev.entities.model.Location location = new com.jsphdev.entities.model.Location(20.1,300.1);
                            EditText givenEventName = (EditText) findViewById(R.id.EditEventName);
                            String eventName = givenEventName.getText().toString();
                            Event event = new DoubleEvent(eventName,4,startDate,endDate,location);
                            com.jsphdev.entities.model.Calendar calendar = new com.jsphdev.entities.model.Calendar();
                            System.out.println("Trying to regitser event");
                            calendar.registerEvent(event, getApplicationContext());
                            Toast.makeText(getApplicationContext(), "Event Created successful.",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(v.getContext(), ProfileActivity.class);
                            intent.putExtra("UserId","");
                            startActivity(intent);
                        } catch (InvalidInputException e) {
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
        System.out.println("hiiiii");
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
