package application.postgre;

import ComProg.ResultSet;
import java.sql.*;

public class DatabaseTableName { //modify this per table
    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:postgresql://localhost:5432/cpif"; //modify this with your own DB URL
    private static final String USER = "postgres"; //modify this with your database username
    private static final String PASSWORD = "1234"; //modify this with your database password

    // JDBC variables for opening, closing and managing connection
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    // Method to establish connection with the database
    // *You can auto initialize when the Application starts or you can manually do so for every query
    public static void connect() {
        try {
            // Register JDBC driver
            Class.forName("org.postgresql.Driver");

            // Open a connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Connected to the database.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to close connection
    // Don't forget to close when your Application closes or you can manually close every query
    public static void close() {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();

            System.out.println("Connection closed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to insert data into the database
    public static void insertData(String username, String email, String password, String first_name, String last_name) {
        try {
            statement = connection.createStatement();
            String sql = "INSERT INTO studyflow (email, password, username, first_name, last_name) VALUES ('" + email + "', '" + password + "', '" + username + "', '" + first_name + "', '" + last_name + "')";
            statement.executeUpdate(sql);
            System.out.println("Data inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve data from the database
    // Modify this to return the values and use it to display in your App if needed
    public static void retrieveData(String email) {
        try {
            statement = connection.createStatement();
            String sql = "SELECT password FROM studyflow WHERE email = " + email;
            resultSet = statement.executeQuery(sql);
	    
	    //resultSet would hold all the data with column names
            while (resultSet.next()) {
                System.out.println(resultSet.getString("password"));
//                System.out.println("Name: " + resultSet.getString("name") + ", Email: " + resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update data in the database
    // Modify this to update the fields needed or create one for each. you can also retrieve first then ask for changes
    public static void updateData(int id, String newName, String newEmail) {
        try {
            statement = connection.createStatement();
            String sql = "UPDATE users SET name='" + newName + "', email='" + newEmail + "' WHERE id=" + id;
            statement.executeUpdate(sql);
            System.out.println("Data updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete data from the database
    // Delete rows and columns if needed
    public static void deleteData(int id) {
        try {
            statement = connection.createStatement();
            String sql = "DELETE FROM users WHERE id=" + id;
            statement.executeUpdate(sql);
            System.out.println("Data deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        connect();
           insertData("studyflow2023", "studyflow2023@gmail.com" , "studyflow123", "sweqsds", "qwdqdsd");
//        retrieveData("studyflow.ph@gmail.com");
    }
}
