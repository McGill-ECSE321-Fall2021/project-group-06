package ca.mcgill.ecse321.librarysystem_android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Login extends AppCompatActivity{

    private String error = null;
    private Button loginButton;
    private Button mediaButton;
    private Button eventButton;
    private Button ohButton;
    private EditText loginID;
    private EditText loginPassword;
    private Button registerButton;
    // Add more EditText private variables if needed

    private static final String HEADER = "User Login";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loginID = (EditText) findViewById(R.id.user_loginID);
        loginPassword = (EditText) findViewById(R.id.user_loginPwd);
        loginButton = (Button) findViewById(R.id.login_button);
        mediaButton = (Button) findViewById(R.id.media_button);
        eventButton = (Button) findViewById(R.id.event_button);
        ohButton = (Button) findViewById(R.id.oh_button);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                loginUser();
            }
        });

        mediaButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getToMediaPage();
            }
        });

        eventButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getToEventsPage();
            }
        });

        ohButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getToOHPage();
            }
        });

        registerButton = (Button) findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                registerUser();
            }
        });

        refreshErrorMessage();
    }


    public void loginUser(){

    }

    public void registerUser(){

    }

    public void getToMediaPage(){
        Intent intent = new Intent(this, MainActivity.class); // Might need to be changed to Media.java
        startActivity(intent);
    }

    public void getToEventsPage(){
        Intent intent = new Intent(this, MainActivity.class); // Might need to be changed to Event.java
        startActivity(intent);
    }

    public void getToOHPage(){
        Intent intent = new Intent(this, MainActivity.class); // Might need to be changed to OpeningHour.java
        startActivity(intent);
    }

    private void refreshErrorMessage(){
        TextView tvError = (TextView) findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0){
            tvError.setVisibility(View.GONE);
        }
        else{
            tvError.setVisibility(View.VISIBLE);
        }
    }

}
