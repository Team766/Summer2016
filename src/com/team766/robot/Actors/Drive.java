package com.team766.robot.Actors;

import interfaces.RobotProvider;

import com.team766.lib.Scheduler.Actor;
import com.team766.lib.Scheduler.Message;
import com.team766.lib.Scheduler.MotorCommand;

public class Drive extends Actor{

	public void init() {
	}
	
	public void run() {
	}


	public void readMessage(Message m) {
		if(!relevantMessage(m))
			return;
		
		MotorCommand motor = (MotorCommand)m;
		if(motor.getMotor().equals("rightDrive"))
			RobotProvider.instance.getRightDrive().set(motor.getValue());
	
	}

	public Message postMessage() {
		return null;
	}
	
	public String toString(){
		return "Actor:\tDrive";
	}

}
