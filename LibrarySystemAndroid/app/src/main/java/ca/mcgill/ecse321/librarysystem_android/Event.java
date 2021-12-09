package ca.mcgill.ecse321.librarysystem_android;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.Button;
import android.widget.TextView;

import cz.msebera.android.httpclient.entity.mime.Header;


public class Event extends AppCompatActivity {
    private String error;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event);

        getAllEvents();
        configureHomeButton();
    }

    public void configureHomeButton(){
        Button homeButton = (Button) findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    /**
     * getAllEvents
     * Gets all events from database to current view
     * @param v: current view
     */
    public void getAllEvents() {
        error = "";
        RequestParams rq = new RequestParams();

        // REST service call GET with Http: gets all Events and writes details on Event page on success
        // On failure: displays error message on top of screen of current view
        HttpUtils.get("events/getAllEvents/", rq, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {
                final JSONArray[] allOP= {new JSONArray()};
                allOP[0]=response;
                JSONObject object;
                try {
                    for(int i=0; i<allOP[0].length(); i++) {
                        object = allOP[0].getJSONObject(i);
                        String name= object.getString("name");
                        String date=String.valueOf(object.get("date"));
                        String start=String.valueOf(object.get("eventStart"));
                        String end=String.valueOf(object.get("eventEnd"));

                        TextView tvName = findViewById(R.id.evt_name);
                        TextView tvDate = findViewById(R.id.evt_date);
                        TextView tvStart = findViewById(R.id.evt_start);
                        TextView tvEnd = findViewById(R.id.evt_end);
                        tvDate.setText(date);
                        tvStart.setText(start);
                        tvEnd.setText(end);
                        tvName.setText(name);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setContentView(R.layout.event);

            }
            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
            }
        });
    }

}