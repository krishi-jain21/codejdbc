package com.pengwin.krishi.jdbc.day2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Scanner;

import com.pengwin.krishi.jdbc.day1.PropertyUtil;

public class DataDisplayer {
	
	
	public static void displayRowsPerPage(Connection connection, int rowsPerPage, String categoryName, int orderYear)
	{
		SalesByCategoryRunner sr = new SalesByCategoryRunner(connection,rowsPerPage);
		Scanner scan = new Scanner (System.in);
		int pageNo = 1;
		boolean exit = false;
		while(!exit)
		{
			System.out.println("ProductName  |  TotalPurchase ");
            System.out.println("------------------------------");
            List<SalesByCatData> salesData = sr.fetchRecords(categoryName, orderYear, pageNo);            
            for (SalesByCatData salesByCatData : salesData)
            {
            	System.out.println(salesByCatData.getProductName() +"\t" + salesByCatData.getTotalPurchase());
    	
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
            displayRowsPerPage(connection,7,"Seafood",1996);		
        } catch (Exception e) {		
            e.printStackTrace();		
        }		
	}

}
