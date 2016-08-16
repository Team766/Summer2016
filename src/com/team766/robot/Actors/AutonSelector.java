package com.team766.robot.Actors;

import trajectory.AutoPaths;
import lib.Actor;
import lib.Scheduler;

import com.team766.lib.Messages.DriveDistance;
import com.team766.lib.Messages.DrivePath;
import com.team766.lib.Messages.DriveStatusUpdate;

public class AutonSelector extends Actor{

	@Override
	public void init() {
		acceptableMessages = new Class[]{DriveStatusUpdate.class};
	}
	
	@Override
	public void run() {
//		for(int i = 0; i < 4; i++){
//			waitForMessage(new DriveDistance(90, 0), DriveStatusUpdate.class);
//			sleep();
//			waitForMessage(new DriveDistance(0, 6.0), DriveStatusUpdate.class);
//			sleep();
//		}
		waitForMessage(new DrivePath("InsideLanePathFar"), DriveStatusUpdate.class);
	
		Scheduler.getInstance().remove(this);
	}
	
	public void step(){
	}

	@Override
	public String toString() {
		return "Actor:\tAutonSelector";
	}

}
