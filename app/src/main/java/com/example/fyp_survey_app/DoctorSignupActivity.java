package com.example.fyp_survey_app;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp_survey_app.Model.Doctor;

import java.util.Date;
import java.util.Locale;

public class DoctorSignupActivity extends Activity{

    private static final String TAG = "DoctorSignupActivity";
    ConnectionHelper connectionHelper;
    Doctor doctor;

    final Calendar myCalendar = Calendar.getInstance();

    Button confirmButton = (Button)findViewById(R.id.confirmButton);
    TextView backButton = (TextView) findViewById(R.id.backButton);

    EditText firstName = (EditText)findViewById(R.id.firstNameText);
    EditText lastName = (EditText)findViewById(R.id.lastNameText);
    EditText emailText = (EditText)findViewById(R.id.emailText);
    EditText passwordText = (EditText)findViewById(R.id.passwordText);
    EditText birthdayText = (EditText)findViewById(R.id.birthdayText);
    EditText departmentText = (EditText)findViewById(R.id.departmentText);
    EditText roleText = (EditText)findViewById(R.id.roleText);

    RadioGroup rg = (RadioGroup)findViewById(R.id.genderRG);
    RadioButton rb;

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    DatePickerDialog datePickerDialog = new DatePickerDialog(DoctorSignupActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_signup_layout1);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        confirmButton.setEnabled(false);

//        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
//                R.style.AppTheme_Dark_Dialog);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setMessage("Creating Account...");
//        progressDialog.show();

        Date currentTime = Calendar.getInstance().getTime();

        doctor.setDoctor_ID(firstName.getText().toString() + currentTime.toString());
        doctor.setFirstName(firstName.getText().toString());
        doctor.setLastName(lastName.getText().toString());
        doctor.setEmailAddress(emailText.getText().toString());
        doctor.setPassword(passwordText.getText().toString());
        doctor.setGender(rb.getText().toString());
        doctor.setBirthday(birthdayText.getText().toString());
        doctor.setDepartment(departmentText.getText().toString());
        doctor.setRole(roleText.getText().toString());

        // TODO: Implement your own signup logic here.
        boolean result = connectionHelper.addDoctor(doctor);
        if(result == true) {
            onSignupSuccess();
        }

//        new android.os.Handler().postDelayed(
//                new Runnable() {
//                    public void run() {
//                        // On complete call either onSignupSuccess or onSignupFailed
//                        // depending on success
//                        onSignupSuccess();
//                        // onSignupFailed();
//                        progressDialog.dismiss();
//                    }
//                }, 3000);
    }


    public void onSignupSuccess() {
        confirmButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        confirmButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = firstName.getText().toString();
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            firstName.setError("at least 3 characters");
            valid = false;
        } else {
            firstName.setError(null);
        }

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

    public void rbclick(View v) {
        int radioButtonId = rg.getCheckedRadioButtonId();
        rb = (RadioButton)findViewById(radioButtonId);
    }

    public void birthdayOnClick(View v) {
        // TODO Auto-generated method stub
        datePickerDialog.show();
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.CHINA);

        birthdayText.setText(sdf.format(myCalendar.getTime()));
    }

}




