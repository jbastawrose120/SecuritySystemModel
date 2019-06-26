package edu.cuny.csi.csc430.fall15.securitysystem;

import java.awt.*;
import javax.swing.*;

public class AlarmFrame extends JFrame implements Runnable{
	/**
	 * TODO CALL ALARM FRAME
	 */
	private static final long serialVersionUID = -7035710714683725442L;
	
	private static String title = "THIS IS THE ALARM";

	public AlarmFrame(){
		super(title);
	}
	
	public void run(){
		createGUI();
	}
	
	private void createGUI(){
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.setVisible(true);
		this.setSize(1250,200);
		this.setLocation(150,250);
		
		String text = "THE ALARM IS ON \nPLEASE STAY WHERE YOU ARE AND WAIT FOR THE POLICE"
				+ "\nWEE WOO WEE WOO WEE WOO WEE WOO WEE WOO";
		JTextArea textArea = new JTextArea(text);
		textArea.setVisible(true);
		textArea.setEditable(false);
		Font font = textArea.getFont();
		float size = font.getSize() + 25.0f;
		textArea.setFont(font.deriveFont(size));
		this.add(textArea);
	}
	
	
	public static void main(String[] args){
		AlarmFrame af = new AlarmFrame();
		
		SwingUtilities.invokeLater(af);
	}

}
