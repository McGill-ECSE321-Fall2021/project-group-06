package ca.mcgill.ecse321.librarysystem_android;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
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
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.sql.Time;

public class MainActivity extends AppCompatActivity {
    private String error = null;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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

    public void returnToMain(View v) {
        error="";
        setContentView(R.layout.content_main);
    }

    public void login(View v) {
        error="";
        RequestParams rq=new RequestParams();
        final TextView userID=(TextView) findViewById(R.id.userID);
        final TextView pwd=(TextView) findViewById(R.id.pwd);
        rq.put("pwd", pwd.getText().toString());
        HttpUtils.post("/login" + userID.getText().toString(), rq, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                setContentView(R.layout.Online);
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

    public void addEvent(View v) {
        error = "";
        RequestParams rq = new RequestParams();
        final TextView eventName = (TextView) findViewById(R.id.newperson_name);
        final TextView eventDate = (TextView) findViewById(R.id.newperson_date);
        final TextView eventStart = (TextView) findViewById(R.id.newperson_start);
        final TextView eventEnd = (TextView) findViewById(R.id.newperson_end);
        rq.put("eventDate", eventDate.getText().toString());
        rq.put("eventStart", eventStart.getText().toString());
        rq.put("eventEnd", eventEnd.getText().toString());
        HttpUtils.post("create_event/" + eventName.getText().toString(), rq, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
                eventName.setText("");
                eventDate.setText("");
                eventStart.setText("");
                eventEnd.setText("");

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
    //Opening Hours Begin, for now only View All OH is needed
    /*
    public void createOpeningHours(View v){
        error = "";
        RequestParams rq = new RequestParams();
        final TextView id = (TextView) findViewById(R.id.newperson_id);
        final TextView dayOfWeek = (TextView) findViewById(R.id.newperson_day);
        final TextView startTime = (TextView) findViewById(R.id.newperson_start);
        final TextView endTime = (TextView) findViewById(R.id.newperson_end);
        rq.put("dayOfWeek", dayOfWeek.getText().toString());
        rq.put("startTime", startTime.getText().toString());
        rq.put("endTime", endTime.getText().toString());
        HttpUtils.post("openingHours/" + id.getText().toString(), rq, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
                id.setText("");
                dayOfWeek.setText("");
                startTime.setText("");
                endTime.setText("");

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
    public void updateOpeningHours(View v){
        error = "";
        RequestParams rq = new RequestParams();
        final TextView id = (TextView) findViewById(R.id.newperson_id);
        final TextView dayOfWeek = (TextView) findViewById(R.id.newperson_day);
        final TextView startTime = (TextView) findViewById(R.id.newperson_start);
        final TextView endTime = (TextView) findViewById(R.id.newperson_end);
        rq.put("dayOfWeek", dayOfWeek.getText().toString());
        rq.put("startTime", startTime.getText().toString());
        rq.put("endTime", endTime.getText().toString());
        HttpUtils.post("updateOpeningHour/" + id.getText().toString(), rq, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
                id.setText("");
                dayOfWeek.setText("");
                startTime.setText("");
                endTime.setText("");

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
    }*/
    public void getAllOpeningHours(View v){
        error = "";
        RequestParams rq = new RequestParams();
        HttpUtils.post("openingHours/", rq, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                final JSONArray[] allOP= {new JSONArray()};
                allOP[0]=response;
                JSONObject object= null;
                try {
                    for(int i=0; i<allOP[0].length(); i++) {
                        object = allOP[0].getJSONObject(i);
                        String date=object.getString("DayOfWeek");
                        String start=object.getString("startTime");
                        String end= object.getString("endTime");

                        TextView tvDate=(TextView) findViewById(R.id.evt_date);
                        TextView tvStart=(TextView) findViewById(R.id.evt_start_time);
                        TextView tvEnd=(TextView) findViewById(R.id.evt_end_time);
                        tvDate.setText(date);
                        tvStart.setText(start);
                        tvEnd.setText(end);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setContentView(R.layout.opening_hour);
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
    /*
    public void getOpeningHourById(View v){
        error = "";
        RequestParams rq = new RequestParams();
        final TextView id = (TextView) findViewById(R.id.newperson_id);
        HttpUtils.post("getopeningHours/" + id.getText().toString(), rq, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
                id.setText("");
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
    public void deleteOpeningHours(View v){
        error = "";
        RequestParams rq = new RequestParams();
        final TextView id = (TextView) findViewById(R.id.newperson_id);
        HttpUtils.post("deleteOpeningHour/" + id.getText().toString(), rq, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
                id.setText("");
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
    }*/
    //Opening Hours End
    public void getAllMedia(View v) {
        error = "";
        RequestParams rq = new RequestParams();
        HttpUtils.post("checkoutItems/", rq, new JsonHttpResponseHandler() {
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
    }
}
