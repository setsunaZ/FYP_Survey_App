package com.example.fyp_survey_app;

import android.os.StrictMode;
import android.util.Log;

import com.example.fyp_survey_app.Model.Doctor;
import com.example.fyp_survey_app.Model.Patient;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionHelper {

    //Database URL
    private static final String JDBC_DATABASE_URL = "jdbc:postgres://glihorrcogsbsf:d02d99f7fec51b2ee736a7ee753d8af3ef92445d617f19906fdd0dcee66996fa@ec2-54-225-227-125.compute-1.amazonaws.com:5432/dbv5me60jqllra";

//    public Connection getConnection(){
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//        java.sql.Connection connection = null;
//        String dbConnUrl = null;
//
//        try{
//            dbConnUrl = "jdbc:postgres://glihorrcogsbsf:d02d99f7fec51b2ee736a7ee753d8af3ef92445d617f19906fdd0dcee66996fa@ec2-54-225-227-125.compute-1.amazonaws.com:5432/dbv5me60jqllra";
//            connection = DriverManager.getConnection(dbConnUrl);
//        }catch (SQLException se){
//            Log.e("error from SQL ",se.getMessage());
//        }
//        return connection;
//    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_DATABASE_URL);
    }

    public String checkUser(String email, String password){

        try{
            Connection conn = getConnection();
            PreparedStatement stmt1 = conn.prepareStatement("SELECT EmailAddress,Password FROM ? WHERE EmailAddress=? and Password=?");

            stmt1.setString(1, "Doctor");
            stmt1.setString(2, email);
            stmt1.setString(3, password);
            ResultSet doctorResults = stmt1.executeQuery();

            stmt1.setString(1, "Patient");
            stmt1.setString(2, email);
            stmt1.setString(3, password);
            ResultSet patientResults = stmt1.executeQuery();

            if(doctorResults.next()){
                return "doctor";
            }
            if(patientResults.next()){
                return "patient";
            }
            if(!doctorResults.next() && !patientResults.next()) {
                Log.d("Database Verifivcation","Wrong password! ");
            }
        }catch (SQLException se){
            Log.d("Database Verifivcation",se.getMessage());
        }

        return null;
    }

    //TODO: Sign up functions
    public boolean addDoctor(Doctor doctor){
        return false;
    }

    public boolean addPatient(){
        return false;
    }


    public Doctor getDoctorAccount(String email){
        Doctor doctor = new Doctor();
        try{
            Connection conn = getConnection();
            String query = "SELECT * FROM Doctor WHERE EmailAddress=" + email;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next())
            {
                doctor.setDoctor_ID(rs.getInt("Doctor_ID"));
                doctor.setBirthday(rs.getDate("Birthday"));
                doctor.setDepartment(rs.getString("Department"));
                doctor.setEmailAddress(rs.getString("EmailAddress"));
                doctor.setGender(rs.getString("Gender"));
                doctor.setPassword(rs.getString("Password"));
                doctor.setRole(rs.getString("Role"));
                doctor.setUsername(rs.getString("Username"));
            }
            stmt.close();

            return doctor;
        }catch (SQLException se){
            Log.e("Create Doctor Account", se.getMessage());
        }

        return null;
    }

    public Patient getPatientAccount(String email){
        Patient patient = new Patient();
        try{
            Connection conn = getConnection();
            String query = "SELECT * FROM Patient WHERE EmailAddress=" + email;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next())
            {
                patient.setPatient_ID(rs.getInt("Patient_ID"));
                patient.setBirthday(rs.getDate("Birthday"));
                patient.setContact(rs.getString("Contact"));
                patient.setEmailAddress(rs.getString("EmailAddress"));
                patient.setGender(rs.getString("Gender"));
                patient.setPassword(rs.getString("Password"));
                patient.setAddress(rs.getString("Address"));
                patient.setPostalCode(rs.getString("PostalCode"));
            }
            stmt.close();

            return patient;
        }catch (SQLException se){
            Log.e("Create Patient Account", se.getMessage());
        }

        return null;
    }
}
