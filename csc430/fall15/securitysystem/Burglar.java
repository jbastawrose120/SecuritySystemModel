package edu.cuny.csi.csc430.fall15.securitysystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Burglar extends JFrame implements Runnable{

	private static final long serialVersionUID = -9208347620817926345L;
	/**
	 * Burglar is called in SecSysMainMenu.run()
	 */
	
	private SecuritySystem secsys;
	private static String title = "Super Stealthy Burglar";

	public Burglar(SecuritySystem secsys) {
		// TODO Auto-generated constructor stub
		super(title);
		this.secsys = secsys;
	}
	
	public void run(){
		createGUI();
	}
	
	private void breakIn(){
		this.secsys.triggerASensor(0);
		try{
			Thread.sleep(100);
		}
		catch(Exception ex){
			System.out.println(ex);
		}
		this.secsys.clearSensorTriggers();
	}
	
	
	
	private void createGUI(){
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.setVisible(true);
		this.setSize(400,100);
		this.setLocation(500,100);
		
		JButton button = new JButton("Break into House");
		button.setVisible(true);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				breakIn();
			}
		});
		this.add(button);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Burglar b = new Burglar(new SecuritySystem());
		SwingUtilities.invokeLater(b);
	}
}
