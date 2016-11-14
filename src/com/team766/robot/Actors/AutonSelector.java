package com.team766.robot.Actors;

import lib.Actor;
import lib.LogFactory;
import lib.Scheduler;

import com.team766.lib.Messages.DriveDistance;
import com.team766.lib.Messages.DrivePath;
import com.team766.lib.Messages.DriveStatusUpdate;
import com.team766.robot.Constants;

public class AutonSelector extends Actor{
	
	private int autonMode;
	
	public AutonSelector(int mode){
		this.autonMode = mode;
	}
	
	@Override
	public void init() {
		acceptableMessages = new Class[]{DriveStatusUpdate.class};
	}
	
	@Override
	public void run() {
		switch(Constants.AUTONS[autonMode]){
			case "None":
				System.out.println("Auton: None");
				LogFactory.getInstance("General").print("Auton: None");
				break;
			case "Inside Lane Path":
				System.out.println("Auton: Inside Lane Path");
				waitForMessage(new DrivePath("InsideLanePathFar"), DriveStatusUpdate.class);
				break;
			default:
				System.out.println("Auton: Failed to select auton");
				LogFactory.getInstance("General").print("Auton: Failed to select auton");
				break;
		}
		
		Scheduler.getInstance().remove(this);
	}
	
	public void step(){
	}

	@Override
	public String toString() {
		return "Actor:\tAutonSelector";
	}

}
