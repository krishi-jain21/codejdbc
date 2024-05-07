package com.pengwin.krishi.jdbc.day2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesByCategoryRunner {

    private int rowsPerPage = 5;
    private Connection connection;

    public SalesByCategoryRunner(Connection connection, int rowsPerPage) {
        this.connection = connection;
        this.rowsPerPage = rowsPerPage;
    }

    public List<SalesByCatData> fetchRecords(String categoryName, int orderYear, int pageNo) {
        List<SalesByCatData> salesList = new ArrayList<>();

        try {
            // Prepare and execute the stored procedure
            CallableStatement callableStatement = connection.prepareCall("{CALL GetSalesByCategoryAndYear(?, ?)}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Set parameters
            callableStatement.setString(1, categoryName);
            callableStatement.setInt(2, orderYear);

            // Execute the query
            ResultSet resultSet = callableStatement.executeQuery();

            // Move cursor to the first row of the requested page
            int firstRow = (pageNo - 1) * rowsPerPage +1;
            boolean isLastPage = resultSet.absolute(firstRow);

            // Iterate through the result set and populate the list with rows from the current page
            int rowCount = 0;
            while (!isLastPage && rowCount < rowsPerPage) {
                String productName = resultSet.getString("productname");
                double totalPurchase = resultSet.getDouble("totalpurchase");

                // Create a new SalesByCatData object and add it to the list
                SalesByCatData salesData = new SalesByCatData(productName, totalPurchase);
                salesList.add(salesData);

                // Move to the next row
                isLastPage = !resultSet.next();
                rowCount++;
            }

            // Close ResultSet and CallableStatement
            resultSet.close();
            callableStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salesList;
    }
}
