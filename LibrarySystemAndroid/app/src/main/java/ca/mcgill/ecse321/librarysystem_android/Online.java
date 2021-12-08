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
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Online extends AppCompatActivity {
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

    /**
     * Creates an event
     * @param v
     */
    public void addEvent(View v) {
        error = "";
        RequestParams rq = new RequestParams();
        final TextView eventName = (TextView) findViewById(R.id.event_name);
        final TextView eventDate = (TextView) findViewById(R.id.event_date);
        final TextView eventStart = (TextView) findViewById(R.id.event_start);
        final TextView eventEnd = (TextView) findViewById(R.id.event_end);
        rq.put("eventDate", eventDate.getText().toString());
        rq.put("eventStart", eventStart.getText().toString());
        rq.put("eventEnd", eventEnd.getText().toString());
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
     * Updates Online Account information
     * If some fields are to remain unchanged, reenter previously entered information
     * @param v
     */
    public void updateInfo(View v) {
        error = "";
        RequestParams rq = new RequestParams();
        final TextView accountID = (EditText) findViewById(R.id.account_to_update);
        final TextView addr = (EditText) findViewById(R.id.updated_address);
        final TextView name = (EditText) findViewById(R.id.updated_name);
        final TextView pwd = (EditText) findViewById(R.id.updated_password);
        rq.put("address", addr.getText().toString());
        rq.put("name", name.getText().toString());
        rq.put("password", pwd.getText().toString());
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
                    //error += HttpUtils.getAbsoluteUrl("persons/" + tv.getText().toString());
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });
    }
    public void reserveMedia(View v) {
        error = "";
        RequestParams rq = new RequestParams();
        final TextView accountID = (EditText) findViewById(R.id.userID);
        final TextView mediaID = (EditText) findViewById(R.id.mediaID);
        rq.put("userId", accountID.getText().toString());
        HttpUtils.post("reserve_media_online/" + mediaID.getText().toString(), rq, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
                accountID.setText("");
                mediaID.setText("");
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
     * Set content view back to main
     * @param v
     */
    public void returnToMain(View v) {
        error="";
        setContentView(R.layout.content_main);
    }

}
