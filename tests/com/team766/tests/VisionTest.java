package com.team766.tests;

import lib.Scheduler;

import org.junit.Test;

import tests.Camera;
import tests.Encoder;
import tests.Gyro;
import tests.RobotTestCase;

import com.team766.lib.ConfigFile;
import com.team766.lib.Messages.VisionStatusUpdate;
import com.team766.robot.Constants;
import com.team766.robot.Actors.Auton.AutonSelector;

public class VisionTest extends RobotTestCase{
	@Test
	public void testGrabImage() throws Exception{
		setImage("img_1.jpeg");
	}
	
	private void setImage(String image){
		((Camera)(instance.getCamera(ConfigFile.getAxisCamera()[0], ConfigFile.getAxisCamera()[1]))).setNextImage(image);
	}
	
	@Test
	public void testAuton() throws Exception{
		Scheduler.getInstance().add(new AutonSelector(0));
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				((AutonSelector)(Scheduler.getInstance().getActor(AutonSelector.class))).targetWithVision();
			}
		}).start();
		
		//Target Forward and to the camera's right
		Scheduler.getInstance().sendMessage(new VisionStatusUpdate(15, 5));
		System.out.println("Checking!");
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getLeftMotor()).get() > 0;}, 2);
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getRightMotor()).get() <= 0;}, 2);
		
		((Gyro)(instance.getGyro(ConfigFile.getGyro()))).setAngle(15);
		
		((Encoder)(instance.getEncoder(ConfigFile.getLeftEncoder()[0], ConfigFile.getLeftEncoder()[1]))).set((int)Math.ceil(5 / Constants.wheel_circumference * Constants.counts_per_rev));
		((Encoder)(instance.getEncoder(ConfigFile.getRightEncoder()[0], ConfigFile.getRightEncoder()[1]))).set((int)Math.ceil(5 / Constants.wheel_circumference * Constants.counts_per_rev));
		
		Scheduler.getInstance().sendMessage(new VisionStatusUpdate(0, 0));
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getLeftMotor()).get() == 0;}, 2);
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getRightMotor()).get() == 0;}, 2);
	}
}
