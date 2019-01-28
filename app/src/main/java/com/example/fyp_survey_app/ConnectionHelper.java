package com.example.fyp_survey_app;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {

    /* user name. */
    String dbUserName = "glihorrcogsbsf";
    /* password. */
    String dbPassword = "d02d99f7fec51b2ee736a7ee753d8af3ef92445d617f19906fdd0dcee66996fa";
    /* database name */
    String ServenName = "ec2-54-225-227-125.compute-1.amazonaws.com";

    public Connection connection(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        java.sql.Connection connection = null;
        String dbConnUrl = null;

        try{
            dbConnUrl = "jdbc:postgres://glihorrcogsbsf:d02d99f7fec51b2ee736a7ee753d8af3ef92445d617f19906fdd0dcee66996fa@ec2-54-225-227-125.compute-1.amazonaws.com:5432/dbv5me60jqllra";
            connection = DriverManager.getConnection(dbConnUrl);
        }catch (SQLException se){
            Log.e("error from SQL ",se.getMessage());
        }
        return connection;
    }

}
