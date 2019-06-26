package edu.cuny.csi.csc430.fall15.securitysystem;

//import java.util.*;

public class Sensor {
	
	private String name;
	private int id;
	private boolean isArmed;
	private boolean isTriggered;
	
	/*
	 * If triggered while not armed, ignore.
	 * If triggered while armed, set off alarm.
	 */
	
	public Sensor(int id) throws HomeSecException{
		if(id < 1000 || id > 9999){
			throw new HomeSecException("Sensor() Constructor" , HomeSecException.INVALID_SENSOR_ID);
		}
		
		this.id = id;
		this.name = "Unnamed Sensor";
		this.isArmed = false;
		this.isTriggered = false;
		
	}
	
	public Sensor(String name, int id) throws HomeSecException{
		if(id < 1000 || id > 9999){
			throw new HomeSecException("Sensor() Constructor" , HomeSecException.INVALID_SENSOR_ID);
		}
		
		this.name = name;
		this.id = id;
		this.isArmed = false;
		this.isTriggered = false;
	}
	
	//SETTERS AND GETTERS
	
	public int getID(){
		return this.id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isArmed() {
		return isArmed;
	}

	public void setArmed(boolean isArmed) {
		this.isArmed = isArmed;
	}

	public boolean isTriggered() {
		return isTriggered;
	}

	public void setTriggered(boolean isTriggered) {
		this.isTriggered = isTriggered;
	}
	
	
	/*
	 * isAlarmSet() will be the function that is polled from Security System.
	 * If it returns true, Security System will run the alarm.
	 */
	public boolean isAlarmSet(){
		if(isArmed && isTriggered)
			return true;
		
		else
			return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sensor other = (Sensor) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sensor name= " + name + ", id = " + id + "\n";
	}
	
	public String sensorInfo(){
		return "Sensor [name=" + name + ", id=" + id + "]";
	}
}
