package com.jsphdev.balance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsphdev.utils.UserUtils;

import org.w3c.dom.Text;


public class
        Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button createEventButton = (Button)findViewById(R.id.createEventButton);

        Intent i = getIntent();
        String UserId = i.getExtras().getString("UserId");
        System.out.println(UserId);
        UserUtils userUtils = new UserUtils();
        com.jsphdev.entities.model.Profile profile = null;
        try {
            profile = userUtils.getProfile(UserId,getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (profile!=null){
            TextView nameView = (TextView) findViewById(R.id.textView7);
            nameView.setText(profile.getFirstName() + " " + profile.getLastName());
            TextView deptView = (TextView) findViewById(R.id.textView8);
            deptView.setText(profile.getDepartment());
            TextView emailView = (TextView) findViewById(R.id.textView9);
            emailView.setText(profile.getEmail());
            TextView phoneNoView = (TextView) findViewById(R.id.textView10);
            phoneNoView.setText(profile.getPhoneNo());
            ImageView myImageView = (ImageView) findViewById(R.id.profilePictureImageView);
            myImageView.setImageResource(R.drawable.profilepic);
        }
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
        Button calendarEventButton = (Button) findViewById(R.id.calendarButton);
        calendarEventButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), CalendarPage.class);
                        startActivity(intent);
                    }
                }
        );
    }
}
