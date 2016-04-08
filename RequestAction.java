package com.jpalenci.action;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;

public class RequestAction extends ActionSupport{
	private static final long serialVersionUID = 1L;

	//DataBase Connection
	protected static final String myDriver = "com.mysql.jdbc.Driver";
	protected static final String myURL = "jdbc:mysql://127.0.0.1:3306/innovation_lab";
	protected static final String dbUser = "root";
	protected static final String dbPass = "";
	
	//Data Fields That Come From User Inputed Data
	private static String in_FullName;
	private static String in_PhoneNumber;
	private static String in_EmailAddress;
	private static String in_CheckOutDate;
	private static String in_DeviceUsage;
	private static String in_DeviceSelection;
	
	//Data Fields That Will Be Inserted To Their Respective Place
	private static String out_FullName;
	private static String out_PhoneNumber;
	private static String out_EmailAddress;
	private static String out_CheckOutDate;
	private static String out_DeviceUseage;
	private static String out_DeviceSelection;
	
	static Connection dbConn = getDBConn();
	static CallableStatement callStmt = null;
	
	@Override
	public String execute() throws SQLException{
		String ret ="";
		
		try{
			Class.forName(myDriver);
			
			callInsertUserData();
			getUserData();
			
			System.out.println("Success");
			ret = SUCCESS;
			
		} catch(Exception e){
			e.printStackTrace();
			ret = ERROR;
		} finally {
			if(dbConn != null){
				dbConn.close();
			}
		}
		
		return ret;
	}
	
	public static void getUserData() throws SQLException{
		ResultSet rs = null;
		
		try{
			dbConn = getDBConn();
			
			String selectQuery = "SELECT FullName, PhoneNumber, EmailAddress, CheckOutDate, DeviceUsage, DeviceSelection " 
			+ "FROM devices_requested";
			
			rs = callStmt.executeQuery(selectQuery);
			rs.last();
			out_FullName = rs.getString("FullName");
			out_PhoneNumber = rs.getString("PhoneNumber");
			out_EmailAddress = rs.getString("EmailAddress");
			out_CheckOutDate = rs.getString("CheckOutDate");
			out_DeviceUseage = rs.getString("DeviceUsage");
			out_DeviceSelection = rs.getString("DeviceSelection");
			
			callStmt.executeUpdate();
			rs.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	/*
	 * Method Which Calls To MySQL Stored Procedure That Inserts Information
	 * That Comes From The Register Form,Which Then Is Updated Into The
	 * DataBase.
	 */
	public static void callInsertUserData() throws SQLException{
		String insertUserData = "{CALL insertUserData(?,?,?,?,?,?)}";
		
		try{
			Connection dbConn = getDBConn();
			callStmt = dbConn.prepareCall(insertUserData);
			
			callStmt.setString(1, in_FullName);
			callStmt.setString(2, in_PhoneNumber);
			callStmt.setString(3, in_EmailAddress);
			callStmt.setString(4, in_CheckOutDate);
			callStmt.setString(5, in_DeviceUsage);
			callStmt.setString(6, in_DeviceSelection);
			
			callStmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if (callStmt != null) {
				callStmt.close();
			}
		}
	}
	
	/*
	 * MySQL DataBase Connection, Attempts To Connect To Specified DB URL With
	 * UserName & Password.
	 */
	private static Connection getDBConn() {
		Connection dbConn = null;

		try {
			Class.forName(myDriver);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			dbConn = DriverManager.getConnection(myURL, dbUser, dbPass);
			return dbConn;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConn;
	}

	//IN Getters
	public static String getIn_FullName() {
		return in_FullName;
	}
	public static String getIn_PhoneNumber() {
		return in_PhoneNumber;
	}
	public static String getIn_EmailAddress() {
		return in_EmailAddress;
	}
	public static String getIn_CheckOutDate() {
		return in_CheckOutDate;
	}
	public static String getIn_DeviceUsage() {
		return in_DeviceUsage;
	}
	public static String getIn_DeviceSelection() {
		return in_DeviceSelection;
	}

	//OUT Getters
	public static String getOut_FullName() {
		return out_FullName;
	}
	public static String getOut_PhoneNumber() {
		return out_PhoneNumber;
	}
	public static String getOut_EmailAddress() {
		return out_EmailAddress;
	}
	public static String getOut_CheckOutDate() {
		return out_CheckOutDate;
	}
	public static String getOut_DeviceUseage() {
		return out_DeviceUseage;
	}
	public static String getOut_DeviceSelection() {
		return out_DeviceSelection;
	}

	//IN Setters
	public static void setIn_FullName(String in_FullName) {
		RequestAction.in_FullName = in_FullName;
	}
	public static void setIn_PhoneNumber(String in_PhoneNumber) {
		RequestAction.in_PhoneNumber = in_PhoneNumber;
	}
	public static void setIn_EmailAddress(String in_EmailAddress) {
		RequestAction.in_EmailAddress = in_EmailAddress;
	}
	public static void setIn_CheckOutDate(String in_CheckOutDate) {
		RequestAction.in_CheckOutDate = in_CheckOutDate;
	}
	public static void setIn_DeviceUsage(String in_DeviceUsage) {
		RequestAction.in_DeviceUsage = in_DeviceUsage;
	}
	public static void setIn_DeviceSelection(String in_DeviceSelection) {
		RequestAction.in_DeviceSelection = in_DeviceSelection;
	}

	//OUT Setters
	public static void setOut_FullName(String out_FullName) {
		RequestAction.out_FullName = out_FullName;
	}
	public static void setOut_PhoneNumber(String out_PhoneNumber) {
		RequestAction.out_PhoneNumber = out_PhoneNumber;
	}
	public static void setOut_EmailAddress(String out_EmailAddress) {
		RequestAction.out_EmailAddress = out_EmailAddress;
	}
	public static void setOut_CheckOutDate(String out_CheckOutDate) {
		RequestAction.out_CheckOutDate = out_CheckOutDate;
	}
	public static void setOut_DeviceUseage(String out_DeviceUseage) {
		RequestAction.out_DeviceUseage = out_DeviceUseage;
	}
	public static void setOut_DeviceSelection(String out_DeviceSelection) {
		RequestAction.out_DeviceSelection = out_DeviceSelection;
	}
	
	
}
