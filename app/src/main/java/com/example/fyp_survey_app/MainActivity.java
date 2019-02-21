package com.example.fyp_survey_app;

import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp_survey_app.Model.Doctor;
import com.example.fyp_survey_app.Model.Patient;

public class MainActivity extends Activity {
    private final Activity activity = MainActivity.this;

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    Button loginButton;
    TextView signupButton;
    EditText emailText, passwordText;

    ConnectionHelper connectionHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        loginButton = (Button)findViewById(R.id.button);
        passwordText = (EditText)findViewById(R.id.password);
        emailText = (EditText)findViewById(R.id.username);
        signupButton = (TextView)findViewById(R.id.signup);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        loginButton.setEnabled(false);

        // TODO: progress dialog deprecated, think alternative way
//        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
//                R.style.AppTheme_Dark_Dialog);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setMessage("Authenticating...");
//        progressDialog.show();

        // TODO: Authentication Logic
        verifyFromDatabase();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        //progressDialog.dismiss();
                    }
                }, 3000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("enter a valid email address");
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }

    private void verifyFromDatabase() {
        //Validation for Doctor
        if (connectionHelper.checkUser(emailText.getText().toString().trim()
                , passwordText.getText().toString().trim()) == "doctor") {
            Doctor doctor = connectionHelper.getDoctorAccount(emailText.getText().toString());
            Intent doctorAccountsIntent = new Intent(activity, DoctorManagementActivity.class);
            doctorAccountsIntent.putExtra("Doctor_ID", doctor.getDoctor_ID());
            emptyInputEditText();
            startActivity(doctorAccountsIntent);
        } else {
            Log.d(TAG, "Verification Failed!");
        }

        //Validation for Patient
        if (connectionHelper.checkUser(emailText.getText().toString().trim()
                , passwordText.getText().toString().trim()) == "patient") {
            Patient patient = connectionHelper.getPatientAccount(emailText.getText().toString());
            Intent patientAccountsIntent = new Intent(activity, PatientManagementActivity.class);
            patientAccountsIntent.putExtra("Patient_ID", patient.getPatient_ID());
            emptyInputEditText();
            startActivity(patientAccountsIntent);
        } else {
            Log.d(TAG, "Verification Failed!");
        }

    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        emailText.setText(null);
        passwordText.setText(null);
    }
}
