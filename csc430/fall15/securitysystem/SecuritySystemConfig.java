package edu.cuny.csi.csc430.fall15.securitysystem;

import java.io.*;
import java.util.*;

import javax.swing.SwingUtilities;

public class SecuritySystemConfig {
	
	/**
	 * THIS IS THE LAUNCHPAD FOR THE APPLICATION
	 */
	private SecuritySystem secsys;
	
	private String fileName = "SecSysConfig.txt";
	
	private FileReader fileReader;
	private BufferedReader bufferedReader;
	private StringBuilder stringBuilder;

	public SecuritySystemConfig() {
		// TODO Auto-generated constructor stub
		init();
	}
	
	private void init(){
		secsys = new SecuritySystem();
		stringBuilder = new StringBuilder(4096);
	}
	
	public SecuritySystem getSecSysInstance(){
		return this.secsys;
	}
	
	public String readIn(){
		try{
			fileReader = new FileReader(this.fileName);
			bufferedReader = new BufferedReader(this.fileReader);
		}
		catch(Exception ex){
			System.err.println(ex);
		}
		
		String line;
		try{
			while((line = bufferedReader.readLine()) != null ){
				if(line.startsWith("#") || line.length() == 0)
					continue;
				
	            stringBuilder.append( line + "\n" );
	        }
			
	    	fileReader.close();
		}
		catch(Exception ex){
			System.out.println(ex);
		}
		
		return stringBuilder.toString();
		
	}
	
	
	public void execute(){
		
		String everything = this.readIn();
		
		String[] afterSplit = everything.split("\n");
		
		String name;
		int id;
		boolean armCodeSet;
		
		/**
		 * First line of StringBuilder will be either "false" or
		 * "true 91294" (true [space] code). 
		 * 
		 * So split the first line of the StringBuilder again, and 
		 * parse first index as a bool. If it's true, parse the second index
		 * as an int and pass it to the armCode method for SecuritySystem
		 */ 
		String [] doubleSplit = afterSplit[0].split(" ");
		armCodeSet = Boolean.parseBoolean(doubleSplit[0]);
		
		if (armCodeSet){
			try{
				secsys.setArmCode(Integer.parseInt(doubleSplit[1]));
			}
			catch(HomeSecException hse){
				System.err.println(hse);
			}
		}
		secsys.setArmCodeBool(armCodeSet);
		
		/**
		 * For loop starts at 1 to skip index 0, which has the boolean value.
		 * Increment by two, i is the name, i+1 is the int id.
		 */
		for(int i = 1; i < afterSplit.length; i+=2){
			name = afterSplit[i];
			id = Integer.parseInt(afterSplit[i+1]);
			
			try{
				secsys.addSensor(name, id);
			}
			catch(HomeSecException hse){
				System.err.println(hse);
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SecuritySystemConfig ssc = new SecuritySystemConfig();
		/*
		System.out.println(ssc.readIn());
		
		String everything = ssc.readIn();
		String[] afterSplit = everything.split("\n");
		System.out.println("\n");
		
		System.out.println(afterSplit[0]);
		*/
		
		ssc.execute();
		
		LoginFrame lf = new LoginFrame(ssc.getSecSysInstance());
		SwingUtilities.invokeLater(lf);
		
		Thread thread = new Thread(ssc.getSecSysInstance());
		thread.start();
		
		//SecSystemMainMenu ssmm = new SecSystemMainMenu(ssc.getSecSysInstance());
		//SwingUtilities.invokeLater(ssmm);
		

	}

}
