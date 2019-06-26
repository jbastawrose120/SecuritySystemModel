package edu.cuny.csi.csc430.fall15.securitysystem;

import java.sql.*;

public class LoadDriver {

	private String url = "jdbc:mysql://localhost:3306/secsys";
	private String username = "java";
	private String password = "password";

	public LoadDriver() {
		// TODO Auto-generated constructor stub
	}
	
	public void execute(){
		System.out.println("Connecting database...");

		try (Connection conn = DriverManager.getConnection(url, username, password)) {
		    System.out.println("Database connected!");
		    //Create Statement
		    Statement statement = conn.createStatement();
		    
		    //Execute Query
		    String sendName = "bjones";
		    ResultSet rs = statement.executeQuery("select password from userdata where username = '" + sendName + "'");
		    
		    while(rs.next()){
		    	System.out.println(rs.getString("password"));
		    }
		    
		    
		    statement.close();
		    conn.close();
		    
		    
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

	public static void main(String[] args) {

		LoadDriver ld = new LoadDriver();
		ld.execute();
	
	}

}
