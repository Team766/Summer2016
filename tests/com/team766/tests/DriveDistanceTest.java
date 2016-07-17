package com.team766.tests;

import org.junit.Test;

import tests.RobotTestCase;
import tests.Encoder;

import com.team766.lib.ConfigFile;
import com.team766.lib.Scheduler.DriveTo;
import com.team766.lib.Scheduler.Scheduler;
import com.team766.robot.Constants;
import com.team766.robot.HardwareProvider;

public class DriveDistanceTest extends RobotTestCase{
	
	private double distance;
	
	protected void setUp() throws Exception {
		distance = 5;
	}
	
	@Test
	public void testDriveForward(){
		try {
			super.setUp();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {
			Scheduler.getInstance().sendMessage(new DriveTo(0, distance, 0));
		} catch (InterruptedException e) {
			System.out.println("Failed to send DriveTo() in Test");
			e.printStackTrace();
		}
		
		assert(instance.getMotor(ConfigFile.getLeftMotor()).get() > 0); 
		assert(instance.getMotor(ConfigFile.getRightMotor()).get() > 0); 
		
		((Encoder)(HardwareProvider.getInstance().getLeftEncoder())).set((int)(distance / Constants.wheel_circumference * Constants.counts_per_rev));
		((Encoder)(HardwareProvider.getInstance().getRightEncoder())).set((int)(distance / Constants.wheel_circumference * Constants.counts_per_rev));
		
		assert(instance.getMotor(ConfigFile.getLeftMotor()).get() == 0); 
		assert(instance.getMotor(ConfigFile.getRightMotor()).get() == 0); 
	}
}
