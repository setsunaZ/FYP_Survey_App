package com.example.fyp_survey_app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignupActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        Button doctorButton = (Button)findViewById(R.layout.signup_activity.doctor);
        Button patientButton = (Button)findViewById(R.layout.signup_activity.patient);

        doctorButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                doctorSignUp();
            }
        });
        patientButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                patientSignUp();
            }
        });
    }


    //TODO: doctor sign up function
    public void doctorSignUp(){
        setContentView(R.layout.doctor_signup_layout);
    }

    //TODO: patient sign up function
    public void patientSignUp(){
        setContentView(R.layout.patient_signup_layout);
    }
}
