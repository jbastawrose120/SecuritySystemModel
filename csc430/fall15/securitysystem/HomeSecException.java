package edu.cuny.csi.csc430.fall15.securitysystem;

public class HomeSecException extends Exception {
	
	//error codes
	public static int DUPLICATE_SENSOR_ID = 0;
	public static int INVALID_SENSOR_ID = 1;
	public static int ACCESS_DENIED = 2;
	public static int INVALID_CODE_SET = 3;
	
	public static String[] MESSAGE = {
		"Sensor ID Already Deployed",
		"Sensor ID Is Outside of Range of Possible Values",
		"Wrong Code, Access Denied",
		"Arm Code Already Set"
	};
	
	private int code;
	

	public HomeSecException() {
		// TODO Auto-generated constructor stub
	}

	public HomeSecException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public HomeSecException(String message, int code){
		super(message);
		this.code = code;
	}
	
	public int getCode(){
		return code;
	}

	@Override
	public String toString() {
		return "HomeSecException [code=" + code + ", toString()="
					+ super.toString() + "]\n" + MESSAGE[code] ;
	}
	
	
	
	


}
