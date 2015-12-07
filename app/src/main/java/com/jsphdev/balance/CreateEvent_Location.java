package com.jsphdev.balance;

import android.content.Intent;
import android.graphics.Camera;
import android.location.Address;
import android.location.Geocoder;
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

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.vision.barcode.Barcode;
import com.jsphdev.abstrct.Event;
import com.jsphdev.exception.InvalidInputException;
import com.jsphdev.maps.PlacesTask;
import com.jsphdev.utils.CalendarUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

public class CreateEvent_Location extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private SupportMapFragment mapFragment;
    private GoogleMap map;
    private AutoCompleteTextView atvPlaces;
    private PlacesTask placesTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment!=null) {
            mapFragment.getMapAsync(this);
        }

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
            }

            @Override
            public void afterTextChanged(Editable s) {
                LatLng postion = GetLatitudeAndLongitude(atvPlaces.getText().toString());
                if(map!=null && postion!=null){
                    map.moveCamera(CameraUpdateFactory.newLatLng(postion));
                }
            }
        });
    }


    private LatLng GetLatitudeAndLongitude(String address) {
        Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocationName(address.trim().toLowerCase(), 1);
            if (addresses.size() > 0) {
                LatLng latLng = new LatLng(addresses.get(0).getLatitude(),addresses.get(0).getLongitude());
                return latLng;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.map=map;
        LatLng pittsburgh = new LatLng(40.4433,-79.9436);
        map.moveCamera(CameraUpdateFactory.newLatLng(pittsburgh));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(pittsburgh, 17));

        map.setOnMapClickListener(this);

    }

    @Override
    public void onMapClick(LatLng latLng) {
        map.addMarker(new MarkerOptions()
                .position(latLng)
                .title("Your selection")
                .draggable(true));
        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        Intent intent = new Intent(getBaseContext(), CreateEvent.class);
        intent.putExtra("latitude",String.valueOf(latLng.latitude));
        intent.putExtra("longitude",String.valueOf(latLng.longitude));
        startActivity(intent);
    }
}
