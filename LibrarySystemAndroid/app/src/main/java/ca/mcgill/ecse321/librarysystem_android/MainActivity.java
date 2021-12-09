package ca.mcgill.ecse321.librarysystem_android;

import android.content.Intent;
import android.os.Bundle;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ca.mcgill.ecse321.librarysystem_android.databinding.ActivityMainBinding;
import cz.msebera.android.httpclient.Header;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String error = null;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private Button loginbutton;
    private Button oHbutton;
    private Button mediabutton;
    private Button eventbutton;
    private Button registerbutton;

    private List<String> localOptions = new ArrayList<>(2);
    private ArrayAdapter<String> localAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        loginbutton = (Button) findViewById(R.id.loginButton);
        oHbutton = (Button) findViewById(R.id.oHButton);
        mediabutton = (Button) findViewById(R.id.mediaButton);
        eventbutton = (Button) findViewById(R.id.eventButton);
        registerbutton = (Button) findViewById(R.id.registerButton);

//        loginbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v){
//                startActivity(new Intent(MainActivity.this, Online.class));
//            }
//        });
        oHbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, OpeningHours.class));
            }
        });
        mediabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, Media.class));
            }
        });
        eventbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, Event.class));
            }
        });

//        button = findViewById(R.id.fab);

//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        setSupportActionBar(binding.toolbar);
//
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//
//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        localOptions.add("Local");
        localOptions.add("Non-local");

        Spinner localSpinner = (Spinner) findViewById(R.id.userRegisterLocal);
        localAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, localOptions);
        localAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        localSpinner.setAdapter(localAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = (TextView) findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Set content view back to main
     * @param v
     */
    public void returnToMain(View v) {
        error="";
        setContentView(R.layout.content_main);
    }

    /**
     * Login
     * Sets content view to Online if entered params are correct
     * @param v
     */
    public void login(View v) {
        error="";
        RequestParams rq=new RequestParams();
        final TextView userID=(EditText) findViewById(R.id.userID);
        final TextView pwd=(EditText) findViewById(R.id.pwd);
        rq.put("password", pwd.getText().toString());
        HttpUtils.post("login/" + userID.getText().toString(), rq, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                startActivity(new Intent(MainActivity.this, Online.class));
                refreshErrorMessage();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    //error += HttpUtils.getAbsoluteUrl("persons/" + tv.getText().toString());
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });
    }

    public void register(View v){
        error="";
        final TextView newUserID = findViewById(R.id.userRegisterID);
        final TextView newName = findViewById(R.id.userRegisterName);
        final TextView newAddress = findViewById(R.id.userRegisterAddress);

    }

    /**
     * Gets all opening hours
     * @param v
     */
    public void getAllOpeningHours(View v){
        error = "";
        RequestParams rq = new RequestParams();
        HttpUtils.get("openingHours/", rq, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {
                // final JSONArray[] allOP= {new JSONArray()};
                // JSONObject test = response.getJSONObject("openinHour");
                // allOP[0]=response;
                // System.out.println(allOP[0]);
//                JSONObject object= null;
                try {
                    for(int i=0; i<response.length(); i++) {
                        JSONObject object = response.getJSONObject(i);
//                        object = allOP[0].getJSONObject(i);
                        // String date= String.valueOf(object.get("DayOfWeek"));
                        // String start=String.valueOf(object.get("startTime"));
                        // String end=String.valueOf(object.get("endTime"));
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
                            TextView tvDate=(TextView) findViewById(R.id.oh_date_tuesday);
                            TextView tvStart=(TextView) findViewById(R.id.oh_start_time_tuesday);
                            TextView tvEnd=(TextView) findViewById(R.id.oh_end_time_tuesday);
                            tvDate.setText(date);
                            tvStart.setText(start);
                            tvEnd.setText(end);
                        }
                        if (date.equals("Wednesday")) {
                            TextView tvDate=(TextView) findViewById(R.id.oh_date_wednesday);
                            TextView tvStart=(TextView) findViewById(R.id.oh_start_time_wednesday);
                            TextView tvEnd=(TextView) findViewById(R.id.oh_end_time_wednesday);
                            tvDate.setText(date);
                            tvStart.setText(start);
                            tvEnd.setText(end);
                        }
                        if (date.equals("Thursday")) {
                            TextView tvDate=(TextView) findViewById(R.id.oh_date_thursday);
                            TextView tvStart=(TextView) findViewById(R.id.oh_start_time_thursday);
                            TextView tvEnd=(TextView) findViewById(R.id.oh_end_time_thursday);
                            tvDate.setText(date);
                            tvStart.setText(start);
                            tvEnd.setText(end);
                        }
                        if (date.equals("Friday")) {
                            TextView tvDate=(TextView) findViewById(R.id.oh_date_friday);
                            TextView tvStart=(TextView) findViewById(R.id.oh_start_time_friday);
                            TextView tvEnd=(TextView) findViewById(R.id.oh_end_time_friday);
                            tvDate.setText(date);
                            tvStart.setText(start);
                            tvEnd.setText(end);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // setContentView(R.layout.opening_hour);
                // refreshErrorMessage();
            }
            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    //error += HttpUtils.getAbsoluteUrl("persons/" + tv.getText().toString());
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                // refreshErrorMessage();
            }
        });
    }

    /**
     * Gets all media of both types
     * @param v
     */
    public void getAllMedia(View v) {
        error = "";
        RequestParams rq = new RequestParams();
        //media checkout-able
        HttpUtils.get("checkoutItems/", rq, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                final JSONArray[] allOP= {new JSONArray()};
                allOP[0]=response;
                JSONObject object= null;
                try {
                    for(int i=0; i<allOP[0].length(); i++) {
                        object = allOP[0].getJSONObject(i);
                        String type=String.valueOf(object.get("mediaType"));
                        int id=object.getInt("mediaID");
                        String name= object.getString("name");
                        String checkoutStatus=String.valueOf(object.getBoolean("isCheckedOut"));
                        String reserveStatus=String.valueOf(object.getBoolean("isReserved"));

                        TextView tvType=(TextView) findViewById(R.id.media_type);
                        TextView tvID=(TextView) findViewById(R.id.media_id);
                        TextView tvName=(TextView) findViewById(R.id.media_title);
                        TextView tvChecked=(TextView) findViewById(R.id.media_checked);
                        TextView tvReserved=(TextView) findViewById(R.id.media_reserved);
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
                refreshErrorMessage();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    //error += HttpUtils.getAbsoluteUrl("persons/" + tv.getText().toString());
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });
        //media non checkout-able
        HttpUtils.get("nonCheckOutItems/", rq, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                final JSONArray[] allOP= {new JSONArray()};
                allOP[0]=response;
                JSONObject object= null;
                try {
                    for(int i=0; i<allOP[0].length(); i++) {
                        object = allOP[0].getJSONObject(i);
                        String type=(String) object.get("mediaType");
                        int id=object.getInt("mediaID");
                        String name= object.getString("name");

                        TextView tvType=(TextView) findViewById(R.id.media_type);
                        TextView tvID=(TextView) findViewById(R.id.media_id);
                        TextView tvName=(TextView) findViewById(R.id.media_title);
                        tvType.setText(type);
                        tvID.setText(id);
                        tvName.setText(name);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setContentView(R.layout.media);
                refreshErrorMessage();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    //error += HttpUtils.getAbsoluteUrl("persons/" + tv.getText().toString());
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });
    }

    /**
     * Gets all events
     * @param v
     */
    public void getAllEvents(View v) {
        error = "";
        RequestParams rq = new RequestParams();
        HttpUtils.get("events/getAllEvents/", rq, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                final JSONArray[] allOP= {new JSONArray()};
                allOP[0]=response;
                JSONObject object= null;
                try {
                    for(int i=0; i<allOP[0].length(); i++) {
                        object = allOP[0].getJSONObject(i);
                        String name= object.getString("name");
                        String date=String.valueOf(object.get("date"));
                        String start=String.valueOf(object.get("eventStart"));
                        String end=String.valueOf(object.get("eventEnd"));

                        TextView tvName=(TextView) findViewById(R.id.evt_name);
                        TextView tvDate=(TextView) findViewById(R.id.evt_date);
                        TextView tvStart=(TextView) findViewById(R.id.evt_start);
                        TextView tvEnd=(TextView) findViewById(R.id.evt_end);
                        tvDate.setText(date);
                        tvStart.setText(start);
                        tvEnd.setText(end);
                        tvName.setText(name);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setContentView(R.layout.event);
                refreshErrorMessage();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    //error += HttpUtils.getAbsoluteUrl("persons/" + tv.getText().toString());
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });
    }
}
