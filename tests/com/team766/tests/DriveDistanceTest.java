package com.team766.tests;

import lib.Scheduler;

import org.junit.Test;

import tests.Encoder;
import tests.Gyro;
import tests.RobotTestCase;

import com.team766.lib.ConfigFile;
import com.team766.lib.Messages.DriveDistance;
import com.team766.lib.Messages.DriveTo;
import com.team766.robot.Constants;

public class DriveDistanceTest extends RobotTestCase{
	
	private double distance;
	
	protected void setUp() throws Exception {
		super.setUp();
		distance = 5;
	}
	
	@Test
	public void testDriveForward() throws Exception{
		try {
			Scheduler.getInstance().sendMessage(new DriveTo(0, distance, 0));
		} catch (InterruptedException e) {
			System.out.println("Failed to send DriveTo() in Test");
			e.printStackTrace();
		}
		
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getLeftMotor()).get() > 0;}, 2); 
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getRightMotor()).get() > 0;}, 2);
		
		
		((Encoder)(instance.getEncoder(ConfigFile.getLeftEncoder()[0], ConfigFile.getLeftEncoder()[1]))).set((int)Math.ceil(distance / Constants.wheel_circumference * Constants.counts_per_rev));
		((Encoder)(instance.getEncoder(ConfigFile.getRightEncoder()[0], ConfigFile.getRightEncoder()[1]))).set((int)Math.ceil(distance / Constants.wheel_circumference * Constants.counts_per_rev));
		
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getLeftMotor()).get() == 0;}, 3);
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getRightMotor()).get() == 0;}, 3);
	}
	
	@Test
	public void testDriveDistance() throws Exception{
		distance = 5;
		Scheduler.getInstance().sendMessage(new DriveDistance(0, distance));
		
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getLeftMotor()).get() > 0;}, 2); 
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getRightMotor()).get() > 0;}, 2);
		
		
		((Encoder)(instance.getEncoder(ConfigFile.getLeftEncoder()[0], ConfigFile.getLeftEncoder()[1]))).set((int)Math.ceil(distance / Constants.wheel_circumference * Constants.counts_per_rev));
		((Encoder)(instance.getEncoder(ConfigFile.getRightEncoder()[0], ConfigFile.getRightEncoder()[1]))).set((int)Math.ceil(distance / Constants.wheel_circumference * Constants.counts_per_rev));
		
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getLeftMotor()).get() == 0;}, 3);
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getRightMotor()).get() == 0;}, 3);
	}
	
	@Test
	public void testDriveBackward() throws Exception{
		distance = 5;
		Scheduler.getInstance().sendMessage(new DriveDistance(0, -distance));
		
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getLeftMotor()).get() < 0;}, 2); 
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getRightMotor()).get() < 0;}, 2);
		
		
		((Encoder)(instance.getEncoder(ConfigFile.getLeftEncoder()[0], ConfigFile.getLeftEncoder()[1]))).set((int)Math.ceil(-distance / Constants.wheel_circumference * Constants.counts_per_rev));
		((Encoder)(instance.getEncoder(ConfigFile.getRightEncoder()[0], ConfigFile.getRightEncoder()[1]))).set((int)Math.ceil(-distance / Constants.wheel_circumference * Constants.counts_per_rev));
	
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getLeftMotor()).get() == 0;}, 3);
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getRightMotor()).get() == 0;}, 3);
	}
	
	@Test
	public void testTurnDrive() throws Exception{
		distance = 5;
		Scheduler.getInstance().sendMessage(new DriveDistance(90, distance));

		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getLeftMotor()).get() > 0;}, 2); 
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getRightMotor()).get() < 0;}, 2);
		
		((Gyro)(instance.getGyro(ConfigFile.getGyro()))).setAngle(90);
		
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getLeftMotor()).get() > 0;}, 2); 
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getRightMotor()).get() > 0;}, 2);
		
		((Encoder)(instance.getEncoder(ConfigFile.getLeftEncoder()[0], ConfigFile.getLeftEncoder()[1]))).set((int)Math.ceil(distance / Constants.wheel_circumference * Constants.counts_per_rev));
		((Encoder)(instance.getEncoder(ConfigFile.getRightEncoder()[0], ConfigFile.getRightEncoder()[1]))).set((int)Math.ceil(distance / Constants.wheel_circumference * Constants.counts_per_rev));
		
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getLeftMotor()).get() == 0;}, 3);
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getRightMotor()).get() == 0;}, 3);
	}
}
