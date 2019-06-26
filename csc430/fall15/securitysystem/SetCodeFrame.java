package edu.cuny.csi.csc430.fall15.securitysystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SetCodeFrame extends JFrame implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7383886453064076803L;
	private SecuritySystem secsys;
	private static String title = "Set Arm Code";
	
	public SetCodeFrame(SecuritySystem secsys){
		super(title);
		this.secsys = secsys;
	}
	
	public void run(){
		//Thread t1 = new Thread(secsys);
		//t1.start();
		createGUI();
	}
	
	private void createGUI(){
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.setVisible(true);
		this.setSize(400,100);
		this.setLocation(500,250);
		
		JLabel label = new JLabel("Set Your Code");
		label.setVisible(true);
		this.add(label);
		
		JTextField textField = new JTextField(10);
		textField.setEditable(true);
		textField.setVisible(true);
		this.add(textField);
		
		JButton enter = new JButton("Enter");
		enter.setVisible(true);
		this.add(enter);
		
		JLabel error = new JLabel("Error, try again");
		error.setVisible(false);
		this.add(error);
		
		enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				boolean errorFlag = false;
				
				try{
					int code = Integer.parseInt(textField.getText());
					secsys.setArmCode(code);
				}
				catch(NumberFormatException nfe){
					errorFlag = true;
					error.setVisible(true);
				}
				catch(HomeSecException hse){
					errorFlag = true;
					error.setVisible(true);
				}
				
				if(errorFlag)
					error.setVisible(true);
				else{
					dispose();
					SwingUtilities.invokeLater(new SecSystemMainMenu(secsys));
				}
			}
		});
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		SetCodeFrame scf = new SetCodeFrame();
		
		SwingUtilities.invokeLater(scf);
		*/
		

	}

}
