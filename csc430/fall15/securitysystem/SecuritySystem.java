package edu.cuny.csi.csc430.fall15.securitysystem;

import java.util.*;

import javax.swing.SwingUtilities;

import edu.cuny.csi.csc330.spring15.lab1.*;


public class SecuritySystem implements Runnable{
	
	private final int MAX_AUTHENTICATION_ATTEMPTS = 5;
	
	private int armCode;
	private boolean armCodeSet; //bool flag to make sure armCode is only set once
	private ArrayList<Sensor> sensorList;
	private ArrayList<Integer> usedIDs;
	private int authenticationAttempts;
	private boolean triggerAlarm;
	
	private SecSysLogger logs = new SecSysLogger();
	
	public SecuritySystem() {
		init();
	}
	
	private void init(){

		this.sensorList = new ArrayList<Sensor>();
		this.usedIDs = new ArrayList<Integer>();
		this.authenticationAttempts = 0;
		this.armCodeSet = false;
		this.triggerAlarm = false;
		this.logs.addToLog("SecuritySystem Instance Created");

	}
	
	public void setArmCode(int armCode) throws HomeSecException{
		if(this.armCodeSet){
			throw new HomeSecException("setArmCode() Method", HomeSecException.INVALID_CODE_SET);
		}
		else{
			this.armCode = armCode;
			this.armCodeSet = true;
			this.logs.addToLog("Arm Code Set");
		}
	}
	
	public void setArmCodeBool(boolean bool){
		this.armCodeSet = bool;
	}
	
	public boolean getArmCodeBool(){
		return this.armCodeSet;
	}
	
	public void addSensor(Sensor sensor) throws HomeSecException{
		if (this.usedIDs.contains(sensor.getID())){
			throw new HomeSecException("addSensor() Method", HomeSecException.DUPLICATE_SENSOR_ID);
		}
		this.sensorList.add(sensor);
		this.usedIDs.add(sensor.getID());
		
		this.logs.addToLog("Sensor Added. ID= " + sensor.getID());
	}
	
	public void addSensor(String name, int id) throws HomeSecException{
		if(this.usedIDs.contains(id)){
			throw new HomeSecException("addSensor() Method", HomeSecException.DUPLICATE_SENSOR_ID);
		}
		this.sensorList.add(new Sensor(name,id));
		this.usedIDs.add(id);
		this.logs.addToLog("Sensor Added. ID= " + id);
	}
	
	public void addSensor(String name){
		Randomizer randomizer = new Randomizer();
		int tempID = randomizer.generateInt(1000, 9999);
		
		while(!this.usedIDs.contains(tempID)){
			tempID = randomizer.generateInt(1000,9999);
		}
		
		
		try{
			this.sensorList.add(new Sensor(name, tempID));
		}
		catch(HomeSecException hse){
			System.out.println(hse);
		}
		
		this.logs.addToLog("Sensor Added. ID= " + tempID);
	}
	
	public void arm(int armCode) throws HomeSecException{
		/*
		 * Method exits if authentication fails,
		 * and must be re-called to try again
		 */
		
		this.authenticationAttempts++;
		if(this.authenticationAttempts > this.MAX_AUTHENTICATION_ATTEMPTS){
			/*
			 * TRIGGER ALARM
			 */
			this.triggerAlarm = true;
			this.alarm();
			
			if(this.authenticationAttempts > this.MAX_AUTHENTICATION_ATTEMPTS){
				this.authenticationAttempts = 0;
			}
		}
		
		if(this.armCode == armCode){
			System.out.println("Authentication Successful");
			this.authenticationAttempts = 0;
			
			/*
			 * Arm the alarm by arming each sensor
			 */
			
			if(sensorList != null){
				for(Sensor i : sensorList){
					i.setArmed(true);
				}
				this.logs.addToLog("System Armed");
			}
			
			else{
				System.out.println("No Sensors Deployed.");
				this.logs.addToLog("System Arm Failed: No Sensors Deployed");
			}
		}
			
		else{
			throw new HomeSecException("arm() method", HomeSecException.ACCESS_DENIED);
		}
	}
	
	public void disarm(int armCode) throws HomeSecException{
		/*
		 * Method exits if authentication fails,
		 * and must be re-called to try again
		 */
		
		this.authenticationAttempts++;
		if(this.authenticationAttempts > this.MAX_AUTHENTICATION_ATTEMPTS){
			/*
			 * TRIGGER ALARM
			 */
			this.triggerAlarm = true;
			this.alarm();
			
			if(this.authenticationAttempts > this.MAX_AUTHENTICATION_ATTEMPTS){
				this.authenticationAttempts = 0;
			}
		}
		
		if(this.armCode == armCode){
			System.out.println("Authentication Successful");
			this.authenticationAttempts = 0;
			
			if(sensorList != null){
				for(Sensor i : sensorList){
					i.setArmed(false);
				}
				this.logs.addToLog("System Disarmed");
			}
			
			else{
				System.out.println("No Sensors Deployed.");
				this.logs.addToLog("System Disarm Failed: No Sensors Deployed");
			}
		}
		
		else{
			throw new HomeSecException("disarm() method", HomeSecException.ACCESS_DENIED);
		}
	}
	
	@Override
	public String toString() {
		return "SecuritySystem [MAX_AUTHENTICATION_ATTEMPTS="
				+ MAX_AUTHENTICATION_ATTEMPTS + ", armCodeSet= " + armCodeSet + 
				", armCode=" + armCode + ", sensorList=" + sensorList
				+ ", usedIDs=" + usedIDs + ", authenticationAttempts="
				+ authenticationAttempts + ", triggerAlarm=" + triggerAlarm
				+ "]";
	}
	
	
	private void alarm(){
		boolean flag = false;
		
		/*
		 * If a sensor is triggered, trip alarm
		 */
		for(Sensor i : sensorList){
			if(i.isAlarmSet()){
				flag = true;
				break;
			}
		}
		
		if(this.triggerAlarm || flag){
			/*
			 * What does the alarm do?
			 */
			AlarmFrame af = new AlarmFrame();
			SwingUtilities.invokeLater(af);
			//Thread t1 = new Thread(new AlarmFrame());
			//t1.start();
			this.logs.addToLog("ALARM TRIGGERED");
			
			for(int i = 0; i < 5; i++){
				System.out.println("----------------WEE WOO WEE WOO WEE WOO WEE WOO WEE WOO----------------\n");
				
				System.out.println("----------------THIS IS THE ALARM, YOU'RE GOING TO JAIL----------------\n");
				
			}
		}	
	}
	
	public int getRemainingAttempts(){
		return this.MAX_AUTHENTICATION_ATTEMPTS - this.authenticationAttempts;
		}

	public String getSensorListString(){
		StringBuilder listSensors = new StringBuilder(4096);
		
		if(sensorList.size() == 0)
			return null;
		
		for(int i = 0; i < sensorList.size(); i++){
			listSensors.append(sensorList.get(i));
		}
		return listSensors.toString();
		
	}

	public void run(){
		while(true){
			for(Sensor i : this.sensorList){
				
				if(i.isAlarmSet()){
					/**
					 * ALAAAAARM
					 * and then break
					 */
					this.alarm();
					i.setTriggered(false);
					break;
				}
			}
		}
	}
	
	public void clearSensorTriggers(){
		for(Sensor i: this.sensorList){
			i.setTriggered(false);
		}
	}
	
	public void closeLogFile(){
		this.logs.closeFile();
	}
	
	public void addToLog(String data){
		this.logs.addToLog(data);
	}
	
	public ArrayList<Sensor> getSensorList(){
		return sensorList;
	}
	
	public void triggerASensor(int index){
		this.sensorList.get(index).setTriggered(true);
	}
	
	public static void main(String[] args){
		SecuritySystem secsys = new SecuritySystem();
		System.out.println(secsys);
		
		try{
			Sensor s = new Sensor(8394);
			secsys.addSensor(s);
		}	
		catch(HomeSecException hse){
			System.err.println(hse);
		}
		
		
		try{
			Sensor s1 = new Sensor(8394);
			secsys.addSensor(s1);
		}
		
		catch(HomeSecException hse){
			System.err.println(hse);
		}
		
		System.out.println(secsys);
		System.out.println(secsys.sensorList);
		
		secsys.addSensor("Front Door");
		System.out.println("\n" + secsys);
		
		try{
			secsys.arm(3544);
		}
		
		catch(HomeSecException hse){
			System.err.println(hse);
		}
		
		System.out.println("\n" + secsys);
		
		for(int i = 0; i < 5; i++){
			try{
				secsys.arm(3544);
			}
			catch(HomeSecException hse){
				;
			}
		}
		
	}
}
