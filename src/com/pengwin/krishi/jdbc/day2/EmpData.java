package com.pengwin.krishi.jdbc.day2;
public class EmpData {
	private int employeeId;
	private String name;
	private String title;
	private String address;
	private String homePhone;
	public EmpData(int employeeId,String name,String title,String address,String homePhone)
	{	this.employeeId = employeeId;
		this.name = name;
		this.title = title;
		this.address = address;
		this.homePhone = homePhone;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}	
}
