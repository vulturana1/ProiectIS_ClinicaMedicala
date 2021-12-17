package com.example.demo.repository;


import com.example.demo.connection.ConnectionFactory;
import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class DoctorRepository {

    private static final String insertStatementString = "INSERT INTO user (firstName,lastName)" + " VALUES (?,?)";

    public int addPatient(User user) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, user.getFirstName());
            insertStatement.setString(2, user.getLastName());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            //LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }




}
