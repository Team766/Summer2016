package com.team766.tests;

import lib.Actor;
import lib.Scheduler;

import org.junit.Test;

import tests.Gyro;
import tests.RobotTestCase;

import com.team766.lib.ConfigFile;
import com.team766.lib.Messages.DriveDistance;
import com.team766.lib.Messages.DriveStatusUpdate;
import com.team766.robot.Actors.Drive.Drive;

public class ArchitectureTest extends RobotTestCase{
	
	@Test
	public void testMessageOrder() throws Exception{
		Scheduler.getInstance().add(new TestActor());
		assertTrueTimed(() -> {return !((TestActor)(Scheduler.getInstance().getActor(TestActor.class))).onToNextMessage;}, 2);
		
		Thread.sleep(500);
		
		((Gyro)(instance.getGyro(ConfigFile.getGyro()))).setAngle(50);

		assertTrueTimed(() -> {return !((TestActor)(Scheduler.getInstance().getActor(TestActor.class))).onToNextMessage;}, 2);
		
		((Gyro)(instance.getGyro(ConfigFile.getGyro()))).setAngle(90);

		assertTrueTimed(() -> {return ((TestActor)(Scheduler.getInstance().getActor(TestActor.class))).onToNextMessage;}, 2);
	}
	
	private class TestActor extends Actor{
		public boolean onToNextMessage = false;
		
		@Override
		public void init() {
			acceptableMessages = new Class[]{DriveStatusUpdate.class};
		}
		
		@Override
		public void run() {
			waitForMessage(new DriveDistance(90, 0), DriveStatusUpdate.class);
			onToNextMessage = true;
		}
		
		public void step(){
		}

		@Override
		public String toString() {
			return "Actor:\tTestActor";
		}
		
	}
}
