package com.ckv.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionDB {

	private Connection connection = null;

	public ConnectionDB(String url, String user, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Connected!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public long getTimeHandlingQueriesNoIndex() {
		String sql = "select*from userinfo where country = 'Hazleh' and sex IN('Male','Female') and dob > '1994-11-07'";
		long currentTime = 0, timeNew = 0;

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			currentTime = System.currentTimeMillis();
			ps.executeQuery();
			timeNew = System.currentTimeMillis();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timeNew - currentTime;
	}
	public long getTimeHandlingQueriesHasIndex() {
		String sql = "select*from userinfoi where country = 'Hazleh' and sex IN('Male','Female') and dob > '1994-11-07'";
		long currentTime = 0, timeNew = 0;

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			currentTime = System.currentTimeMillis();
			ps.executeQuery();
			timeNew = System.currentTimeMillis();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timeNew - currentTime;
	}
	public long getTimeHandlingQueriesHasIndexMultiColumn() {
		String sql = "select*from userinfom where country = 'Hazleh' and sex IN('Male','Female') and dob > '1994-11-07'";
		long currentTime = 0, timeNew = 0;

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			currentTime = System.currentTimeMillis();
			ps.executeQuery();
			timeNew = System.currentTimeMillis();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timeNew - currentTime;
	}
	public long getTimeHandlingQueriesHasIndexCovering1(String nameTable) {
		String sql = "SELECT country, sex, dob FROM "+nameTable;
		long currentTime = 0, timeNew = 0;

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			currentTime = System.currentTimeMillis();
			ps.executeQuery();
			timeNew = System.currentTimeMillis();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timeNew - currentTime;
	}
	public long getTimeHandlingQueriesHasIndexCovering2(String nameTable) {
		String sql = "SELECT * FROM "+nameTable+" JOIN (SELECT id FROM "+nameTable+" WHERE country = 'Enigma') AS t ON (t.id = "+nameTable+".id)";
		long currentTime = 0, timeNew = 0;

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			currentTime = System.currentTimeMillis();
			ps.executeQuery();
			timeNew = System.currentTimeMillis();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timeNew - currentTime;
	}	
	public long getTimeHandlingQueriesHasIndexForSorts(String nameTable) {
		String sql = "SELECT * FROM "+nameTable+" WHERE country = 'Moultrie' AND sex='Male' ORDER BY dob";
		long currentTime = 0, timeNew = 0;

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			currentTime = System.currentTimeMillis();
			ps.executeQuery();
			timeNew = System.currentTimeMillis();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timeNew - currentTime;
	}
	public long getTimeHandlingQueriesHasIndexRedundant1(String nameTable) {
		String sql = "SELECT*FROM "+nameTable+" WHERE country = 'Patterson'";
		long currentTime = 0, timeNew = 0;

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			currentTime = System.currentTimeMillis();
			ps.executeQuery();
			timeNew = System.currentTimeMillis();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timeNew - currentTime;
	}	
	public long getTimeHandlingQueriesHasIndexRedundant2(String nameTable) {
		String sql = "SELECT country, sex, dob FROM "+nameTable+" WHERE country ='Patterson' ";
		long currentTime = 0, timeNew = 0;

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			currentTime = System.currentTimeMillis();
			ps.executeQuery();
			timeNew = System.currentTimeMillis();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timeNew - currentTime;
	}	
	public void resetQueryCache(){
		String sql="reset query cache";
		
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.executeQuery();
			System.out.println("Query OK, 0 rows affected (0.00 sec)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	public long pushDatabaseFromFileToTable(String nameTable, String path){
		String sql="load data local infile'"+path+"' into table "+nameTable;
		long timeStamp=0,timeCurrent=0;
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			timeStamp=System.currentTimeMillis();
			ps.executeQuery();
			timeCurrent=System.currentTimeMillis();
			System.out.println("Load data to table sucessfull!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return timeCurrent-timeStamp;
	}	
}
