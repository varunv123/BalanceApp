package com.jsphdev.balance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class
        Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button createEventButton = (Button)findViewById(R.id.createEventButton);
        createEventButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), CreateEvent.class);
                        startActivity(intent);
                    }
                }
        );

        Button searchEventButton = (Button)findViewById(R.id.searchEventButton);
        searchEventButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), SearchEvent.class);
                        startActivity(intent);
                    }
                }
        );
    }
}
