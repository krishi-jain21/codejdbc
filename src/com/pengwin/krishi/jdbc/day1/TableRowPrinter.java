package com.pengwin.krishi.jdbc.day1;

import java.sql.*;		
		
public class TableRowPrinter {		
    private Connection conn;		
		
    public void connect() {		
        String url = PropertyUtil.getProperty("url");		
        String username = PropertyUtil.getProperty("username");		
        String password = PropertyUtil.getProperty("password");		
		
        try {		
            Class.forName("com.mysql.cj.jdbc.Driver");		
            conn = DriverManager.getConnection(url, username, password);		
            System.out.println("Connected to database successfully.");		
        } catch (Exception e) {		
            e.printStackTrace();		
        }		
    }		
		
  
    public void displayRecords() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT  employeeid AS EmployeeID, CONCAT(first_name, ' ', last_name) AS Name, title AS Title, "
            		+ "CONCAT(address, ', ', city, ', ', country) AS Address, homephone AS HomePhone FROM  employee");

            // Print table header
            System.out.printf("%-11s%-25s%-15s%-50s%-15s%n", "EmployeeID", "Name", "Title", "Address", "HomePhone");

            // Print separator line
            System.out.println("-----------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                int employeeID = rs.getInt("EmployeeID");
                String name = rs.getString("Name");
                String title = rs.getString("Title");
                String address = rs.getString("Address");
                String homePhone = rs.getString("HomePhone");

                // Print each record in table format
                System.out.printf("%-11d%-25s%-15s%-50s%-15s%n", employeeID, name, title, address, homePhone);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void disconnect() {		
        try {		
            if (conn != null) {		
                conn.close();		
                System.out.println("Connection closed successfully.");		
            }		
        } catch (Exception e) {		
            e.printStackTrace();		
        }		
    }	
    
    public static void main(String[] args) {		
        TableRowPrinter printer = new TableRowPrinter();		
        printer.connect();		
        printer.displayRecords();		
        printer.disconnect();		
    }		
}		
		
