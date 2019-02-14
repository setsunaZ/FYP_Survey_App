package com.example.fyp_survey_app;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.widget.Button;

public class MainActivity extends Activity {

    Button b1,b2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.mainactivity);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }




}
