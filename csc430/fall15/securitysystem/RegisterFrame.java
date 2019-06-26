package edu.cuny.csi.csc430.fall15.securitysystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;


public class RegisterFrame extends JFrame implements Runnable{
	
	private static final String  title = "Create Account";
	private String url = "jdbc:mysql://localhost:3306/secsys";
	private String username = "java";
	private String password = "password";

	public RegisterFrame() {
		// TODO Auto-generated constructor stub
		super(title);
	}
	
	public void run(){
		createGUI();
	}
	
	private void createGUI(){
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(5,2));
		this.setVisible(true);
		this.setSize(600,200);
		this.setLocation(500,250);
		
		JLabel labeluname = new JLabel("Enter Username");
		labeluname.setVisible(true);
		this.add(labeluname);
		
		JTextField uNameField = new JTextField(20);
		uNameField.setVisible(true);
		uNameField.setEditable(true);
		this.add(uNameField);
		
		JLabel labelpword = new JLabel("Enter Password");
		labelpword.setVisible(true);
		this.add(labelpword);
		
		JPasswordField pwordfield = new JPasswordField(20);
		pwordfield.setVisible(true);
		pwordfield.setEditable(true);
		this.add(pwordfield);
		
		JLabel labelname = new JLabel("Enter First and Last Name");
		labelname.setVisible(true);
		this.add(labelname);
		
		JTextField namefield = new JTextField(20);
		namefield.setVisible(true);
		namefield.setEditable(true);
		this.add(namefield);
		
		JLabel labeladdr = new JLabel("Enter Address");
		labeladdr.setVisible(true);
		this.add(labeladdr);
		
		JTextField addrfield = new JTextField(20);
		addrfield.setVisible(true);
		addrfield.setEditable(true);
		this.add(addrfield);
		
		JButton enter = new JButton();
		enter.setVisible(true);
		enter.setText("Enter");
		this.add(enter);
		enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				
				int sendID = generateInt(100,999);
				
				try (Connection conn = DriverManager.getConnection(url, username, password)) {
				    System.out.println("Database connected!");
				    //Create Statement
				    Statement statement = conn.createStatement();
				    
				    //Execute Query
				    ResultSet rs = statement.executeQuery("insert into userdata values(" + sendID + ","
				    		+ namefield.getText() + "," + addrfield.getText() + "," + uNameField.getText() + "," 
				    		+ new String(pwordfield.getPassword()) + ",");
				    
				    //while(rs.next()){
				    //}
				    
				    
				    statement.close();
				    conn.close();
				    
				    
				} catch (SQLException e) {
				    throw new IllegalStateException("Cannot connect the database!", e);
				}
				
				
			}
		});
		
	}

	
	public int generateInt(int low, int high) {
        return ( (int) ( (Math.random() * 1000000000 )  % (high - low) )  + low );
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RegisterFrame rf = new RegisterFrame();
		
		SwingUtilities.invokeLater(rf);
		
	}

}
