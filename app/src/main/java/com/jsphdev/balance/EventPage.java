package com.jsphdev.balance;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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

            final Button registrationButton = (Button) findViewById(R.id.RegisterEventbutton);
            registrationButton.setTag(1);
            registrationButton.setText("Register");
            registrationButton.setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {
                            final int status = (Integer) v.getTag();
                            if (status == 1) {
                                registrationButton.setText("Unregister");
                                v.setTag(0);
                            } else {
                                registrationButton.setText("Register");
                                v.setTag(1);
                            }
                        }
                    }
            );

        }


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