package com.pengwin.krishi.jdbc.day2;

import java.sql.*;
import java.util.*;

public class RowsFetcher 
{
	private Connection connection;
	private int rowsPerPage = 5;
	public RowsFetcher(Connection connection,int rowsPerPage) 
	{
		this.connection = connection;
		this.rowsPerPage = rowsPerPage;
	}
	public List<EmpData> getRowsPerPage(int pageNo)
	{
		List <EmpData> empDataList = new ArrayList<>();
		try {
			String query = "SELECT  employeeid AS EmployeeID, CONCAT(first_name, ' ', last_name) AS Name, title AS Title, CONCAT(address, ', ', city, ', ', country) AS Address, homephone"
					+ " AS HomePhone FROM  employee";
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet=statement.executeQuery(query);
			int rowNumber = rowsPerPage *( pageNo-1);
			resultSet.absolute(rowNumber);
			for(int i=0; i<rowsPerPage && resultSet.next(); i++)
			{
				int employeeId = resultSet.getInt("EmployeeID");
                String name = resultSet.getString("Name");
                String title = resultSet.getString("Title");
                String address = resultSet.getString("Address");
                String homePhone = resultSet.getString("HomePhone");
                
                EmpData employee = new EmpData(employeeId,name,title,address,homePhone);
                empDataList.add(employee);
			}
			return empDataList;
		}
		catch (SQLException e) {
	           
			e.printStackTrace();
			return empDataList;
	    }
	}
	
}
