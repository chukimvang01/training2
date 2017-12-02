package com.ckv.controller;

import java.math.BigDecimal;
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
	public void resetQueryCache(){
		String sql="reset query cache";
		
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.executeQuery();
			System.out.println("Query OK, 0 rows affected (0.00 sec)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public long pushDatabaseFromFileToTableNoIndex(String path){
		String sql="load data local infile'"+path+"' into table userinfo";
		long timeStamp=0,timeCurrent=0;
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			timeStamp=System.currentTimeMillis();
			ps.executeQuery();
			timeCurrent=System.currentTimeMillis();
			System.out.println("Load data to table no index sucessfull!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return timeCurrent-timeStamp;
	}
	
	public long pushDatabaseFromFileToTableHasIndex(String path){
		String sql="load data local infile'"+path+"' into table userinfoi";
		long timeStamp=0,timeCurrent=0;
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			timeStamp=System.currentTimeMillis();
			ps.executeQuery();
			timeCurrent=System.currentTimeMillis();
			System.out.println("Load data to table has index sucessfull!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return timeCurrent-timeStamp;
	}
	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306/test";
		ConnectionDB con=new ConnectionDB(url, "root", "12345678");
		long timeHasIndex=con.getTimeHandlingQueriesHasIndex();
		long timeNoIndex=con.getTimeHandlingQueriesNoIndex();
		con.resetQueryCache();
		System.out.println();
		System.out.println("Time to query with table has index is: "+new BigDecimal(timeHasIndex/1000f)+" sec");
		System.out.println("Time to query with table no index is: "+new BigDecimal(timeNoIndex/1000f)+ " sec");
		
	}
	
}
