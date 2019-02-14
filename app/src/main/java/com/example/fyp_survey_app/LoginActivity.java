package com.example.fyp_survey_app;

import android.os.Bundle;
import android.view.View;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends Activity {
    Button b1,b2;
    EditText usernameText,passwordText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //b1 = (Button)findViewById(R.id.button);
        //usernameText = (EditText)findViewById(R.id.editText);
        //passwordText = (EditText)findViewById(R.id.editText2);

        //b2 = (Button)findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed1.getText().toString().equals("admin") &&
                        ed2.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(),
                            "Redirecting...",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Wrong
                            Credentials",Toast.LENGTH_SHORT).show();

                            tx1.setVisibility(View.VISIBLE);
                    tx1.setBackgroundColor(Color.RED);
                    counter--;
                    tx1.setText(Integer.toString(counter));

                    if (counter == 0) {
                        b1.setEnabled(false);
                    }
                }
            }
        });


    }
