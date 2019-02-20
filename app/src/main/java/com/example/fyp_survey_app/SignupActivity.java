package com.example.fyp_survey_app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class SignupActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        Button doctorButton = (Button)findViewById(R.layout.signup_activity.doctor);
    }
}
