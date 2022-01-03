package com.example.demo.repository;


import com.example.demo.connection.ConnectionFactory;
import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class DoctorRepository {

    private static final String insertStatementString = "INSERT INTO user (firstName, lastName, username, password, role, phoneNumber)" + " VALUES (?,?,?,?,?,?)";
    private static final String findDoctorStatementString = "SELECT * FROM user WHERE username = ?";
    private static final String findRecipeStatementString = "SELECT * FROM recipe WHERE id = ?";

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

    public boolean addNurse(User user) {
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
                insertStatement.setString(5, "NURSE");
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

    private static final String insertRecipe = "INSERT INTO recipe (usernameDoctor, usernamePatient, listOfDrugs)" + " VALUES (?,?,?)";

    public void addRecipe(Recipe recipe) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        try {
            insertStatement = dbConnection.prepareStatement(insertRecipe, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, recipe.getUsernameDoctor());
            insertStatement.setString(2, recipe.getUsernamePatient());
            insertStatement.setString(3, recipe.getListOfDrugs());
            insertStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    private String updateQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("update ");
        sb.append("recipe" + " set ");
        sb.append("listOfDrugs = ?");
        sb.append(" where id = ?");
        return sb.toString();
    }

    /////legat de update la reteta
    public void updateRecipe(int id, String listOfDrugs) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(updateQuery());
            statement.setString(1, listOfDrugs);
            statement.setString(2, String.valueOf(id));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    public ResultSet findRecipe(int id){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(findRecipeStatementString);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String createDeleteQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("Delete ");
        sb.append("from ");
        sb.append("user");
        sb.append(" where username" + " = ? ");
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

    public void deleteNurse(String username) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        String rezDelete = createDeleteQuery();
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

    private String createNotifyDoctor() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" message ");
        sb.append(" FROM ");
        sb.append("doctorNotify WHERE usernameDoctor = ?");
        return sb.toString();
    }

    public ResultSet notifyDoctor(String username) {
        Connection connection = ConnectionFactory.getConnection();
        String rezSelect = createNotifyDoctor();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(rezSelect);
            statement.setString(1, username);
            rs = statement.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String createSelectQueryForPatients() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append("user WHERE role = 'PATIENT'");
        return sb.toString();
    }

    public ResultSet showPatients() {
        Connection connection = ConnectionFactory.getConnection();
        String rezSelect = createSelectQueryForPatients();
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

    private String createSelectQueryForNurses() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append("user WHERE role = 'nurse'");
        return sb.toString();
    }

    public ResultSet showNurses() {
        Connection connection = ConnectionFactory.getConnection();
        String rezSelect = createSelectQueryForNurses();
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

    public ResultSet findDoctor(String username){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(findDoctorStatementString);
            statement.setString(1, username);
            rs = statement.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String createSelectQueryForRecipe() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("*");
        sb.append(" FROM ");
        sb.append("recipe WHERE usernameDoctor = ?");
        return sb.toString();
    }

    public ResultSet showRecipe(String usernameDoctor) {
        Connection connection = ConnectionFactory.getConnection();
        String rezSelect = createSelectQueryForRecipe();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(rezSelect);
            statement.setString(1, usernameDoctor);
            ResultSet rs = statement.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}