package ca.mcgill.ecse321.librarysystem_android;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;


import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Media extends AppCompatActivity {
    private String error;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media);

        getAllMedia();
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
     * getAllMedia
     * Gets all media of both types (checkOut and nonCheckout) from database, calling from current view
     * @param v: current view
     */
    public void getAllMedia() {
        error = "";
        RequestParams rq = new RequestParams();

        // REST service call GET with Http: gets all checkoutItems and writes details on Media page on success
        // On failure: displays error message on top of screen of current view
        HttpUtils.get("checkoutItems/", rq, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {
                final JSONArray[] allOP= {new JSONArray()};
                allOP[0]=response;
                JSONObject object;
                try {
                    for(int i=0; i<allOP[0].length(); i++) {
                        object = allOP[0].getJSONObject(i);
                        String type=String.valueOf(object.get("mediaType"));
                        int id=object.getInt("mediaID");
                        String name= object.getString("name");
                        String checkoutStatus=String.valueOf(object.getBoolean("isCheckedOut"));
                        String reserveStatus=String.valueOf(object.getBoolean("isReserved"));

                        TextView tvType = findViewById(R.id.media_type);
                        TextView tvID = findViewById(R.id.media_id);
                        TextView tvName = findViewById(R.id.media_title);
                        TextView tvChecked = findViewById(R.id.media_checked);
                        TextView tvReserved = findViewById(R.id.media_reserved);
                        tvType.setText(type);
                        tvID.setText(id);
                        tvName.setText(name);
                        tvChecked.setText(checkoutStatus);
                        tvReserved.setText(reserveStatus);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setContentView(R.layout.media);
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

        // REST service call GET with Http: gets all nonCheckoutItems and writes details on Media page on success
        // On failure: displays error message on top of screen of current view
        HttpUtils.get("nonCheckOutItems/", rq, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode,cz.msebera.android.httpclient.Header[] headers, JSONArray response) {
                final JSONArray[] allOP= {new JSONArray()};
                allOP[0]=response;
                JSONObject object;
                try {
                    for(int i=0; i<allOP[0].length(); i++) {
                        object = allOP[0].getJSONObject(i);
                        String type=(String) object.get("mediaType");
                        int id=object.getInt("mediaID");
                        String name= object.getString("name");

                        TextView tvType = findViewById(R.id.media_type);
                        TextView tvID = findViewById(R.id.media_id);
                        TextView tvName = findViewById(R.id.media_title);
                        tvType.setText(type);
                        tvID.setText(id);
                        tvName.setText(name);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setContentView(R.layout.media);
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