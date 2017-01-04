package com.team766.tests;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import lib.Actor;
import lib.ConstantsFileReader;
import lib.Scheduler;

import org.junit.Test;

import tests.Gyro;
import tests.RobotTestCase;

import com.team766.lib.ConfigFile;
import com.team766.lib.Messages.DriveDistance;
import com.team766.lib.Messages.DriveStatusUpdate;

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
	
	@Test
	public void testReloadConstants() throws Exception{
		
		BufferedReader reader = new BufferedReader(new FileReader(this.getClass().getClassLoader().getResource(ConstantsFileReader.fileName).getPath()));
		
		//Grab first constant from a file to test with
		String[] tokens = reader.readLine().split(",");
		String constantName = tokens[0];
		double value = Double.parseDouble(tokens[1]);
		
		String restOfFile = "";
		String currLine = reader.readLine();
		while(currLine != null){
			restOfFile += currLine + "\n";
			currLine = reader.readLine();
		}
		reader.close();
		
		//Check loaded number correctly
		assertTrueTimed(() -> {return ConstantsFileReader.getInstance().get(constantName) == value;}, 2);

		//Change value and save to file
		double newValue = value + 10;	
		BufferedWriter writer = new BufferedWriter(new FileWriter(this.getClass().getClassLoader().getResource(ConstantsFileReader.fileName).getPath()));
		writer.write(constantName + ", " + newValue + "\n" + restOfFile);
		writer.close();
		
		//Load in new value
		disableInit();
		
		//Check that new value has been added
		assertTrueTimed(() -> {return ConstantsFileReader.getInstance().get(constantName) == newValue;}, 2);

		//Change the value back
		writer = new BufferedWriter(new FileWriter(this.getClass().getClassLoader().getResource(ConstantsFileReader.fileName).getPath()));
		writer.write(constantName + ", " + value + "\n" + restOfFile);
		writer.close();
		
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
