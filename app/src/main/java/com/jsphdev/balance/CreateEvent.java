package com.jsphdev.balance;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jsphdev.abstrct.Event;
import com.jsphdev.entities.model.Workspace;
import com.jsphdev.exception.InvalidInputException;
import com.jsphdev.maps.ParserTask;
import com.jsphdev.maps.PlacesTask;
import com.jsphdev.utils.CalendarUtils;
import com.jsphdev.utils.EventUtils;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CreateEvent extends FragmentActivity implements OnMapReadyCallback {

    private SupportMapFragment mapFragment;
    private LatLng eventPosition;
    private GoogleMap map;
    private DatePicker datePicker;
    private Calendar calendar;
    private static TextView textViewStartTime,textViewStopTime,textViewStartDate,textViewStopDate;
    private static EditText locationName;
    private AutoCompleteTextView atvPlaces;
    private PlacesTask placesTask;

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

        eventPosition = new LatLng(Double.parseDouble(getIntent().getStringExtra("latitude")),Double.parseDouble(getIntent().getStringExtra("longitude")));
        String locationString = getLocationName(eventPosition);
        System.out.println(eventPosition.toString());
        System.out.println("After setting the name " + locationString);
        locationName = (EditText)findViewById(R.id.LocationName);
        locationName.setText(locationString);

        Button ButtonSendFeedback = (Button)findViewById(R.id.ButtonSendFeedback);
        ButtonSendFeedback.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        try {
                            getEventDetails(v);

                            String startDateString = textViewStartDate.getText().toString();
                            String stopDateString = textViewStopDate.getText().toString();
                            String startTimeString = textViewStartTime.getText().toString();
                            String stopTimeString = textViewStopTime.getText().toString();
                            String locationString = locationName.getText().toString();
                            com.jsphdev.entities.model.Location location = CalendarUtils.get_instance().getLocationObject(locationString,eventPosition);
                            EditText givenEventName = (EditText) findViewById(R.id.EditEventName);
                            String eventName = givenEventName.getText().toString();

                            System.out.println("Coming here");
                            EventUtils utils = new EventUtils();
                            Event event = utils.createBaseDoubleEvent(eventName, Workspace.get_instance().getCurrentUser().getIdentifier(), location,
                                    startDateString + "," + startTimeString, stopDateString + "," + stopTimeString);
                            utils.createEvent(event);
                        } catch (InvalidInputException e) {
                            Log.e("CreateEventActivity", e.getMessage());

                            if (e.getMessage().equalsIgnoreCase("Invalid date input")) {
                                textViewStartDate.setError("Invalid date input");
                                textViewStopDate.setError("Invalid date input");
                                textViewStartTime.setError("Invalid date input");
                                textViewStopTime.setError("Invalid date input");
                            }
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


    private String getLocationName(LatLng position){
        Geocoder geocoder;
        List<Address> addresses;
        try {
            geocoder = new Geocoder(this, Locale.getDefault());

            addresses = geocoder.getFromLocation(position.latitude, position.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            //String city = addresses.get(0).getLocality();
            //String state = addresses.get(0).getAdminArea();
            //String country = addresses.get(0).getCountryName();
            //String postalCode = addresses.get(0).getPostalCode();
            //String knownName = addresses.get(0).getFeatureName();
            return address;

        } catch (IOException e){
            e.printStackTrace();
            return "";
        }
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
            throw new InvalidInputException("Invalid input");
        }
        else
            eventName = input;
    }


    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;
        if(eventPosition!=null){
            map.addMarker(new MarkerOptions()
                    .position(eventPosition)
                    .title("Your selection")
                    .draggable(true));
            map.moveCamera(CameraUpdateFactory.newLatLng(eventPosition));
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(eventPosition, 17));
        }
        else {
            LatLng pittsburgh = new LatLng(40.4397, -79.9764);
            map.moveCamera(CameraUpdateFactory.newLatLng(pittsburgh));
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(pittsburgh, 17));
        }
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
            textViewStartTime.setText(hourOfDay + ":" + minute);
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
            textViewStopTime.setText(hourOfDay + ":" + minute);
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
            textViewStartDate.setText(day + "/" + (month + 1) + "/" + year);
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
            textViewStopDate.setText(day + "/" + (month + 1) + "/" + year);
        }
    }

}
