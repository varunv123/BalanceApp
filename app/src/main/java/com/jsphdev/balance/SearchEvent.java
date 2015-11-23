package com.jsphdev.balance;

import android.content.Intent;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jsphdev.exception.InvalidInputException;

public class SearchEvent extends FragmentActivity implements OnMapReadyCallback {

    private SupportMapFragment mapFragment;
    private GoogleMap gMap1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment==null){
        }
        else{
            mapFragment.getMapAsync(this);
        }

        Button ButtonSearchFeedback = (Button)findViewById(R.id.ButtonSearchRequest);
        ButtonSearchFeedback.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        try{
                            getSearchEventDetails(v);
                            //go to Profile page
                            Intent intent = new Intent(v.getContext(),SearchResults.class);
                            startActivity(intent);
                        }catch (InvalidInputException e) {
                            Log.e("CreateEventActivity", e.getMessage());
                        }
                    }
                }
        );
    }


    public void getSearchEventDetails(View v) throws InvalidInputException {
        String input;
        String searchEventName;
        String searchEventTime;
        String searchEventLocation;

        EditText findEventByName = (EditText) findViewById(R.id.SearchEventByName);
        input = findEventByName.getText().toString();
        if ((input == null) || input.isEmpty()){
            findEventByName.setError("Invalid input");
            throw new InvalidInputException();
        }
        else
            searchEventName = input;

        EditText findEventByTime = (EditText) findViewById(R.id.SearchEventByTime);
        input = findEventByTime.getText().toString();
        if ((input == null) || input.isEmpty()){
            findEventByTime.setError("Invalid input");
            throw new InvalidInputException();
        }
        else
            searchEventTime = input;

        EditText findEventByLocation = (EditText) findViewById(R.id.SearchEventByLocation);
        input = findEventByLocation.getText().toString();
        if ((input == null) || input.isEmpty()){
            findEventByLocation.setError("Invalid input");
            throw new InvalidInputException();
        }
        else
            searchEventLocation = input;

        Log.v("SearcheventName", searchEventName);
        Log.v("SearcheventTime", searchEventTime);
        Log.v("SearcheventLcocation",searchEventLocation);
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
