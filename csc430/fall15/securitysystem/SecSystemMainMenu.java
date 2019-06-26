package edu.cuny.csi.csc430.fall15.securitysystem;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;


public class SecSystemMainMenu extends JFrame implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2488530463339799411L;
	
	private static String title = "Home Security System";
	
	private SecuritySystem secsys;

	public SecSystemMainMenu(SecuritySystem secsys) {
		// TODO Auto-generated constructor stub
		super(title);
		this.secsys = secsys;
	}
	
	public void run(){
		if(this.secsys.getArmCodeBool()){
			//Start Burglar and Main Menu
			//Thread t1 = new Thread(new Burglar(secsys));
			//t1.start();
			Burglar burglar = new Burglar(secsys);
			SwingUtilities.invokeLater(burglar);
			
			createGUI();
		}
		else{
			SetCodeFrame scf = new SetCodeFrame(secsys);
			SwingUtilities.invokeLater(scf);
		}
	}
	
	public void processWindowEvent(WindowEvent we){
		/**
		 * This method allows another method to be called
		 * just before the window is closed. In this case,
		 * the stream to the log file needs to be closed 
		 * on application exit. 
		 */
		if(we.getID() == WindowEvent.WINDOW_CLOSING){
			secsys.addToLog("Application Closed");
			secsys.closeLogFile();
			this.dispose();
			System.exit(0);
		}
	}
	
	private void createGUI(){		
		this.setLayout(new GridLayout(2,2));
		
		this.setSize(500,150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(500,250);
		
		JButton viewSensors = new JButton();
		viewSensors.setText("View Deployed Sensors");
		viewSensors.setVisible(true);
		viewSensorsButtonMethod(viewSensors);
		
		JButton newSensor = new JButton();
		newSensor.setText("Register a New Sensor");
		newSensor.setVisible(true);
		newSensorButtonMethod(newSensor);
		
		JButton armButton = new JButton();
		armButton.setText("Arm Alarm");
		armButton.setVisible(true);
		armButtonMethod(armButton);
		
		JButton disarmButton = new JButton();
		disarmButton.setText("Disarm Alarm");
		disarmButton.setVisible(true);
		disarmButtonMethod(disarmButton);
		
		this.add(viewSensors);
		this.add(newSensor);
		this.add(armButton);
		this.add(disarmButton);
	}
	
	private void viewSensorsButtonMethod(JButton button){
		/**
		 * TODO Implement button
		 */
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
				ViewSensorsFrame vsf = new ViewSensorsFrame(secsys);
				SwingUtilities.invokeLater(vsf);
			}
		});
	}
	
	private void newSensorButtonMethod(JButton button){
		
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				RegisterSensorFrame rsf = new RegisterSensorFrame(secsys);
				SwingUtilities.invokeLater(rsf);
			}
		});
	}
	
	private void armButtonMethod(JButton button){
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				AuthenticationFrame af = new AuthenticationFrame(secsys,false);
				SwingUtilities.invokeLater(af);
			}
		});
	}
	
	private void disarmButtonMethod(JButton button){
		/**
		 * TODO Implement button
		 */
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
				AuthenticationFrame af = new AuthenticationFrame(secsys,true);
				SwingUtilities.invokeLater(af);
			}
		});
	}
	
	
	public static void main(String[] args){
		//SecSystemMainMenu secsystemui = new SecSystemMainMenu();
		
		
		
		//SwingUtilities.invokeLater(secsystemui);
	}

}
