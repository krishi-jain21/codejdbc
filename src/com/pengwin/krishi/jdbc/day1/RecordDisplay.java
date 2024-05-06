/**
 * RecordDisplay class provides methods to connect to a MySQL database,
 * display employee records, and disconnect from the database.
 */
package com.pengwin.krishi.jdbc.day1;

import java.sql.*;

/**
 * RecordDisplay class provides methods to connect to a MySQL database,
 * display employee records, and disconnect from the database.
 */
public class RecordDisplay {

    private Connection connection;
    private Statement statement;
    private String dbUrl = "jdbc:mysql://localhost:3306/";
    private String dbUserName;
    private String dbPassword;

    /**
     * Constructor to initialize the RecordDisplay object with database name,
     * username, and password.
     * 
     * @param dbName     The name of the database to connect to.
     * @param dbUserName The username for database authentication.
     * @param dbPassword The password for database authentication.
     */
    public RecordDisplay(String dbName, String dbUserName, String dbPassword) {
        this.dbUserName = dbUserName;
        this.dbPassword = dbPassword;
        this.dbUrl += dbName;
    }

    /**
     * Connects to the database using the provided credentials.
     */
    public void connect() {
        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            statement = connection.createStatement();
            System.out.println("Connected to the database");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays employee records from the database. If empId is provided,
     * displays records for the specified employee.
     * 
     * @param empId The ID of the employee to display records for. If null, all
     *              records will be displayed.
     */
    public void displayRecords(Integer empId) {
        String query = "SELECT employeeid AS EmployeeID, CONCAT(first_name, ' ', last_name) AS Name, title AS Title, CONCAT(address, ', ', city, ', ', country) AS Address,"
        		+ " homephone AS HomePhone FROM employee";
        if (empId != null) {
            query += " WHERE employeeid = ?";
        }
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            if (empId != null) {
                statement.setInt(1, empId);
            }
            ResultSet resultSet = statement.executeQuery();
            System.out.println("EmployeeID  |  Name                    |  Title        |  Address                                     |  HomePhone");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
            while (resultSet.next()) {
                int employeeId = resultSet.getInt("EmployeeID");
                String name = resultSet.getString("Name");
                String title = resultSet.getString("Title");
                String address = resultSet.getString("Address");
                String homePhone = resultSet.getString("HomePhone");

                // Adjust spacing as needed based on your actual data lengths
                System.out.printf("%-11d|  %-24s|  %-12s|  %-45s|  %-13s%n", employeeId, name, title, address,homePhone);
            }
            resultSet.close();
            statement.close(); // Close the statement
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Disconnects from the database.
     */
    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Disconnected from the database");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}