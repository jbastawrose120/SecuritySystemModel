package edu.cuny.csi.csc430.fall15.securitysystem;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class RegisterSensorFrame extends JFrame implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9061876405428850998L;
	
	private static String title = "Register New Sensor";
	private SecuritySystem secsys;
	
	public RegisterSensorFrame(SecuritySystem secsys){
		super(title);
		this.secsys = secsys;
	}
	
	public void run(){
		createGUI();
	}
	
	private void createGUI(){
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.setVisible(true);
		this.setSize(500,100);
		this.setLocation(500,250);
		
		JLabel nameLabel = new JLabel("Sensor Name ");
		nameLabel.setVisible(true);
		this.add(nameLabel);
		
		JTextField nameField = new JTextField(10);
		nameField.setVisible(true);
		//nameField.setSize(100,100);
		nameField.setEditable(true);
		this.add(nameField);
		
		JLabel idLabel = new JLabel("Enter ID*");
		idLabel.setVisible(true);
		this.add(idLabel);
		
		JTextField idField = new JTextField(10);
		idField.setVisible(true);
		idField.setEditable(true);
		this.add(idField);
		
		JButton enter = new JButton("Enter");
		enter.setVisible(true);
		this.add(enter);
		
		JLabel error = new JLabel("Error, try again");
		error.setVisible(false);
		this.add(error);
		
		enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				boolean flag = true; //If false, error occurred
				
				String name;
				
				if(nameField.getText().equals("")){
					name = "Unnamed Sensor";
				}
				else{
					name = nameField.getText();
				}
				
				try{
					Integer id = Integer.parseInt(idField.getText());
					secsys.addSensor(name, id);
				}
				catch(HomeSecException hse){
					System.err.println(hse);
					error.setVisible(true);
					flag = false;
				}
				catch(NumberFormatException nfe){
					error.setVisible(true);
					flag = false;
				}
				
				if(flag){
					dispose();
				}
				else{
					error.setVisible(true);
				}
				return;

				/**
				 * TODO Write exceptions to log file for troubleshooting
				 */
			}
		});
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RegisterSensorFrame rsf = new RegisterSensorFrame(new SecuritySystem());
		
		SwingUtilities.invokeLater(rsf);

	}

}
