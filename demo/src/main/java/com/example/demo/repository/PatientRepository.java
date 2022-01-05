package com.example.demo.repository;

import com.example.demo.connection.ConnectionFactory;
import com.example.demo.model.Appointment;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Repository
public class PatientRepository {

    private String createSelectQueryForRecipe() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("*");
        sb.append(" FROM ");
        sb.append("recipe WHERE usernamePatient = ?");
        return sb.toString();
    }

    public ResultSet showRecipe(String usernamePatient) {
        Connection connection = ConnectionFactory.getConnection();
        String rezSelect = createSelectQueryForRecipe();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(rezSelect);
            statement.setString(1, usernamePatient);
            ResultSet rs = statement.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final String insertAppointment = "INSERT INTO appointment (usernameDoctor, usernamePatient, date, time)" + " VALUES (?,?,?,?)";

    public void addAppointment(Appointment appointment) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;

        try {
            insertStatement = dbConnection.prepareStatement(insertAppointment, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, appointment.getUsernameDoctor());
            insertStatement.setString(2, appointment.getUsernamePatient());
            insertStatement.setString(3, appointment.getDate());
            insertStatement.setString(4, appointment.getTime());
            insertStatement.executeUpdate();
            //insertDoctorNotify(dbConnection, appointment);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    private static final String insertDoctorNotify = "INSERT INTO doctorNotify (usernameDoctor, message)" + " VALUES (?,?)";
    public void insertDoctorNotify(Appointment appointment) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement1 = null;
        try {
            insertStatement1 = dbConnection.prepareStatement(insertDoctorNotify, Statement.RETURN_GENERATED_KEYS);
            insertStatement1.setString(1, appointment.getUsernameDoctor());
            insertStatement1.setString(2, appointment.toString());
            insertStatement1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private String createSelectQueryForDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("*");
        sb.append(" FROM ");
        sb.append("user WHERE username = ?");
        return sb.toString();
    }

    public ResultSet showDetails(String username) {
        Connection connection = ConnectionFactory.getConnection();
        String rezSelect = createSelectQueryForDetails();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(rezSelect, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String createSelectQueryForDoctors() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append("user WHERE role = 'DOCTOR'");
        return sb.toString();
    }

    public ResultSet showDoctors() {
        Connection connection = ConnectionFactory.getConnection();
        String rezSelect = createSelectQueryForDoctors();
        try {
            Statement statement = connection.createStatement();
            statement.execute(rezSelect);
            ResultSet rs = statement.getResultSet();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String createSelectQueryForAppointments() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append("appointment WHERE usernamePatient = ?");
        return sb.toString();
    }

    public ResultSet showAppointments(String usernamePatient) {
        Connection connection = ConnectionFactory.getConnection();
        String rezSelect = createSelectQueryForAppointments();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(rezSelect);
            statement.setString(1, usernamePatient);
            ResultSet rs = statement.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}