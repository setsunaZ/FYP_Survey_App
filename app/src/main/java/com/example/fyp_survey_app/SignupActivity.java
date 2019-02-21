package com.example.fyp_survey_app;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.widget.Button;

public class SignupActivity extends Activity {
    private static final String TAG = "SignupActivity";
    private static final int REQUEST_SIGNUP = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        Button doctorButton = (Button)findViewById(R.id.doctorButton);
        Button patientButton = (Button)findViewById(R.id.patientButton);

        doctorButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DoctorSignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });

        patientButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

}
