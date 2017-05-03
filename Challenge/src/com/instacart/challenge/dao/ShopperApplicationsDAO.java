package com.instacart.challenge.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.instacart.challenge.model.ApplicationStatistics;
import com.mysql.jdbc.Connection;


public class ShopperApplicationsDAO {
	
	public final static String CREATE_SCHEMA  = "CREATE SCHEMA IF NOT EXISTS CHALLENGE ";
	
	public final static String CREATE_TABLE  = "create table IF NOT EXISTS challenge.shoppers_applications(id int not null auto_increment,first_name varchar(60) not null ,  last_name varchar(60) not null ,  email varchar(200) not null ,   phone_number varchar(10) not null,  referral_code varchar(20) ,  PRIMARY KEY ( id ))";
	
	public final static String INSERT_SHOPPER = "INSERT INTO CHALLENGE.SHOPPERS_APPLICATIONS(FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, REFERRAL_CODE) VALUES(?, ?, ?, ?, ?)";
	
	public final static String GET_STATISTICS = "select count(id) as applied, year(application_date) as app_year, weekofyear(application_date) as app_week, count(if(quiz_status > 0, 1, NULL)) as quiz_started, count(if(quiz_status = 2, 1, NULL)) as quiz_completed, count(if(onboard_status > 0, 1, NULL)) as onboarding_requested, count(if(onboard_status = 2, 1, NULL)) as onboarding_completed, count(if(hiring_status = 1, 1, NULL)) as hired, count(if(hiring_status = 2, 1, NULL)) as rejected from challenge.application_status where application_date between ? and ? group by year(application_date), weekofyear(application_date)";
	
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
	
	public List<ApplicationStatistics> getApplicationStatistics(Date startDate, Date endDate){
		ArrayList<ApplicationStatistics> applicationStatistics = new ArrayList<ApplicationStatistics>();
		
		GregorianCalendar calendar = new GregorianCalendar();
		
		calendar.setTime(startDate);
		
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		
		startDate = calendar.getTime();
		
		calendar.setTime(endDate);
		
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MINUTE, 59);
		
		endDate = calendar.getTime();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			   connection = getConnection();
			   preparedStatement = connection.prepareStatement(GET_STATISTICS);
			   preparedStatement.setDate(1, new java.sql.Date(startDate.getTime()));
			   preparedStatement.setDate(2, new java.sql.Date(endDate.getTime()));
			   resultSet = preparedStatement.executeQuery();
			   
			   while(resultSet.next()){
				   ApplicationStatistics weekAppStat = new ApplicationStatistics(
						   								resultSet.getInt("app_year"), 
						   								resultSet.getInt("app_week"),
						   								resultSet.getInt("hired"), 
						   								resultSet.getInt("rejected"), 
						   								resultSet.getInt("onboarding_completed"), 
						   								resultSet.getInt("onboarding_requested"), 
						   								resultSet.getInt("quiz_started"), 
						   								resultSet.getInt("quiz_completed"), 
						   								resultSet.getInt("applied"));
				   
				   applicationStatistics.add(weekAppStat);
			   }
			   
		   }catch(Exception e){
			   e.printStackTrace();
		   }finally {
			   closeResultSet(resultSet);
			  closePreparedStatement(preparedStatement);
			  closeConnection(connection);
		   }
		
		return applicationStatistics;
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
	
	private void closeResultSet(ResultSet resultSet){
		if(resultSet != null){
			try {
				resultSet.close();
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
