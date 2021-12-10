package ca.mcgill.ecse321.librarysystem_android;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class OpeningHours extends AppCompatActivity {
    private String error;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opening_hour);

        getAllOpeningHours();
        configureHomeButton();
    }

    public void configureHomeButton(){
        Button homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(v -> finish());
    }
    /**
     * getAllOpeningHours
     * Get all opening hours from database and write to OH view, calling from current view
     */
    public void getAllOpeningHours(){
        error = "";
        RequestParams rq = new RequestParams();

        // REST service call GET with Http: gets all OH and writes details on OH page on success
        // On failure: displays error message on top of screen of current view
        HttpUtils.get("openingHours/", rq, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {

                try {
                    for(int i=0; i<response.length(); i++) {
                        JSONObject object = response.getJSONObject(i);
                        String date= object.getString("DayOfWeek");
                        String start= object.getString("startTime");
                        String end= object.getString("endTime");

                        if (date.equals("Monday")) {
                            TextView tvDate= findViewById(R.id.oh_date_monday);
                            TextView tvStart= findViewById(R.id.oh_start_time_monday);
                            TextView tvEnd= findViewById(R.id.oh_end_time_monday);
                            tvDate.setText(date);
                            tvStart.setText(start);
                            tvEnd.setText(end);
                        }
                        if (date.equals("Tuesday")) {
                            TextView tvDate = findViewById(R.id.oh_date_tuesday);
                            TextView tvStart = findViewById(R.id.oh_start_time_tuesday);
                            TextView tvEnd = findViewById(R.id.oh_end_time_tuesday);
                            tvDate.setText(date);
                            tvStart.setText(start);
                            tvEnd.setText(end);
                        }
                        if (date.equals("Wednesday")) {
                            TextView tvDate = findViewById(R.id.oh_date_wednesday);
                            TextView tvStart = findViewById(R.id.oh_start_time_wednesday);
                            TextView tvEnd = findViewById(R.id.oh_end_time_wednesday);
                            tvDate.setText(date);
                            tvStart.setText(start);
                            tvEnd.setText(end);
                        }
                        if (date.equals("Thursday")) {
                            TextView tvDate = findViewById(R.id.oh_date_thursday);
                            TextView tvStart = findViewById(R.id.oh_start_time_thursday);
                            TextView tvEnd = findViewById(R.id.oh_end_time_thursday);
                            tvDate.setText(date);
                            tvStart.setText(start);
                            tvEnd.setText(end);
                        }
                        if (date.equals("Friday")) {
                            TextView tvDate = findViewById(R.id.oh_date_friday);
                            TextView tvStart = findViewById(R.id.oh_start_time_friday);
                            TextView tvEnd = findViewById(R.id.oh_end_time_friday);
                            tvDate.setText(date);
                            tvStart.setText(start);
                            tvEnd.setText(end);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
