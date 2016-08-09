package com.team766.robot.Actors;

import lib.Actor;
import lib.Scheduler;

import com.team766.lib.Messages.DriveDistance;
import com.team766.lib.Messages.DriveStatusUpdate;

public class AutonSelector extends Actor{

	@Override
	public void init() {
		acceptableMessages = new Class[]{DriveStatusUpdate.class};
	}
	
	@Override
	public void run() {
//		for(int i = 0; i < 5; i++){
			waitForMessage(new DriveDistance(90, 0), DriveStatusUpdate.class);
//			waitForMessage(new DriveDistance(0, 0.3), DriveStatusUpdate.class);
//		}
	
		Scheduler.getInstance().remove(this);
	}

	@Override
	public String toString() {
		return "Actor:\tAutonSelector";
	}

}
