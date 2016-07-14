package com.team766.robot.Actors;

import com.team766.lib.Scheduler.Actor;
import com.team766.lib.Scheduler.Message;
import com.team766.lib.Scheduler.MotorCommand;

public class OperatorControl extends Actor {

	public void init() {
	}
	
	public void run() {
	}

	public void readMessage(Message m) {
	}

	public Message postMessage() {
		return new MotorCommand(1.0, "rightDrive");
	}

	public String toString() {
		return "Actor:\tOperatorControl";
	}
	
}
