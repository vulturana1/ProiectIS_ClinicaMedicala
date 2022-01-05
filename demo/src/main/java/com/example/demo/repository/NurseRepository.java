package com.example.demo.repository;

import com.example.demo.connection.ConnectionFactory;
import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class NurseRepository {
    private static final String insertStatementString = "INSERT INTO user (firstName, lastName, username, password, role, phoneNumber)" + " VALUES (?,?,?,?,?,?)";
    private static final String findNurseStatementString = "SELECT * FROM user WHERE username = ?";

    public boolean addPatient(User user) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            if (verifyUsername(user.getUsername())) {
                insertStatement.setString(1, user.getFirstName());
                insertStatement.setString(2, user.getLastName());
                insertStatement.setString(3, user.getUsername());
                insertStatement.setString(4, user.getPassword());
                insertStatement.setString(5, "PATIENT");
                insertStatement.setString(6, user.getPhoneNumber());
                insertStatement.executeUpdate();
            } else {
                System.out.println("nu se poate adauga pt  ca este deja unu!");///pe interfata
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return true;
    }
    private String createVerifyUsernameString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" username ");
        sb.append(" FROM ");
        sb.append("user WHERE username = ?");
        return sb.toString();
    }

    private boolean verifyUsername(String username) {
        Connection connection = ConnectionFactory.getConnection();
        String rezSelect = createVerifyUsernameString();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(rezSelect);
            statement.setString(1, username);
            rs = statement.executeQuery();
            if (!rs.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String createDeleteQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("Delete ");
        sb.append("from ");
        sb.append("user");
        sb.append(" where username = ?");
        return sb.toString();
    }

    public void deletePatient(String username) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        String rezDelete = createDeleteQuery();

        //System.out.println(rezDelete);
        try {
            statement = connection.prepareStatement(rezDelete);
            statement.setString(1, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    private String createSelectQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append("user WHERE role = 'patient'");
        return sb.toString();
    }

    public ResultSet showPatients() {
        Connection connection = ConnectionFactory.getConnection();
        String rezSelect = createSelectQuery();
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

    public ResultSet findNurse(String username){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(findNurseStatementString);
            statement.setString(1, username);
            rs = statement.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}