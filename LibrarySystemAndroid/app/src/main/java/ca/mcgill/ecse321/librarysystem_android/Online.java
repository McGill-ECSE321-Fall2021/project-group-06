package ca.mcgill.ecse321.librarysystem_android;

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
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

public class Online extends AppCompatActivity {
    private String error = null;
    private AppBarConfiguration appBarConfiguration;

    // Initializing Online page with data from server
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ca.mcgill.ecse321.librarysystem_android.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
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
        TextView tvError = findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }

    /**
     * addEvent
     * Creates an event in current view
     * @param v: current view
     */
    public void addEvent(View v) {
        error = "";

        // Retrieve all fields written by user in "Book Event" section and add those as requested parameters
        RequestParams rq = new RequestParams();
        final TextView eventName = findViewById(R.id.event_name);
        final TextView eventDate = findViewById(R.id.event_date);
        final TextView eventStart = findViewById(R.id.event_start);
        final TextView eventEnd = findViewById(R.id.event_end);
        rq.put("eventDate", eventDate.getText().toString());
        rq.put("eventStart", eventStart.getText().toString());
        rq.put("eventEnd", eventEnd.getText().toString());

        // REST Service call POST with Http: creates an Event on success (see EventController.java in backend)
        // On failure: displays error message on top of screen of current view
        HttpUtils.post("/create_event/" + eventName.getText().toString(), rq, new JsonHttpResponseHandler() {
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
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });
    }

    /**
     * updateInfo
     * Updates Online Account information
     * If some fields are to remain unchanged, reenter previously entered information
     * @param v: current view
     */
    public void updateInfo(View v) {
        error = "";

        // Retrieve all fields written by user in "Update Info" section and add those as requested parameters
        RequestParams rq = new RequestParams();
        final TextView accountID = findViewById(R.id.account_to_update);
        final TextView addr = findViewById(R.id.updated_address);
        final TextView name = findViewById(R.id.updated_name);
        final TextView pwd = findViewById(R.id.updated_password);
        rq.put("address", addr.getText().toString());
        rq.put("name", name.getText().toString());
        rq.put("password", pwd.getText().toString());

        // REST Service call POST with Http: updates Online account on success (see OnlineController.java in backend)
        // On failure: displays error message on top of screen of current view
        HttpUtils.post("edit_online/" + accountID.getText().toString(), rq, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
                accountID.setText("");
                addr.setText("");
                name.setText("");
                pwd.setText("");
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });
    }

    /**
     * reserveMedia
     * Reserve media item from Online account
     * @param v: current view
     */
    public void reserveMedia(View v) {
        error = "";

        // Retrieve all fields written by user in "Reserve Media" section and add those as requested parameters
        RequestParams rq = new RequestParams();
        final TextView accountID = findViewById(R.id.userID);
        final TextView mediaID = findViewById(R.id.mediaID);
        rq.put("userId", accountID.getText().toString());

        // REST Service call PUT with Http: reserves Media item to Online account on success (see OnlineController.java in backend)
        // On failure: displays error message on top of screen of current view
        HttpUtils.put("reserve_media_online/" + mediaID.getText().toString(), rq, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
                accountID.setText("");
                mediaID.setText("");
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });
    }

    /**
     * returnToMain
     * Set current content view back to main page (Login page)
     * @param v: current view
     */
    public void returnToMain(View v) {
        error="";
        setContentView(R.layout.content_main);
    }

}
