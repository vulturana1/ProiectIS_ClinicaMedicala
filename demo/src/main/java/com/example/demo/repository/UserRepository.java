package com.example.demo.repository;

import com.example.demo.connection.ConnectionFactory;
import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class UserRepository {

    private static final String insertStatementString = "INSERT INTO user (firstName, lastName, username, password, role, phoneNumber)" + " VALUES (?,?,?,?,?,?)";

    public boolean addUser(User user) {
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
                insertStatement.setString(5, user.getRole());
                insertStatement.setString(6, user.getPhoneNumber());
                insertStatement.executeUpdate();
            } else{
                System.out.println("nu se poate adauga pt ca este deja unu!");///pe interfata
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
}
