package com.instacart.challenge.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;


public class ShopperApplicationsDAO {
	
	public final static String CREATE_SCHEMA  = "CREATE SCHEMA IF NOT EXISTS CHALLENGE ";
	
	public final static String CREATE_TABLE  = "create table IF NOT EXISTS challenge.shoppers_applications(id int not null auto_increment,first_name varchar(60) not null ,  last_name varchar(60) not null ,  email varchar(200) not null ,   phone_number varchar(10) not null,  referral_code varchar(20) ,  PRIMARY KEY ( id ))";
	
	public final static String INSERT_SHOPPER = "INSERT INTO CHALLENGE.SHOPPERS_APPLICATIONS(FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, REFERRAL_CODE) VALUES(?, ?, ?, ?, ?)";
	
	public void addShopper(String fname, String lname, String  email, String cellNumber, String rcode) throws Exception{
			
		   Connection connection = null;
		   try{
			   connection = getConnection();
			   insertShopper(connection, fname, lname, email, cellNumber, rcode);
		   }catch(Exception e){
			   e.printStackTrace();
		   }finally {
			  closeConnection(connection);
		   }
		  
	}
	
	private Connection getConnection() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = (Connection)DriverManager.getConnection ("jdbc:mysql://localhost/" ,"root", "Pass1234");
		return connection;
	}
	
	private void closeConnection(Connection connection){
		
		if(connection != null){
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void closePreparedStatement(PreparedStatement preparedStatement){
		if(preparedStatement != null){
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void insertShopper(Connection conn, String fname, String lname, String  email, String cellNumber, String rcode) throws Exception{
		PreparedStatement preparedStatement = conn.prepareStatement(INSERT_SHOPPER);
		preparedStatement.setString(1, fname);
		preparedStatement.setString(2, lname);
		preparedStatement.setString(3, email);
		preparedStatement.setString(4, cellNumber);
		preparedStatement.setString(5, rcode);
		
		try{
			preparedStatement.execute(); 
		} catch(Exception e){
			e.printStackTrace();
		}finally {
			if(preparedStatement != null){
				preparedStatement.close();
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			new ShopperApplicationsDAO().addShopper("Carlos", "Palomino", "dsfsdfsd", "3453453454", "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
