package com.jsphdev.balance;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jsphdev.abstrct.Event;
import com.jsphdev.entities.model.Workspace;
import com.jsphdev.exception.InvalidInputException;
import com.jsphdev.maps.PlacesTask;
import com.jsphdev.utils.CalendarUtils;
import com.jsphdev.utils.EventUtils;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchEvent extends FragmentActivity implements OnMapReadyCallback {

    private SupportMapFragment mapFragment;
    private GoogleMap outermap;
    private AutoCompleteTextView atvPlaces;
    private PlacesTask placesTask;


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

//        Button ButtonSearchFeedback = (Button)findViewById(R.id.ButtonSearchRequest);
//        ButtonSearchFeedback.setOnClickListener(
//                new Button.OnClickListener() {
//                    public void onClick(View v) {
//                        try{
//                            getSearchEventDetails(v);
//                            //go to ProfileActivity page
//                            EditText findEventByName = (EditText) findViewById(R.id.SearchEventByName);
//                            String eventName = findEventByName.getText().toString();
//                            //List<Event> searchResults = CalendarUtils.get_instance().getEventByDay();
//                            List<Event> searchResults = CalendarUtils.get_instance().getEventByName(getApplicationContext(), eventName);
//                            //List<Event> searchResults = CalendarUtils.get_instance().getEventByDay();
//                            //List<Event> searchResults = CalendarUtils.get_instance().getEventByDay();
//                            //List<Event> searchResults = CalendarUtils.get_instance().getEventByDay();
//                            Intent intent = new Intent(v.getContext(),SearchResults.class);
//                            System.out.println(searchResults.size());
//                            intent.putExtra("events",(Serializable) searchResults);
//                            startActivity(intent);
//                        }catch (InvalidInputException e) {
//                            Log.e("CreateEventActivity", e.getMessage());
//                        }
//                    }
//                }
//        );

        atvPlaces = (AutoCompleteTextView) findViewById(R.id.atv_places);
        atvPlaces.setThreshold(1);

        atvPlaces.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                placesTask = new PlacesTask(atvPlaces, getBaseContext());
                placesTask.execute(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });
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
            throw new InvalidInputException("Invalid input ");
        }
        else
            searchEventName = input;

        EditText findEventByTime = (EditText) findViewById(R.id.SearchEventByTime);
        input = findEventByTime.getText().toString();
        if ((input == null) || input.isEmpty()){
            findEventByTime.setError("Invalid input");
            throw new InvalidInputException("Invalid input ");
        }
        else
            searchEventTime = input;

        EditText findEventByLocation = (EditText) findViewById(R.id.SearchEventByLocation);
        input = findEventByLocation.getText().toString();
        if ((input == null) || input.isEmpty()){
            findEventByLocation.setError("Invalid input");
            throw new InvalidInputException("Invalid input ");
        }
        else
            searchEventLocation = input;

        Log.v("SearcheventName", searchEventName);
        Log.v("SearcheventTime", searchEventTime);
        Log.v("SearcheventLcocation", searchEventLocation);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.outermap = map;
        LatLng pittsburgh = new LatLng(40.4433,-79.9436);
//        map.addMarker(new MarkerOptions()
//                .position(pittsburgh)
//                .title("Made @ CMU")
//                .draggable(true));
        map.moveCamera(CameraUpdateFactory.newLatLng(pittsburgh));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(pittsburgh, 17));

        for(Event event:Workspace.get_instance().getLatestEvents()){
            System.out.println(event.getIdentifier());
            LatLng position = new LatLng(event.getLocation().getxCoordinate(),event.getLocation().getyCoordinate());

            map.addMarker(new MarkerOptions()
                            .position(position).title(event.getName()).draggable(false)
                            .snippet(String.valueOf(event.getIdentifier())));

        }

        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                int eventIdentifier = Integer.parseInt(marker.getSnippet());
                System.out.println("******" + eventIdentifier);

                final Event selectedEvent = getEventFromLates(eventIdentifier);
                View view = getLayoutInflater().inflate(R.layout.event_info_layout, null);

                TextView eventName = (TextView) view.findViewById(R.id.eventname);
                TextView startDate = (TextView) view.findViewById(R.id.startDateTime);
                TextView endDate = (TextView) view.findViewById(R.id.endDateTime);
                TextView locationName = (TextView) view.findViewById(R.id.locationname);
                TextView status = (TextView) view.findViewById(R.id.status);

                eventName.setText(selectedEvent.getName());
                startDate.setText(selectedEvent.getStartDate().toString());
                endDate.setText(selectedEvent.getEndDate().toString());
                locationName.setText(selectedEvent.getLocation().getName());
                if (Workspace.get_instance().getCurrentUser().getCalendar().isEventRegistered(selectedEvent)) {
                    status.setText("Registered");
                    view.setBackgroundColor(Color.GREEN);
                } else {
                    status.setText("UnRegistered");
                    view.setBackgroundColor(Color.YELLOW);
                }
                return view;
            }
        });

        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                int eventIdentifier = Integer.parseInt(marker.getSnippet());

                final Event selectedEvent = getEventFromLates(eventIdentifier);
                marker.remove();
                LatLng position = marker.getPosition();
                outermap.addMarker(new MarkerOptions()
                        .position(position).title(selectedEvent.getName()).draggable(false)
                        .snippet(String.valueOf(selectedEvent.getIdentifier())));

                //Call the remove server here too
                EventUtils.get_instance().registerUserForEvent(eventIdentifier, Workspace.get_instance().getCurrentUser().getIdentifier(), getApplicationContext());
                Workspace.get_instance().getCurrentUser().getCalendar().registerEvent(selectedEvent,getBaseContext());
            }
        });
    }

    private Event getEventFromLates(int identifier){
        for(Event event:Workspace.get_instance().getLatestEvents()){
            if(event.getIdentifier()==identifier)
                return  event;
        }
        return null;
    }

}
