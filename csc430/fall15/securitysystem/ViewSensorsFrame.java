package edu.cuny.csi.csc430.fall15.securitysystem;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class ViewSensorsFrame extends JFrame implements Runnable{

	private static final long serialVersionUID = 6914183655456327076L;
	
	private static String title = "List of Deployed Sensors";
	
	private SecuritySystem secsys;
	
	public ViewSensorsFrame(SecuritySystem secsys){
		super(title);
		this.secsys = secsys;
	}
	
	public void run(){
		this.createGUI();
	}
	
	private void createGUI(){
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.setVisible(true);
		this.setSize(400,400);
		this.setLocation(250,200);
		
		JTextArea textArea = new JTextArea(secsys.getSensorListString());
		textArea.setVisible(true);
		textArea.setSize(500,500);
		this.add(textArea);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ViewSensorsFrame vsf = new ViewSensorsFrame(new SecuritySystem());
		
		SwingUtilities.invokeLater(vsf);
	}

}
