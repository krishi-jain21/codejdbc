package com.pengwin.krishi.jdbc.day2;

public class SalesByCatData {
	private String ProductName;
	private Double TotalPurchase;
	
	public SalesByCatData(String ProductName, Double TotalPurchase)
	{
		this.ProductName = ProductName;
		this.TotalPurchase = TotalPurchase;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public Double getTotalPurchase() {
		return TotalPurchase;
	}

	public void setTotalPurchase(Double totalPurchase) {
		TotalPurchase = totalPurchase;
	}
	
	
}
