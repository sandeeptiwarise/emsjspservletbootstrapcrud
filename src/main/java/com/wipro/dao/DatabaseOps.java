package com.wipro.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wipro.model.Employee;

// Data Access Object Layer - Database Operation On Objects Of A Class i.e., model or bean or entity or data
public class DatabaseOps {

	private static Connection con;
	private static Statement stmt;
	private static PreparedStatement pstmt;
	private static ResultSet rs;

	private static String drivername = "oracle.jdbc.driver.OracleDriver";
	private static String dbloc = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String dbuser = "hr";
	private static String dupwd = "hr";

	private static final String insertQuery = "INSERT INTO tblemployee(empemail, empaddr, empsalary, empdesig) VALUES (?,?,?,?)";
	private static final String selectAllQuery = "SELECT empemail,empaddr,empsalary,empdesig FROM tblemployee";
	private static final String selectOneQuery = "SELECT empemail,empaddr,empsalary,empdesig FROM tblemployee WHERE empemail = ?";
	private static final String deleteQuery = "DELETE FROM tblemployee WHERE empemail = ?";
	private static final String updateQuery = "UPDATE tblemployee SET empaddr = ?, empsalary = ?, empdesig = ? WHERE empemail = ?";

	public static void getConnection() {
		try {
			Class.forName(drivername);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(dbloc, dbuser, dupwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	// Logic for insert a new record of an object
	public boolean rcrInsert(Employee empobj) {
		String eemail = empobj.getEmpemail();
		String eadr = empobj.getEmpaddress();
		double esal = empobj.getEmpsalary();
		String edesig = empobj.getEmpdesig();
		boolean flag = false;
		try {
			getConnection();
			pstmt = con.prepareStatement(insertQuery);
			pstmt.setString(1, eemail);
			pstmt.setString(2, eadr);
			pstmt.setDouble(3, esal);
			pstmt.setString(4, edesig);
			int status = pstmt.executeUpdate();
			if (status >= 1) {
				flag = true;
			} else {
				flag = false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

	// Logic for update an existing record of an object
	public boolean rcrUpdate(Employee empobj) {
		boolean flag = false;

		String eemail = empobj.getEmpemail();
		String eadr = empobj.getEmpaddress();
		double esal = empobj.getEmpsalary();
		String edesig = empobj.getEmpdesig();


		try {
			getConnection();
			pstmt = con.prepareStatement(updateQuery);
			pstmt.setString(1, eadr);
			pstmt.setDouble(2, esal);
			pstmt.setString(3, edesig);
			pstmt.setString(4, eemail);
			
			int status = pstmt.executeUpdate();
			if(status >=1 ) {
				flag = true;
			}else {
				flag = false;
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	// Logic for delete of a record of an object
	public boolean rcrDelete(String emailid) {		
		boolean flag = false;				
		try {
			getConnection();
			pstmt = con.prepareStatement(deleteQuery);
			pstmt.setString(1, emailid);			
			int status = pstmt.executeUpdate();			
			if(status >= 1) {
				flag = true;
			}else {
				flag = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		return flag;		
	}	
	// Logic for select all record of objects
	public List<Employee> rcrAllEmployee(){		
		List<Employee> allemp = new ArrayList<Employee>();		
		try {
			getConnection();
			pstmt = con.prepareStatement(selectAllQuery);			
			rs = pstmt.executeQuery();			
			while(rs.next()) {
				String emem = rs.getString("empemail");
				String emad = rs.getString("empaddr");
				double emsal = rs.getDouble("empsalary");
				String emdes = rs.getString("empdesig");				
				
				Employee emp = new Employee(emem,emad,emdes,emsal);
				allemp.add(emp);				
			}		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		return allemp;
	}
	
	// Logic for selecting one record for an object based on his/her emailid
	// (primary key)
	public Employee rcrGetEmployee(String empemail) {
		
		Employee emp = null;
						
		try {
			getConnection();
			pstmt = con.prepareStatement(selectOneQuery);
			pstmt.setString(1, empemail);
			
			rs = pstmt.executeQuery();
			
			
			
			if(rs.next()) {
				
				String emem = rs.getString("empemail");
				String emad = rs.getString("empaddr");
				double emsal = rs.getDouble("empsalary");
				String emdes = rs.getString("empdesig");				
				
				emp = new Employee(emem,emad,emdes,emsal);				
			}else {
				return null;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return emp;
	}
	
	
	
	
	
	
	
	
	

}
// Front End : Html CSS JS Bootstrap
// Back End : Servlet and JSP API
// Database : Oracle 11g
/*
CREATE TABLE tblemployee(empemail varchar(50) PRIMARY KEY,
empaddr varchar(100), empsalary NUMBER,
empdesig varchar(50));

SELECT * FROM TBLEMPLOYEE ;

*/