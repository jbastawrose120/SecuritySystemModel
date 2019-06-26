package edu.cuny.csi.csc430.fall15.securitysystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;


public class LoginFrame extends JFrame implements Runnable {

	private static final long serialVersionUID = 7599535749166048225L;
	private static final String title = "Login";
	private SecuritySystem secsys;
	private String url = "jdbc:mysql://localhost:3306/secsys";
	private String username = "java";
	private String password = "password";

	public LoginFrame(SecuritySystem secsys) {
		// TODO Auto-generated constructor stub
		super(title);
		this.secsys = secsys;
	}
	
	public void run(){
		createGUI();
	}

	private void createGUI(){
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(3,2));
		this.setVisible(true);
		this.setSize(300,100);
		this.setLocation(500,250);
		
		JLabel uName = new JLabel("Username");
		uName.setVisible(true);
		this.add(uName);
		
		JTextField textField = new JTextField(20);
		textField.setVisible(true);
		textField.setEditable(true);
		this.add(textField);
		
		JLabel pWord = new JLabel("Password");
		pWord.setVisible(true);
		this.add(pWord);
		
		JPasswordField pWordField = new JPasswordField(20);
		pWordField.setEditable(true);
		pWordField.setVisible(true);
		this.add(pWordField);
		
		JButton register = new JButton();
		register.setVisible(true);
		register.setText("Create Account");
		register.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				
				
			}
		});
		
		JLabel error = new JLabel("Error, try Again");
		error.setVisible(false);
		this.add(error);
		
		JButton enter = new JButton();
		enter.setVisible(true);
		enter.setText("Enter");
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// TODO tie into database here
				boolean loginFlag = false;

				try (Connection conn = DriverManager.getConnection(url,
						username, password)) {
					System.out.println("Database connected!");
					// Create Statement
					Statement statement = conn.createStatement();

					String pWord = new String(pWordField.getPassword());
					String uName = textField.getText();

						// Execute Query
						ResultSet rs = statement
								.executeQuery("select password from userdata"
										+ " where username = '" + uName + "' ");

						while (rs.next()) {
							if (pWord.equals( rs.getString("password"))) {
								System.out.println("Password is correct");
								dispose();

								loginFlag = true;

								SecSystemMainMenu ssmm = new SecSystemMainMenu(secsys);
								SwingUtilities.invokeLater(ssmm);
								
								break;

							}
							
							else{
								error.setVisible(true);
								
							}

					}
					statement.close();
					conn.close();
				} catch (SQLException e) {
					throw new IllegalStateException(
							"Cannot connect the database!", e);
				}

			}

		});
		this.add(enter);
	}
	
	public static void main(String[] args){
		LoginFrame lf = new LoginFrame(new SecuritySystem());
		SwingUtilities.invokeLater(lf);
	}
}
