/**
 * 
 */
package com.pengwin.krishi.jdbc.day1;

import java.sql.*;
/**
 * 
 */
public class RecordDisplay {

	private Connection connection;
	private Statement statement;
	private String dbUrl = "jdbc:mysql://localhost:3306/";
    private String dbUserName;
    private String dbPassword;
    
    
    public RecordDisplay(String dbName,String dbUserName, String dbPassword){
    	
    	this.dbUserName = dbUserName;
    	this.dbPassword = dbPassword;
    	this.dbUrl += dbName;
    	
    }
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
    
    
    
    public void displayRecords() {
        String query = "SELECT employeeid AS EmployeeID, CONCAT(first_name, ' ', last_name) AS Name, title AS Title, CONCAT(address, ', ', city, ', ', country) AS Address, homephone AS HomePhone FROM employee";
        try {
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("EmployeeID  |  Name                    |  Title        |  Address                                     |  HomePhone");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

            while(resultSet.next()) {
                int employeeId = resultSet.getInt("EmployeeID");
                String name = resultSet.getString("Name");
                String title = resultSet.getString("Title");
                String address = resultSet.getString("Address");
                String homePhone = resultSet.getString("HomePhone");
                
                // Adjust spacing as needed based on your actual data lengths
                System.out.printf("%-11d|  %-24s|  %-12s|  %-45s|  %-13s%n", employeeId, name, title, address, homePhone);
            }
            resultSet.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void disconnect() {
    	try {
    		if (connection!= null) {
    			connection.close();
    			System.out.println("Disconnected from the database"); 
    		}
    		
    	}
    	catch( SQLException e) {
    		e.printStackTrace();
    	}
    	
    }
    
}



