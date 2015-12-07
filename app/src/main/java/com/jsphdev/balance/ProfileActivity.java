package com.jsphdev.balance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsphdev.entities.model.Calendar;
import com.jsphdev.entities.model.Workspace;
import com.jsphdev.utils.EventUtils;
import com.jsphdev.utils.UserUtils;

import org.w3c.dom.Text;


public class
        ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button createEventButton = (Button)findViewById(R.id.createEventButton);
        TextView nameView = (TextView) findViewById(R.id.textView7);
        nameView.setText(Workspace.get_instance().getCurrentUser().getProfile().getFirstName() + " " + Workspace.get_instance().getCurrentUser().getProfile().getLastName());
        TextView deptView = (TextView) findViewById(R.id.textView8);
        deptView.setText(Workspace.get_instance().getCurrentUser().getProfile().getDepartment());
        TextView emailView = (TextView) findViewById(R.id.textView9);
        emailView.setText(Workspace.get_instance().getCurrentUser().getProfile().getEmail());
        TextView phoneNoView = (TextView) findViewById(R.id.textView10);
        phoneNoView.setText(Workspace.get_instance().getCurrentUser().getProfile().getPhoneNo());
        ImageView myImageView = (ImageView) findViewById(R.id.profilePictureImageView);
        myImageView.setImageResource(R.drawable.profilepic);

        createEventButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), CreateEvent_Location.class);
                        startActivity(intent);
                    }
                }
        );

        Button searchEventButton = (Button)findViewById(R.id.searchEventButton);
        searchEventButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        EventUtils.get_instance().getLatestEvents();
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
