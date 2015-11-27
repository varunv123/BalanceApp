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

import org.w3c.dom.Text;

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
    private static TextView textViewStartTime,textViewStopTime,textViewStartDate,textViewStopDate;

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

                            //go to Profile page
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
                            Event event = new DoubleEvent("testEvent3",4,startDate,endDate,location);
                            com.jsphdev.entities.model.Calendar calendar = new com.jsphdev.entities.model.Calendar();
                            System.out.println("Trying to regitser event");
                            calendar.registerEvent(event,getApplicationContext());
                            Toast.makeText(getApplicationContext(), "Event Created successful.",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(v.getContext(), Profile.class);
                            startActivity(intent);
                        } catch (InvalidInputException e) {
                            Log.e("CreateEventActivity", e.getMessage());
                        }
                    }
                }
        );

        Button buttonStartTime = (Button) findViewById(R.id.ButtonChooseStartTime);
        buttonStartTime.setOnClickListener(getStartTime);

        Button buttonStopTime = (Button) findViewById(R.id.ButtonChooseStopTime);
        buttonStopTime.setOnClickListener(getStopTime);

        Button buttonStartDate = (Button) findViewById(R.id.ButtonChooseStartDate);
        buttonStartDate.setOnClickListener(getStartDate);

        Button buttonStopDate = (Button) findViewById(R.id.ButtonChooseStopDate);
        buttonStopDate.setOnClickListener(getStopDate);

        textViewStartTime = (TextView) findViewById(R.id.TextViewStartTime);
        textViewStopTime = (TextView) findViewById(R.id.TextViewStopTime);
        textViewStartDate = (TextView) findViewById(R.id.TextViewStartDate);
        textViewStopDate = (TextView) findViewById(R.id.TextViewStopDate);
    }

    View.OnClickListener getStartTime = new View.OnClickListener() {
        public void onClick(View v){
            showStartTimePickerDialog(v);
        }
    };

    View.OnClickListener getStopTime = new View.OnClickListener() {
        public void onClick(View v){
            showStopTimePickerDialog(v);
        }
    };

    View.OnClickListener getStartDate = new View.OnClickListener() {
        public void onClick(View v){
            showStartDatePickerDialog(v);
        }
    };

    View.OnClickListener getStopDate = new View.OnClickListener() {
        public void onClick(View v){
            showStopDatePickerDialog(v);
        }
    };

    public void showStartTimePickerDialog(View v) {
        DialogFragment newFragment = new BalanceStartTimePicker();
        newFragment.show(getFragmentManager(),"startTimePicker");
    }

    public void showStopTimePickerDialog(View v) {
        DialogFragment newFragment = new BalanceStopTimePicker();
        newFragment.show(getFragmentManager(),"stopTimePicker");
    }

    public void showStartDatePickerDialog(View v) {
        DialogFragment newFragment = new BalanceStartDatePicker();
        newFragment.show(getFragmentManager(),"startDatePicker");
    }

    public void showStopDatePickerDialog(View v) {
        DialogFragment newFragment = new BalanceStopDatePicker();
        newFragment.show(getFragmentManager(),"stopDatePicker");
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


    public static class BalanceStartTimePicker extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {



        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            textViewStartTime.setText("Hour: " + hourOfDay + ", Min: " + minute);
        }
    }

    public static class BalanceStopTimePicker extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {



        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            textViewStopTime.setText("Hour: " + hourOfDay + ", Min: " + minute);
        }
    }

    public static class BalanceStartDatePicker extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            textViewStartDate.setText("Year: " + year +", Month: " + month + ", Day: " + day);
        }
    }

    public static class BalanceStopDatePicker extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            textViewStopDate.setText("Year: " + year +", Month: " + month + ", Day: " + day);
        }
    }
}
