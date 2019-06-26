package edu.cuny.csi.csc430.fall15.securitysystem;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class SecSysTestUnit {
	
	private SecuritySystemConfig ssc;
	
	@Before
	public void prereq(){
		ssc = new SecuritySystemConfig();
		ssc.execute();
	}

	public SecSysTestUnit() {
		// TODO Auto-generated constructor stub
	}

}
