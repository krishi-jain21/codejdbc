package com.pengwin.krishi.jdbc.day2;

import java.sql.*;
import java.util.*;

import com.pengwin.krishi.jdbc.day1.PropertyUtil;

public class DisplayUI {
	
	public static void displayRowsPerPage(Connection connection, int rowsPerPage)
	{
		RowsFetcher rf = new RowsFetcher(connection,rowsPerPage);
		Scanner scan = new Scanner (System.in);
		int pageNo = 1;
		boolean exit = false;
		while(!exit)
		{
			System.out.println("EmployeeID  |  Name                    |  Title        |  Address                                     |  HomePhone");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
            List <EmpData> empDataList = rf.getRowsPerPage(pageNo);
            for (EmpData empData : empDataList)
            {
            	System.out.println(empData.getEmployeeId() +"\t" + empData.getName() +"\t" + empData.getTitle() + "\t" + empData.getAddress() +"\t" + empData.getHomePhone());
            	
            }
            System.out.println("Choose Options: N {next page}, P {previous page}, X {exit}");
            String input = scan.nextLine();
            switch(input)
            {
            	case "N" : pageNo++;
            	break;
            	case "P" : pageNo=Math.max(1, pageNo-1);
            	break;
            	case "X" : exit=true;
            	break;
            	default: System.out.println("Invalid Input");
            	
            }
		}
		scan.close();
	}

	public static void main(String[] args) {
		String url = PropertyUtil.getProperty("url");		
        String username = PropertyUtil.getProperty("username");		
        String password = PropertyUtil.getProperty("password");	
        
        try {		
            Class.forName("com.mysql.cj.jdbc.Driver");		
            Connection connection = DriverManager.getConnection(url, username, password);		
            displayRowsPerPage(connection,7);		
        } catch (Exception e) {		
            e.printStackTrace();		
        }		
	}

}
