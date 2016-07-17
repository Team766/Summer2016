package com.team766.tests;

import org.junit.Test;

import tests.RobotTestCase;
import tests.Encoder;

import com.team766.lib.ConfigFile;
import com.team766.lib.Scheduler.DriveTo;
import com.team766.lib.Scheduler.Scheduler;
import com.team766.robot.Constants;
import com.team766.robot.HardwareProvider;
import com.team766.robot.Actors.Drive;

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
		
		//Wait for message to be processed
		Drive drive = (Drive)Scheduler.getInstance().getActor(Drive.class);
		while(drive.remainingMessages() > 0){
			//WAITING TO COMPLETE MESSAGES!!
		}
		
		assertTrue(instance.getMotor(ConfigFile.getLeftMotor()).get() > 0); 
		assertTrue(instance.getMotor(ConfigFile.getRightMotor()).get() > 0); 
		
		((Encoder)(instance.getEncoder(ConfigFile.getLeftEncoder()[0], ConfigFile.getLeftEncoder()[1]))).set((int)(distance / Constants.wheel_circumference * Constants.counts_per_rev));
		((Encoder)(instance.getEncoder(ConfigFile.getRightEncoder()[0], ConfigFile.getRightEncoder()[1]))).set((int)(distance / Constants.wheel_circumference * Constants.counts_per_rev));
		
		assertTrue(instance.getMotor(ConfigFile.getLeftMotor()).get() == 0); 
		assertTrue(instance.getMotor(ConfigFile.getRightMotor()).get() == 0); 
	}
}
