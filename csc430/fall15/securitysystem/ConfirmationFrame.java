package edu.cuny.csi.csc430.fall15.securitysystem;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ConfirmationFrame extends JFrame implements Runnable{
	
	private String title;
	
	public ConfirmationFrame(String title){
		super(title);
		this.title = title;
	}
	
	public void run(){
		createGUI();
	}
	
	private void createGUI(){
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.setVisible(true);
		this.setSize(400,100);
		this.setLocation(500,250);
		
		JLabel label = new JLabel(title);
		label.setVisible(true);
		this.add(label);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
