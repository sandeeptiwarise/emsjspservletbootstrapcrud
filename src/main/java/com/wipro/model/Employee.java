package com.wipro.model;

// model or data or bean or entity
public class Employee {
	
	private String empemail;
	private String empaddress;
	private String empdesig;
	private double empsalary;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(String empemail, String empaddress, String empdesig, double empsalary) {
		super();
		this.empemail = empemail;
		this.empaddress = empaddress;
		this.empdesig = empdesig;
		this.empsalary = empsalary;
	}
	public String getEmpemail() {
		return empemail;
	}
	public void setEmpemail(String empemail) {
		this.empemail = empemail;
	}
	public String getEmpaddress() {
		return empaddress;
	}
	public void setEmpaddress(String empaddress) {
		this.empaddress = empaddress;
	}
	public String getEmpdesig() {
		return empdesig;
	}
	public void setEmpdesig(String empdesig) {
		this.empdesig = empdesig;
	}
	public double getEmpsalary() {
		return empsalary;
	}
	public void setEmpsalary(double empsalary) {
		this.empsalary = empsalary;
	}
	
	

}
