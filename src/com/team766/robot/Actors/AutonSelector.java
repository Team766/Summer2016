package com.team766.robot.Actors;

import lib.Actor;
import lib.Scheduler;

import com.team766.lib.Messages.DriveDistance;

public class AutonSelector extends Actor{

	@Override
	public void init() {
	}
	
	@Override
	public void run() {
//		for(int i = 0; i < 5; i++){
			sendMessage(new DriveDistance(90, 0));
//			sendMessage(new DriveDistance(0, 0.3));
//		}
	
		Scheduler.getInstance().remove(this);
	}

	@Override
	public String toString() {
		return "Actor:\tAutonSelector";
	}

}
