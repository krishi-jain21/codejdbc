package com.pengwin.krishi.jdbc.day1;

public class Main {

    public static void main(String[] args) {
        // Create an instance of RecordDisplay with database credentials
        RecordDisplay r1 = new RecordDisplay("northwind", "root", "root");
        
        // Connect to the database
        r1.connect();
        
        // Display all records
        r1.displayRecords(null);
        
        // Display records for employee with ID 1
        r1.displayRecords(1);
        
        // Disconnect from the database
        r1.disconnect();
    }

}