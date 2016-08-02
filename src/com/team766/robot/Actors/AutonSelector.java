package com.team766.robot.Actors;

import lib.Actor;

import com.team766.lib.Messages.DriveDistance;

public class AutonSelector extends Actor{

	@Override
	public void init() {
	}
	
	@Override
	public void run() {
		sendMessage(new DriveDistance(0, 0.7));
	}

	@Override
	public String toString() {
		return "Actor:\tAutonSelector";
	}

}
