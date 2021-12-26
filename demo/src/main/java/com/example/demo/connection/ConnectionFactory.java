package com.example.demo.connection;

import com.example.demo.model.User;
import com.example.demo.repository.DoctorRepository;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.logging.Logger;

/**
 * In aceasta clasa se efectueaza conectarea bazei de date cu proiectul. de asemea se creaza o conexiune intre acestea
 */
public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/ClinicaMedicala";
    private static final String USER = "root";
    private static final String PASS = "root";

    private static ConnectionFactory singleInstance = new ConnectionFactory();

    public ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //----------------
        return connection;
    }

    public static Connection getConnection() {
        return singleInstance.createConnection();
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

//    public static void main(String[] args) {
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        try {
//            connectionFactory.createConnection();
//        } catch (Exception exception) {
//            System.out.printf("NU");
//        }
//        DoctorRepository dc = new DoctorRepository();
//        try {
//
////            dc.addPatient(new User(1, "Constantin", "Senila", "cevaacolo", "parola", "emailAcolo@ceva.com", "patient", null, null, "0787340611"));
//            dc.deletePatient(1);
//            ResultSet rs=dc.showPatients();
//            while(rs.next()){
//                //Retrieve by column name
//                String id = rs.getString("id");
//                String nume = rs.getString("firstName");
//                String prenume = rs.getString("lastName");
//
//                String s = id + "  " + nume + " " + prenume;
//                System.out.println(s);
//            }
////            String nume=rs.getString("firstName");
////            System.out.println(nume);
//        }catch(Exception exception){
//            System.out.println("ceva nu meie");
//        }
//    }

}