package edu.cuny.csi.csc430.fall15.securitysystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AuthenticationFrame extends JFrame implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8089112900135563979L;
	private static String title = "Authentication";
	private SecuritySystem secsys;
	private boolean flag; //0 for arm, 1 for disarm


	public AuthenticationFrame(SecuritySystem secsys, boolean flag){
		/**
		 * Pass in false for arm, true for disarm
		 */
		super(title);
		this.secsys = secsys;
		this.flag = flag;
	}
	
	public void run(){
		createGUI();
	}
	
	private void createGUI(){
		//JFrame frame = new AuthenticationFrame("Enter your ArmCode");
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.setVisible(true);
		this.setSize(400,100);
		this.setLocation(500,250);
		
		JLabel label = new JLabel("Enter Your Code");
		label.setVisible(true);
		this.add(label);
		
		JPasswordField passwordField = new JPasswordField(10);
		passwordField.setEditable(true);
		passwordField.setVisible(true);
		this.add(passwordField);
		
		JButton enter = new JButton("Enter");
		enter.setVisible(true);
		
		JLabel error = new JLabel("Error, try again");
		error.setVisible(false);
		this.add(error);
		
		enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				/**
				 * TODO Parse in password, check it against armCode.
				 */
				boolean errorFlag = false;
				char[] input = passwordField.getPassword();
				Integer code = Integer.parseInt(new String(input));

				if(!flag){
					try{
						secsys.arm(code);
					}
					catch(HomeSecException hse){
						error.setVisible(true);
						errorFlag = true;
					}
					
					if(errorFlag){
						error.setVisible(true);
					}
					else{
						//POPUP WITH "SYSTEM ARMED"
						dispose();
						ConfirmationFrame cf = new ConfirmationFrame("System Armed");
						SwingUtilities.invokeLater(cf);
					}
				}
				else{
					try{
						secsys.disarm(code);
					}
					catch(HomeSecException hse){
						error.setVisible(true);
						errorFlag = true;
					}
					
					if(errorFlag)
						error.setVisible(true);
					
					else{
						//POPUP WITH "SYSTEM DISARMED"
						dispose();
						ConfirmationFrame cf = new ConfirmationFrame("System Disarmed");
						SwingUtilities.invokeLater(cf);
					}
				}
				
			}
		});
		
		this.add(enter);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AuthenticationFrame af = new AuthenticationFrame(new SecuritySystem(),false);
		
		SwingUtilities.invokeLater(af);
		
	}

}
