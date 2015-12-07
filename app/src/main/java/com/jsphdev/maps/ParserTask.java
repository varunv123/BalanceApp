package com.jsphdev.maps;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.SimpleAdapter;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by vikramn on 12/6/15.
 */
public class ParserTask extends AsyncTask<String, Integer, List<HashMap<String,String>>> {

    JSONObject jObject;
    Context context;
    AutoCompleteTextView atvPlaces;

    public ParserTask(Context context,AutoCompleteTextView atvPlaces){
        this.context = context;
        this.atvPlaces = atvPlaces;
    }


    @Override
    protected List<HashMap<String, String>> doInBackground(String... jsonData) {
        List<HashMap<String, String>> places = null;

        PlaceJSONParser placeJsonParser = new PlaceJSONParser();

        try {
            jObject = new JSONObject(jsonData[0]);

            // Getting the parsed data as a List construct
            places = placeJsonParser.parse(jObject);

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        }
        return places;
    }

    @Override
    protected void onPostExecute(List<HashMap<String, String>> result) {
        Log.d("Studying flow", "onPostExecute ParserTask1111");
        String[] from = new String[] { "description"};
        int[] to = new int[] { android.R.id.text1 };

        // Creating a SimpleAdapter for the AutoCompleteTextView
        SimpleAdapter adapter = new SimpleAdapter(context, result, android.R.layout.simple_list_item_1, from, to);

        // Setting the adapter
        atvPlaces.setAdapter(adapter);
    }
}