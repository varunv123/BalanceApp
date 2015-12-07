package com.jsphdev.maps;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.AutoCompleteTextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by vikramn on 12/6/15.
 */

// Fetches all places from GooglePlaces AutoComplete Web Service
public class PlacesTask extends AsyncTask<String, Void, String> {

    private AutoCompleteTextView atvPlaces;
    private Context contex;
    private ParserTask parserTask;

    public PlacesTask(AutoCompleteTextView atvPlaces, Context context){
        this.atvPlaces = atvPlaces;
        this.contex = context;
    }


    @Override
    protected String doInBackground(String... place) {

        // For storing data from web service
        String data = "";

        // Obtain browser key from https://code.google.com/apis/console
        String key = "key=AIzaSyCyoUwilbwlcgZRZIeOoMNMxh7GuUb9ums";

        String input = "";

        try {
            input = "input=" + URLEncoder.encode(place[0], "utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        // place type to be searched
        String types = "types=geocode";

        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = input + "&" + types + "&" + sensor + "&" + key;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/place/autocomplete/" + output + "?" + parameters;
        System.out.println("url is " + url);
        try {
            // Fetching the data from we service
            data = downloadUrl(url);
        } catch (Exception e) {
            Log.d("Background Task", e.toString());
        }
        return data;
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("Studying flow", "onPostExecute PlacesTask");
        super.onPostExecute(result);

        // Creating ParserTask
        parserTask = new ParserTask(contex,atvPlaces);

        // Starting Parsing the JSON string returned by Web Service
        parserTask.execute(result);
    }

    /**
     * A method to download json data from url
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();
            Log.d("dataFrom browser", data);
            br.close();

        } catch (Exception e) {
            Log.d("Downloading url", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

}