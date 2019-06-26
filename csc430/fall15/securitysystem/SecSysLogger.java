package edu.cuny.csi.csc430.fall15.securitysystem;

import java.io.*;
import java.util.*;
import java.text.*;

public class SecSysLogger {
	
	/**
	 * TODO Logs are only getting written to when the program exits,
	 * not in real time. At least the timestamps are correct now. 
	 */
	
	private String fileName = "SecSysLog.txt";
	private FileOutputStream fileOut;
	private BufferedOutputStream bufferedOut;
	private PrintStream printStream;
	
	private Date date;
	private DateFormat dateFormat;
	
	public SecSysLogger(){
		init();
	}
	
	private void init(){
		try{
			this.fileOut = new FileOutputStream(new File(fileName), true);
			this.bufferedOut = new BufferedOutputStream(fileOut);
			this.printStream = new PrintStream(bufferedOut);
			
		}
		catch(Exception ex){
			System.out.println(ex);
		}
		this.dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss a");
	}
	
	public void addToLog(String data){
		this.date = new Date();
		this.printStream.println(this.dateFormat.format(date) + "    " + data);
	}
	
	public void closeFile(){
		this.printStream.close();
		try{
			this.bufferedOut.close();
			this.fileOut.close();
		}
		catch(IOException ioe){
			System.out.println(ioe);
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SecSysLogger ssl = new SecSysLogger();

		String data = "Stuff happened";
		ssl.addToLog(data);
		
		data = "More Stuff Happened\n";
		ssl.addToLog(data);
		
		data = "Wow even MORE stuff happened";
		ssl.addToLog(data);
		
		ssl.closeFile();
	}

}
