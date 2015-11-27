package com.jsphdev.balance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jsphdev.abstrct.Event;
import com.jsphdev.utils.CalendarUtils;

import java.util.List;

public class SearchResults extends Activity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        ListView listview = (ListView) findViewById(R.id.listView1);
        listview.setOnItemClickListener(this);
        Intent i = getIntent();
        List<Event> events = (List<Event>) i.getSerializableExtra("events");
        CalendarUtils calUtils = new CalendarUtils();
        System.out.println("calling calUtils");
        if (events == null)
            events = calUtils.getAllEvents(this);
        ArrayAdapter<Event> adapter = new ArrayAdapter<Event>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, events);
        listview.setAdapter(adapter);
    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
        // Then you start a new Activity via Intent
        Intent intent = new Intent(this, EventPage.class);
        startActivity(intent);
    }


}
